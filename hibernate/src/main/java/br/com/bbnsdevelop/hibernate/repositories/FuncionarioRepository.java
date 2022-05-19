package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.heranca.Funcionario;

@Repository
public class FuncionarioRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional
	public void inserir(Funcionario funcionario) {
		entityManager.persist(funcionario);		
	}

	
	public List<Funcionario> getAll(){
		return entityManager.createQuery("select f from Funcionario", Funcionario.class).getResultList();
	}
}
