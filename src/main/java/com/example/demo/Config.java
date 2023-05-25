package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.database.DBConfig;

@Configuration
@Import({DBConfig.class})
@ComponentScan(basePackages = {
		"com.example",
		"com.example.database",
})
@EnableTransactionManagement
public class Config {}
