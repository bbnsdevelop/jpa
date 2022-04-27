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
@Table(name = "TB_PASSAPORTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passaporte {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="numero", nullable = false, length = 15)
	private String numero;
	
	@OneToOne
	private Estudante estudante;

}
