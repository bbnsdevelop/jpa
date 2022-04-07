package br.com.bbnsdevelop.databases.jdbc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	
	private String name; 
	private String location; 
	private Date birthDate;

}
