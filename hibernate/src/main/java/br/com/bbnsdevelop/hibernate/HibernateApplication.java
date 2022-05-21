package br.com.bbnsdevelop.hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bbnsdevelop.hibernate.entities.Curso;
import br.com.bbnsdevelop.hibernate.entities.Estudante;
import br.com.bbnsdevelop.hibernate.entities.Passaporte;
import br.com.bbnsdevelop.hibernate.entities.Review;
import br.com.bbnsdevelop.hibernate.entities.heranca.FuncionarioMeioPeriodo;
import br.com.bbnsdevelop.hibernate.entities.heranca.FuncionarioPeriodoIntegral;
import br.com.bbnsdevelop.hibernate.repositories.CursoRepository;
import br.com.bbnsdevelop.hibernate.repositories.EstudanteRepository;
import br.com.bbnsdevelop.hibernate.repositories.FuncionarioRepository;
import br.com.bbnsdevelop.hibernate.repositories.PassaporteRepository;
import br.com.bbnsdevelop.hibernate.repositories.ReviewRepository;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

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
		SpringApplication.run(HibernateApplication.class, args);
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
		Passaporte p = new Passaporte(null, "ZR154877", s);

		estudanteRepository.save(s);
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
		
		estudanteRepository.inserirEstudanteAoCurso(s.getId(), cursos.get(0).getId());
		estudanteRepository.inserirEstudanteAoCurso(s.getId(), cursos.get(1).getId());
		
		funcionarioRepository.inserir(new FuncionarioPeriodoIntegral("Snow", new BigDecimal("4000")));
		
		funcionarioRepository.inserir(new FuncionarioMeioPeriodo("Jhon", new BigDecimal("40")));
	}

}
