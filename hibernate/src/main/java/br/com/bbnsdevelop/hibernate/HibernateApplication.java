package br.com.bbnsdevelop.hibernate;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bbnsdevelop.hibernate.entities.Curso;
import br.com.bbnsdevelop.hibernate.repositories.CursoRepository;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

	@Autowired
	private CursoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Curso> cursos = List.of(new Curso(null, "Java", LocalDateTime.now(), null),
				new Curso(null, "Spring boot", LocalDateTime.now(), null),
				new Curso(null, "Python", LocalDateTime.now(), null));

		repository.save(cursos.get(0));
		repository.save(cursos.get(1));
		repository.save(cursos.get(2));

	}

}
