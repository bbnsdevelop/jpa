package br.com.bbnsdevelop.hibernate.entities.heranca;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuncionarioMeioPeriodo extends Funcionario{
	
	
	private BigDecimal salarioPorHora;
	
	public FuncionarioMeioPeriodo(String nome, BigDecimal salarioPorHora) {
		super(nome);
		this.salarioPorHora = salarioPorHora;
	}

}
