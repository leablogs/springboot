package com.leablogs.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.leablogs.enumeration.SexEnum;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = SexEnum.class)
public class SexType extends BaseTypeHandler<SexEnum> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getId());
	}

	@Override
	public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int sex = rs.getInt(columnName);
		if (sex != 0 && sex != 1) {
			return null;
		}
		return SexEnum.getEnumById(sex);
	}

	@Override
	public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int sex = rs.getInt(columnIndex);
		if (sex != 0 && sex != 1) {
			return null;
		}
		return SexEnum.getEnumById(sex);
	}

	@Override
	public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int sex = cs.getInt(columnIndex);
		if (sex != 0 && sex != 1) {
			return null;
		}
		return SexEnum.getEnumById(sex);
	}

}
