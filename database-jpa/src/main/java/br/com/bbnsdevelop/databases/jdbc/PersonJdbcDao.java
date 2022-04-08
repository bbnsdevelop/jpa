package br.com.bbnsdevelop.databases.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.databases.jdbc.dto.Person;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class PersonJdbcDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Person> findAll(){
		String selectAllPerson = querySelectAllPerson();
		log.info("buscando todos as pessoas: {} ", selectAllPerson);
		return jdbcTemplate.query(selectAllPerson, new BeanPropertyRowMapper<Person>(Person.class));		
	}

	public Person findById(Long id) {
		String selectPersonById = querySelectPersonById();
		log.info("buscando pessoa: {} ", selectPersonById);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		return namedParameterJdbcTemplate.queryForObject(selectPersonById, param, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public List<Person> findByName(String name) {
		String selectPersonByName = querySelectPersonByName();
		log.info("buscando pessoa: {} ", selectPersonByName);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", "%".concat(name).concat("%"));
		
		return namedParameterJdbcTemplate.query(selectPersonByName, param, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	
	public void deleteById(Long id) {
		String deletePersonById = queryDeletePersonById();
		log.info("deletando pessoa: {} ", deletePersonById);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);		
		namedParameterJdbcTemplate.update(deletePersonById, param);
		
	}
	
	
	private String querySelectPersonById() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person WHERE id = :id");
		
		return sb.toString();
	}
	
	private String queryDeletePersonById() {
		StringBuilder sb = new StringBuilder();
		sb.append("delete ");		
		sb.append("from person WHERE id = :id");
		
		return sb.toString();
	}
	
	private String querySelectPersonByName() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person WHERE name like :name");
		
		return sb.toString();
	}

	private String querySelectAllPerson() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person");
		
		return sb.toString();
	}


}
