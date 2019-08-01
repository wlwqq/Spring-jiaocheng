package com.wanglei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = "com.wanglei")
@PropertySource("classpath:jdbcConfig.properties")
@Import(JdbcConfiguration.class)
public class SpringConfiguration {


}
