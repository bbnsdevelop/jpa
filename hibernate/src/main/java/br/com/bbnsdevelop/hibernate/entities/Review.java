package br.com.bbnsdevelop.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_REVIEW")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="descricao", nullable = false, length = 150)
	private String descricao;
	
	@Column(name ="avaliacao", nullable = false)
	private Integer avaliacao;
	
	@ManyToOne
	private Curso curso;

}
