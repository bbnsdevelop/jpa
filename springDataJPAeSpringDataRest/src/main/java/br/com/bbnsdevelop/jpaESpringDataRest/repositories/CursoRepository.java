package br.com.bbnsdevelop.jpaESpringDataRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.jpaESpringDataRest.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	public List<Curso> findByNome(String nome);

	

}
