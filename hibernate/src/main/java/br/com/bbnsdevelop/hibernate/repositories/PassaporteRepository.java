package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.Passaporte;

@Repository
public class PassaporteRepository {
	

	@Autowired
	private EntityManager entityManager;
	
	
	public Passaporte findById(Long id) {
		return entityManager.find(Passaporte.class, id);
	}
	
	public List<Passaporte> findAll() {
		TypedQuery<Passaporte> query = entityManager.createQuery("Select p From Passaporte p", Passaporte.class);
		return query.getResultList();
	}
	
	
	@Transactional
	public Passaporte save(Passaporte passaporte) {
		
		if(passaporte.getId() == null) {
			entityManager.persist(passaporte);
		}else {
			entityManager.merge(passaporte);
		}		
		return passaporte;
	}
	
	public void deleteById(Long id) {
		Passaporte passaporte = this.findById(id);
		if(passaporte != null){
			entityManager.remove(passaporte);			
		}else {
			throw new IllegalArgumentException("Estudante não encontrado");
		}
	}

}
