package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.Estudante;

@Repository
public class EstudanteRepository {
	
	

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
		
		if(estudante.getId() == null) {
			entityManager.persist(estudante);
		}else {
			entityManager.merge(estudante);
		}		
		return estudante;
	}
	
	public void deleteById(Long id) {
		Estudante estudante = this.findById(id);
		if(estudante != null){
			if(estudante.getPassaporte() != null) {
				entityManager.remove(estudante.getPassaporte());				
			}
			entityManager.remove(estudante);			
		}else {
			throw new IllegalArgumentException("Estudante não encontrado");
		}
	}

}
