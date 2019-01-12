package com.project.tools;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/11/30.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor())//要拦截的请求
                .addPathPatterns("/project/**")
                .addPathPatterns("/bg/**")
                .addPathPatterns("/user/**")
                .addPathPatterns("/out/**")
                 /*.addPathPatterns("/allot*")
                .addPathPatterns("/usergroup*")
                .addPathPatterns("/ac*")
                .addPathPatterns("/ap*")
                .addPathPatterns("/userrole*")
                .addPathPatterns("/ssid*")
                .addPathPatterns("/dictionary*")
                .addPathPatterns("/map*")
                .addPathPatterns("/log_info*")
                .addPathPatterns("/t_auth_log*")
                .addPathPatterns("/online_auth_log*")
                .addPathPatterns("/LogAction*")
                .addPathPatterns("/main*")
                .addPathPatterns("/Auth_Log*")
                .addPathPatterns("/tactics*")
                .addPathPatterns("/group*")
                .addPathPatterns("/food*")
                .addPathPatterns("/foodtype*")
                .addPathPatterns("/foodmenu*")
                .addPathPatterns("/namelist*")
                .addPathPatterns("/advert*")
                .addPathPatterns("/advertone*")
                .addPathPatterns("/advertsubmit*")
                .addPathPatterns("/localnet*")
                .addPathPatterns("/adminpz*")
                .addPathPatterns("/gdadvert*")
                .addPathPatterns("/gdadminpz*")
                .addPathPatterns("/nofeel*")
                .addPathPatterns("/question*")
                .addPathPatterns("/apstatus*")
                .addPathPatterns("/statistics*")
                .addPathPatterns("/advertbefore*")
                .addPathPatterns("/advertmiddle*")
                .addPathPatterns("/advertafter*")
                .addPathPatterns("/queryCount*")
                .addPathPatterns("/radacct*")
                .addPathPatterns("/wechat*")*/

                .excludePathPatterns("/").excludePathPatterns("/sys/index").excludePathPatterns("/main/index").excludePathPatterns("/sys/login").excludePathPatterns("/sys/nologin");//不拦截的请求
        super.addInterceptors(registry);

    }

    /**
     * 转向
     * @param registry
     */
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/sys/index" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

    // 多数据源才开启
    /*@Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "primaryNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate primaryNamedParameterJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate secondaryNamedParameterJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }*/
}
