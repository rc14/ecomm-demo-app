package com.omnicuris.dataSource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.MysqlDataSource;



@Component
@Configuration

public class Connect {
	@Bean("mysql")
	public MysqlDataSource getmysql(){
	MysqlDataSource msds= new MysqlDataSource();
	msds.setUrl("jdbc:mysql://localhost:3306/ecomm");
	msds.setUser("app1");
	msds.setPassword("testproject");
	return msds;
	}

	
}
