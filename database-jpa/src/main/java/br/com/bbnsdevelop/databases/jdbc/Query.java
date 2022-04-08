package br.com.bbnsdevelop.databases.jdbc;

import org.springframework.stereotype.Component;

@Component
public class Query {

	
	
	protected String selectPersonById() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person WHERE id = :id");
		
		return sb.toString();
	}
	
	protected String deletePersonById() {
		StringBuilder sb = new StringBuilder();
		sb.append("delete ");		
		sb.append("from person WHERE id = :id");
		
		return sb.toString();
	}
	
	protected String selectPersonByLikeName() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person WHERE name like :name");
		
		return sb.toString();
	}
	
	protected String selectPersonByName() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person WHERE name = :name");
		
		return sb.toString();
	}

	protected String selectAllPerson() {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("id, name, location, birth_date as birthDate ");
		sb.append("from person");
		
		return sb.toString();
	}
	
	protected String insertPerson() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append("person(name, location, birth_date) ");
		sb.append("values (:name, :location, :birthDate) ");
		
		return sb.toString();
	}
	
	protected String updatePerson() {
		StringBuilder sb = new StringBuilder();
		sb.append("update person ");
		sb.append("set location = :location ");
		sb.append("where id = :id");
		
		return sb.toString();
	}

}