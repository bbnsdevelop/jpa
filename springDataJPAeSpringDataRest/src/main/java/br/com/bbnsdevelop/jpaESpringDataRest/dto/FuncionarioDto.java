package br.com.bbnsdevelop.jpaESpringDataRest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioDto {
	
	
	private Long id;
	
	private String nome;
	
	private BigDecimal salarioIntegral;
	
	private BigDecimal salarioPorHora;
	
	private Integer tipo;
	

}
