package com.example.druid.multisource.cfg;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;


@Primary
@Configuration
@MapperScan(basePackages = "com.example.druid.multisource.dao.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterSource {
    private static final String MAPPER_LOCAL = "classpath:/mapping/MasterMapper.xml";


    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DruidDataSource druidDataSource() {
        return  new DruidDataSource();
    }

    //其他数据源的事务管理器
    @Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DruidDataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
        sessionFactoryBean.setTypeAliasesPackage("com.example.druid.multisource.model");
        /*这里要在启动类上，把@SpringBootApplication改为@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
        *就是排除spirngboot自动配置分页插件,不然会报错有多个分页配置，文章上没有说，我百度之后，再看源码才知道得到
        * 也可以直接在application.properties里配置全局的pagehlper
        */
        // @SpringBootApplication(exclude = PageHelperAutoConfiguration.class)

        //分页插件 可以百度一下PageInterceptor
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);
        sessionFactoryBean.setPlugins(new Interceptor[] {interceptor});

        return sessionFactoryBean.getObject();
    }
}
