package br.com.bbnsdevelop.jpaESpringDataRest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoDto {
	
	private Long id;
	private Date dataCriacao;
	private String nome;
	private Date dataAtualizacao;
	private Integer qtdAlunos;
	
}
