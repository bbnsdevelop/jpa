package br.com.bbnsdevelop.databases.jdbc.rowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bbnsdevelop.databases.jdbc.dto.Person;

public class PersonRowMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person p = new Person();
		p.setId(rs.getLong("id"));
		p.setName(rs.getString("name"));
		p.setLocation(rs.getString("location"));
		p.setBirthDate(rs.getTimestamp("birth_date"));
		return p;
	}

}
