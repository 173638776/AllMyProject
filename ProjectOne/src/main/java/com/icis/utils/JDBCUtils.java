package com.icis.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Properties;

public class JDBCUtils {
	private static JdbcTemplate jdbcTemplate;
	static {
		Properties properties=new Properties();
		try {
			properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
			jdbcTemplate=new JdbcTemplate(new DruidDataSourceFactory().createDataSource(properties));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JdbcTemplate  getJDBCTemplate() {
		return jdbcTemplate;
	}
}