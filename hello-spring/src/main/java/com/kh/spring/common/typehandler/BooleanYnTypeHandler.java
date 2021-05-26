package com.kh.spring.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

//mapper에서 TypeHandler를 연결안할시 어노테이션 해줘야한다.
@MappedTypes(Boolean.class)
@MappedJdbcTypes(JdbcType.CHAR) 
public class BooleanYnTypeHandler extends BaseTypeHandler<Boolean> {
	
	
	/**
	 * boolean ------> YN(char)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter ? "Y" : "N");
	}

	/**
	 * boolean <------ YN(char)
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return "Y".equals(rs.getString(columnName));
	}

	/**
	 * boolean <------ YN(char)
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return "Y".equals(rs.getString(columnIndex));
	}

	/**
	 * boolean <------ YN(char)
	 */
	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return "Y".equals(cs.getString(columnIndex));
	}

}
