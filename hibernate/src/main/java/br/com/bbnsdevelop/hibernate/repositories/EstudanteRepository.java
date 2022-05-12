package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.Curso;
import br.com.bbnsdevelop.hibernate.entities.Estudante;

@Repository
public class EstudanteRepository {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private EntityManager entityManager;

	public Estudante findById(Long id) {
		return entityManager.find(Estudante.class, id);
	}

	public List<Estudante> findAll() {
		TypedQuery<Estudante> query = entityManager.createQuery("Select e From Estudante e", Estudante.class);
		return query.getResultList();
	}

	@Transactional
	public Estudante save(Estudante estudante) {

		if (estudante.getId() == null) {
			entityManager.persist(estudante);
		} else {
			entityManager.merge(estudante);
		}
		return estudante;
	}

	public void deleteById(Long id) {
		Estudante estudante = this.findById(id);
		if (estudante != null) {
			if (estudante.getPassaporte() != null) {
				entityManager.remove(estudante.getPassaporte());
			}
			entityManager.remove(estudante);
		} else {
			throw new IllegalArgumentException("Estudante não encontrado");
		}
	}

	public void inserirEstudanteAoCurso(Long estudanteId, Long cursoId) {
		Estudante estudante = this.findById(estudanteId);
		Curso curso = cursoRepository.findById(cursoId);
		if (curso != null && estudante != null) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("cursoId", curso.getId());
			param.addValue("estutandeId", estudante.getId());
			namedParameterJdbcTemplate.update(insertEstutandeNoCurso(), param);
		}
	}

	protected String insertEstutandeNoCurso() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ");
		sb.append("TB_CURSO_ESTUDANTE(CURSO_ID, ESTUDANTE_ID) ");
		sb.append("values (:cursoId, :estutandeId) ");

		return sb.toString();
	}

}
