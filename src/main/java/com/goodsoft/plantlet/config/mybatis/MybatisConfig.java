package com.goodsoft.plantlet.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * function mybatis配置属性
 * Created by 严彬荣 on 2017/8/3.
 * version v1.0
 */
@EnableTransactionManagement
@Configuration
public class MybatisConfig {

    @Resource
    private DruidDataSource druidDataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //使用缓存
        configuration.setCacheEnabled(true);
        //懒加载
        configuration.setLazyLoadingEnabled(true);
        configuration.setAggressiveLazyLoading(false);
        //单一语句返回多结果集
        configuration.setMultipleResultSetsEnabled(true);
        //列标签代替列名
        configuration.setUseColumnLabel(true);
        //设置数据库响应超时时间为180秒
        configuration.setDefaultStatementTimeout(180);
        //允许嵌套语句中使用分页
        configuration.setSafeRowBoundsEnabled(true);
        //开启驼峰命名
        configuration.setMapUnderscoreToCamelCase(true);
        //设置参数为jdbc类型
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        //日志管理（使用slf4j）
        configuration.setLogImpl(org.apache.ibatis.logging.slf4j.Slf4jImpl.class);
        //支持自动生成主键
        configuration.setUseGeneratedKeys(true);
        //mybatis日志前缀
        configuration.setLogPrefix("plantlet");
        //设置返回数据默认大小（可重写）
        configuration.setDefaultFetchSize(100);
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(druidDataSource);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath*:static/mapper/*.xml"));
        //设置映射对象别名
        /*sqlSessionFactory.setTypeAliasesPackage("com.goodsoft.plantlet.domain.entity");*/
        //设置数据库厂商标识
       /* sqlSessionFactory.setDatabaseIdProvider();*/
        return sqlSessionFactory;
    }
}
