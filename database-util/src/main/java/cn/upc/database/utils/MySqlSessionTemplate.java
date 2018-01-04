package cn.upc.database.utils;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import cn.upc.database.model.PageData;

import java.util.Map;

@SuppressWarnings("unchecked")
public class MySqlSessionTemplate extends SqlSessionTemplate {
    /**
     * @param sqlSessionFactory
     */
    public MySqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    /**
     * @param sqlSessionFactory
     * @param executorType
     * @param exceptionTranslator
     */
    public MySqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator) {
        super(sqlSessionFactory, executorType, exceptionTranslator);
    }

    /**
     * @param sqlSessionFactory
     * @param executorType
     */
    public MySqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        super(sqlSessionFactory, executorType);
    }


    public <E> PageData<E> selectPageList(String sql, Map<String, Object> params, Integer pageNo, Integer pageSize) {
        return PageQuery.selectPageList(this, pageNo, pageSize, sql, params);
    }

}

