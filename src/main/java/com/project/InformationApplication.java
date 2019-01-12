package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class InformationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InformationApplication.class, args);
	}

	/**
	 *  多个数据源 要  extends SpringBootServletInitializer
	 *  固定格式:
	 *  @Override
	 *	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	 *      return application.sources(运行类.class);
	 *  }
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InformationApplication.class);
	}

	/**
	 * SpringBootFilter 类中有 jdbcTemplate , Filter , 转向
	 */
	/*@Bean//过滤器
	public WebMvcConfig sitemeshFilter() {
		return new WebMvcConfig();
	}*/
}
