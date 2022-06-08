package br.com.bbnsdevelop.jpaESpringDataRest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.bbnsdevelop.jpaESpringDataRest.entities.Curso;
import br.com.bbnsdevelop.jpaESpringDataRest.entities.Estudante;
import br.com.bbnsdevelop.jpaESpringDataRest.entities.Passaporte;
import br.com.bbnsdevelop.jpaESpringDataRest.entities.Review;
import br.com.bbnsdevelop.jpaESpringDataRest.entities.heranca.FuncionarioMeioPeriodo;
import br.com.bbnsdevelop.jpaESpringDataRest.entities.heranca.FuncionarioPeriodoIntegral;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.CursoRepository;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.EstudanteRepository;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.FuncionarioRepository;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.PassaporteRepository;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.ReviewRepository;

@SpringBootApplication
public class JPADataRestApplication implements CommandLineRunner {

	@Autowired
	private CursoRepository repository;

	@Autowired
	private EstudanteRepository estudanteRepository;

	@Autowired
	private PassaporteRepository passaporteRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(JPADataRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Curso> cursos = List.of(new Curso(null, "Java", LocalDateTime.now(), null, null, null),
				new Curso(null, "Spring boot", LocalDateTime.now(), null, null, null),
				new Curso(null, "Python", LocalDateTime.now(), null, null, null),
				new Curso(null, "Java EE", LocalDateTime.now(), null, null, null));

		repository.save(cursos.get(0));
		repository.save(cursos.get(1));
		repository.save(cursos.get(2));
		repository.save(cursos.get(3));

		Estudante s = new Estudante(null, "Willian", null, null);
		Estudante s1 = new Estudante(null, "Jhon", null, null);
		Estudante s2 = new Estudante(null, "Bob", null, null);
		Estudante s3 = new Estudante(null, "Ana", null, null);

		cursos.get(0).addEstudante(s);
		cursos.get(1).addEstudante(s);
		
		cursos.get(0).addEstudante(s1);
		cursos.get(0).addEstudante(s2);
		cursos.get(1).addEstudante(s3);
		
		s.addCurso(cursos.get(0));
		s.addCurso(cursos.get(1));
		s1.addCurso(cursos.get(0));
		s2.addCurso(cursos.get(0));
		s3.addCurso(cursos.get(1));

		estudanteRepository.save(s);
		estudanteRepository.save(s1);
		estudanteRepository.save(s2);
		estudanteRepository.save(s3);

		Passaporte p = new Passaporte(null, "ZR154877", s);
		passaporteRepository.save(p);
		Review r = new Review();
		r.setAvaliacao(5);
		r.setDescricao("Curso muito bom");
		r.setCurso(cursos.get(0));
		reviewRepository.save(r);

		Review r1 = new Review();
		r1.setAvaliacao(4);
		r1.setDescricao("Excelente curso");
		r1.setCurso(cursos.get(0));
		reviewRepository.save(r1);

		funcionarioRepository.save(new FuncionarioPeriodoIntegral("Snow", new BigDecimal("4000")));

		funcionarioRepository.save(new FuncionarioMeioPeriodo("Jhon", new BigDecimal("40")));
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
