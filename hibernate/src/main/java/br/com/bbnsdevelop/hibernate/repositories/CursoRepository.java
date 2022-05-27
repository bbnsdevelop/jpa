package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.dto.CursoDto;
import br.com.bbnsdevelop.hibernate.entities.Curso;

@Repository
public class CursoRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CursoRepositoryCriteria cursoRepositoryCriteria;

	public Curso findById(Long id) {
		// return entityManager.find(Curso.class, id);

		TypedQuery<Curso> namedQuery = entityManager.createNamedQuery("query_get_curso_by_id", Curso.class);
		namedQuery.setParameter("id", id);
		return namedQuery.getSingleResult();
	}
	
	/*
	public List<Curso> findAll() {
		TypedQuery<Curso> query = entityManager.createNamedQuery("query_get_all_cursos", Curso.class);
		return query.getResultList();
	}*/
	
	public List<Curso> findAll() {		
		return cursoRepositoryCriteria.findAllCursosCriteria();
	}
	
	public List<Curso> findByNome(String nome) {		
		return cursoRepositoryCriteria.findCursosLikeNameCriteria(nome);
	}	
	

	public List<Curso> findCursosSemEstudante() {
		TypedQuery<Curso> query = entityManager.createQuery("select c from Curso c where c.estudantes is empty",
				Curso.class);
		return query.getResultList();
	}

	public List<CursoDto> findCursosComMaisEstudantes() {
		
		return jdbcTemplate.query(queryCursosAlunos() , new BeanPropertyRowMapper<CursoDto>(CursoDto.class));
	}

	@Transactional
	public Curso save(Curso curso) {

		if (curso.getId() == null) {
			entityManager.persist(curso);
		} else {
			entityManager.merge(curso);
		}
		return curso;
	}

	public void deleteById(Long id) {
		Curso curso = this.findById(id);
		if (curso != null) {
			entityManager.remove(curso);
		} else {
			throw new IllegalArgumentException("Curso não encontrado");
		}
	}

	private String queryCursosAlunos() {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT c.id, ");  
		sb.append("c.dh_criacao dataCriacao, c.nome nome, c.dh_atualizacao dataAtualizacao, ");
		sb.append("(select count(*) from TB_CURSO_ESTUDANTE ce where ce.curso_id = c.id ) qtdAlunos "); 
		sb.append("FROM TB_CURSO c ORDER BY qtdAlunos DESC");

		return sb.toString();

	}

}
