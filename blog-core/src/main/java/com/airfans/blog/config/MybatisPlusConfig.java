package com.airfans.blog.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;

@EnableTransactionManagement
@Configuration
@PropertySource("classpath:config/mybatis.properties")
@MapperScan("com.airfans.blog.dao.*")
public class MybatisPlusConfig {

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis-plus.global-config.id-type}")
    private Integer idType;
    @Value("${mybatis-plus.global-config.field-strategy}")
    private int fieldStrategy;
    @Value("${mybatis-plus.global-config.capital-mode}")
    private boolean isCapitalMode;
    @Value("${mybatis-plus.type-aliases-package}")
    private String typeAliasesPackage;
    @Value("${mybatis-plus.global-config.refresh-mapper}")
    private boolean refreshMapper;

    @Bean(name = "globalConfig")
    public GlobalConfiguration globalConfig() {
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setFieldStrategy(fieldStrategy);
        globalConfig.setIdType(idType);
        globalConfig.setCapitalMode(isCapitalMode);
        return globalConfig;
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory getMasterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource,
            @Qualifier("globalConfig") GlobalConfiguration globalConfig,
            @Qualifier("paginationInterceptor") PaginationInterceptor paginationInterceptor) throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // 配置数据源和全局变量
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        // 别名扫描包
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        // 分页插件
        Interceptor[] plugins = { paginationInterceptor };
        sqlSessionFactoryBean.setPlugins(plugins);

        // 配置mapper文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory getClusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource,
            @Qualifier("globalConfig") GlobalConfiguration globalConfig,
            @Qualifier("paginationInterceptor") PaginationInterceptor paginationInterceptor) throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // 配置数据源和全局变量
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        // 别名扫描包
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        // 分页插件
        Interceptor[] plugins = { paginationInterceptor };
        sqlSessionFactoryBean.setPlugins(plugins);

        // 配置mapper文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 分页插件
     */
    @Bean(name = "paginationInterceptor")
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
