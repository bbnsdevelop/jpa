package br.com.bbnsdevelop.jpaESpringDataRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.jpaESpringDataRest.entities.Passaporte;

@Repository
public interface PassaporteRepository extends JpaRepository<Passaporte, Long>{
	
}
