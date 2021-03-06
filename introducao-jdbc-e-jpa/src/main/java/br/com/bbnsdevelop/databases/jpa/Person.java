package br.com.bbnsdevelop.databases.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "find_all_person", query = "select p from Person p")
@NamedQuery(name = "find_by_id_person", query = "select p from Person p where p.id = :id")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name; 
		
	@Column(name = "location", nullable = false, length = 100)
	private String location; 
	
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	
	

}
