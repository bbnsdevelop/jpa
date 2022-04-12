package br.com.bbnsdevelop.databases.jpa.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bbnsdevelop.databases.jpa.Person;
import br.com.bbnsdevelop.databases.jpa.repositories.PersonEntityManager;
import br.com.bbnsdevelop.databases.jpa.repositories.PersonRepository;

@SpringBootTest
public class PersonServiceTest {
	
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepository personRepository;
	
	
	@Mock
	private PersonEntityManager personEntityManager;
	
	
	@Test
	public void findAllPersonTest() {
		List<Person> list = List.of(new Person(1L, "Jhon", "EUA", new Date()));
		when(service.findAll()).thenReturn(list);		
		assertFalse(list.isEmpty());
	}
	

}
