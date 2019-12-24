package kr.starbocks.api.dao;

import java.io.IOException;
import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class SqlMapFactory {
	private String config = "mybatis/config.xml";
	private SqlSessionFactory factory;

	public SqlMapFactory() {
		init();
	}

	private void init() {
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public SqlSession openSession(boolean autocommit) {
		return this.factory.openSession(autocommit);
	}
}