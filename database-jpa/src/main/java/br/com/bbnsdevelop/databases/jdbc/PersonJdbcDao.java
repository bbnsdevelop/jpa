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
public class PersonJdbcDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Query query;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Person> findAll(){
		String selectAllPerson = query.selectAllPerson();
		log.info("buscando todos as pessoas: {} ", selectAllPerson);
		return jdbcTemplate.query(selectAllPerson, new BeanPropertyRowMapper<Person>(Person.class));		
	}

	public Person findById(Long id) {
		String selectPersonById = query.selectPersonById();
		log.info("buscando pessoa: {} ", selectPersonById);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		return namedParameterJdbcTemplate.queryForObject(selectPersonById, param, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person save(Person person) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		if(person.getId() == null) {
			String insert = query.insertPerson();
			log.info("inserindo uma pessoa: {} ", insert);
			param.addValue("name", person.getName());
			param.addValue("location", person.getLocation());
			param.addValue("birthDate", person.getBirthDate());
			namedParameterJdbcTemplate.update(insert, param);
			person = this.findByName(person.getName());
			
		}
		else {
			String update = query.updatePerson();
			log.info("atualizando uma pessoa: {} ", update);
			param.addValue("location", person.getLocation());
			param.addValue("id", person.getId());			
			namedParameterJdbcTemplate.update(update, param);
		}
		return person;
	}
	
	public List<Person> findByLikeName(String name) {
		String selectPersonByName = query.selectPersonByLikeName();
		log.info("buscando pessoa: {} ", selectPersonByName);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", "%".concat(name).concat("%"));
		
		return namedParameterJdbcTemplate.query(selectPersonByName, param, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	private Person findByName(String name) {
		String selectPersonByName = query.selectPersonByLikeName();
		log.info("buscando pessoa: {} ", selectPersonByName);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", name);
		
		return namedParameterJdbcTemplate.queryForObject(selectPersonByName, param, new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	
	public void deleteById(Long id) {
		String deletePersonById = query.deletePersonById();
		log.info("deletando pessoa: {} ", deletePersonById);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);		
		namedParameterJdbcTemplate.update(deletePersonById, param);
		
	}


}
