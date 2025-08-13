package org.arthurandrade.samsungfakestoreapi.config;

import org.hibernate.dialect.PostgreSQLDialect;

public class MyPostgresDialect extends PostgreSQLDialect {

	@Override
	public String getCheckCondition(String columnName, String[] values) {
		// We do not want enum database checks
		return null;
	}
}
