package cn.upc.database.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.StringUtils;

import cn.upc.database.model.PageData;

@Intercepts(
	    {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}),
	     @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
@SuppressWarnings("rawtypes")
public class PageQuery<E> implements Interceptor {

	
	public static final ThreadLocal<PageData> localPage = new ThreadLocal<>();
	
	public static PageData selectPageList(SqlSessionTemplate temp,int pageNum,int pageSize,String sql,Map<String,Object> params) {
		try {
			localPage.set(new PageData<>(pageNum, pageSize));
			temp.selectList(sql,params);
			return localPage.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			localPage.remove();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (localPage.get() == null) {
			return invocation.proceed();
		}
		if (invocation.getTarget() instanceof StatementHandler) {
			StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
			// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环  
            // 可以分离出最原始的的目标类)
			while(metaStatementHandler.hasGetter("h")) {
				Object object = metaStatementHandler.getValue("h");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			//分离最后一个代理对象的目标类
			while(metaStatementHandler.hasGetter("target")) {
				Object object = metaStatementHandler.getValue("target");
				metaStatementHandler = SystemMetaObject.forObject(object);
			}
			MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
			PageData page = localPage.get();
			BoundSql boundSql = (BoundSql)metaStatementHandler.getValue("delegate.boundSql");
			StringBuilder sql = new StringBuilder(boundSql.getSql());
			if (StringUtils.endsWithIgnoreCase(sql.toString(), ";")) {
				sql.deleteCharAt(sql.length() - 1);
			}
			//重写sql
			String pageSql = buildPageSql(sql.toString(), page);
			metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
			Connection connection = (Connection)invocation.getArgs()[0];
			setPageParameter(sql.toString(), connection, mappedStatement, boundSql, page);
			return invocation.proceed();
		} else if (invocation.getTarget() instanceof ResultSetHandler) {
            Object result = invocation.proceed();  
            PageData page = localPage.get();
            List resultArrayList = result == null? new ArrayList():(List)result;
            page.setResult(resultArrayList);  
            return result;  
        }
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties arg0) {
	}
	
	private String buildPageSql(String sql,PageData page) {
		StringBuilder pageSql = new StringBuilder(255);
		pageSql.append(sql);
		pageSql.append(" limit " + page.getStartRow() + "," + page.getEndRow());
		return pageSql.toString();
	}
	
	private void setPageParameter(String sql,Connection connection,MappedStatement mappedStatement,
			BoundSql boundSql,PageData page) {
		if(page.getTotalCount() != 0 || page.getTotalPage() != 0){
    		return ;
    	}
        // 记录总记录数  
        String countSql = "select count(0) from (" + sql + ") tempTb";  
        PreparedStatement countStmt = null;  
        ResultSet rs = null;  
        try {  
            countStmt = connection.prepareStatement(countSql);  
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());  
            MetaObject countBsObject = SystemMetaObject.forObject(countBS);
            MetaObject boundSqlObject = SystemMetaObject.forObject(boundSql);
            countBsObject.setValue("metaParameters",boundSqlObject.getValue("metaParameters"));  
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());  
            rs = countStmt.executeQuery();  
            int totalCount = 0;  
            if (rs.next()) {  
                totalCount = rs.getInt(1);  
            }  
            page.setTotalCount(totalCount);  
            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);  
            page.setTotalPage(totalPage);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                rs.close();  
                countStmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
	}
	
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement,BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

}
