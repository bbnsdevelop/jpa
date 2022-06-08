package br.com.bbnsdevelop.jpaESpringDataRest.entities.heranca;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuncionarioPeriodoIntegral extends Funcionario{
	
	
	private BigDecimal salarioIntegral;
	
	public FuncionarioPeriodoIntegral(String nome, BigDecimal salarioIntegral) {
		super(nome);
		this.salarioIntegral = salarioIntegral;
	}

}
