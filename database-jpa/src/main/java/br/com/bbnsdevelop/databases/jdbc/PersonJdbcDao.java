package br.com.bbnsdevelop.databases.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.databases.jdbc.dto.Person;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class PersonJdbcDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Person> findAll(){
		String selectAllPerson = querySelectAllPerson();
		log.info("buscando todos as pessoas: {} ", selectAllPerson);
		return jdbcTemplate.query(selectAllPerson, new BeanPropertyRowMapper<Person>(Person.class));		
	}

	private String querySelectAllPerson() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person");
		
		return sb.toString();
	}

}
