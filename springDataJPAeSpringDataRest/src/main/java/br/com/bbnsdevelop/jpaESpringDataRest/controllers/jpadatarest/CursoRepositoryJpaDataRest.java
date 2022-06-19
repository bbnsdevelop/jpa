package br.com.bbnsdevelop.jpaESpringDataRest.controllers.jpadatarest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.bbnsdevelop.jpaESpringDataRest.entities.Curso;

@RepositoryRestResource(path = "cursos")
public interface CursoRepositoryJpaDataRest extends JpaRepository<Curso, Long> {

	public List<Curso> findByNome(String nome);

	

}
