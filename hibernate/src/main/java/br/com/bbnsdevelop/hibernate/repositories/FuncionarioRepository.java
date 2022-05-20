package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.dto.FuncionarioDto;
import br.com.bbnsdevelop.hibernate.entities.heranca.Funcionario;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class FuncionarioRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional
	public void inserir(Funcionario funcionario) {
		entityManager.persist(funcionario);		
	}

	
	public List<FuncionarioDto> getAll(){
		log.info("buscando todos os funcionários ");
		return jdbcTemplate.query(queryGetFuncionarioUnion() , new BeanPropertyRowMapper<FuncionarioDto>(FuncionarioDto.class));	
	}
	
	private String queryGetFuncionarioUnion() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT f.id id, f.nome nome, f.salarioPorHora salarioPorHora, f.salarioIntegral salarioIntegral, f.tipo ");
		sb.append("FROM ( ");
		sb.append("SELECT id, nome, SALARIO_POR_HORA salarioPorHora, null as salarioIntegral, 1 as tipo ");
		sb.append("FROM FUNCIONARIO_MEIO_PERIODO ");
		sb.append("UNION ALL ");
		sb.append("SELECT id, nome, null as salarioPorHora, SALARIO_INTEGRAL salarioIntegral, 2 as tipo ");
		sb.append("FROM FUNCIONARIO_PERIODO_INTEGRAL ");
		sb.append(") f");
		
		return sb.toString();
		
	}
}
