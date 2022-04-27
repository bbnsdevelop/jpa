package br.com.bbnsdevelop.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ESTUDANTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudante {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="nome", nullable = false, length = 70)
	private String nome;
	
	@OneToOne(mappedBy = "estudante")
	private Passaporte passaporte;

}
