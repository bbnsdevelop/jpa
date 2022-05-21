package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.Curso;

@Repository
public class CursoRepository {
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	public Curso findById(Long id) {
		//return entityManager.find(Curso.class, id);

		TypedQuery<Curso> namedQuery = entityManager.createNamedQuery("query_get_curso_by_id", Curso.class);
		namedQuery.setParameter("id", id);
		return namedQuery.getSingleResult();
	}
	
	public List<Curso> findAll() {
		TypedQuery<Curso> query = entityManager.createNamedQuery("query_get_all_cursos", Curso.class);
		return query.getResultList();
	}
	
	public List<Curso> findCursosSemEstudante() {
		TypedQuery<Curso> query = entityManager.createQuery("select c from Curso c where c.estudantes is empty", Curso.class);
		return query.getResultList();
	}
	
	
	@Transactional
	public Curso save(Curso curso) {
		
		if(curso.getId() == null) {
			entityManager.persist(curso);
		}else {
			entityManager.merge(curso);
		}		
		return curso;
	}
	
	public void deleteById(Long id) {
		Curso curso = this.findById(id);
		if(curso != null){
			entityManager.remove(curso);			
		}else {
			throw new IllegalArgumentException("Curso não encontrado");
		}
	}

}
