package cn.upc.database.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.mybatis.spring.SqlSessionTemplate;

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
	
	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		return null;
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
	
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement,BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

}
