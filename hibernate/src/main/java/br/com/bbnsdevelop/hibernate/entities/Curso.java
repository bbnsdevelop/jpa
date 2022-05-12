package br.com.bbnsdevelop.hibernate.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CURSO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = { 
		@NamedQuery(name = "query_get_all_cursos", query = "select c from Curso c"),
		@NamedQuery(name = "query_get_curso_by_id", query = "select c from Curso c where c.id = :id") 
		})
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@CreationTimestamp
	@Column(name = "dh_criacao", nullable = false)
	private LocalDateTime dataCriacao;

	@UpdateTimestamp
	@Column(name = "dh_atualizacao", nullable = true)
	private LocalDateTime ultimaAtualizacao;
	
	@OneToMany(mappedBy = "curso")
	private List<Review> reviews;
	
	@ManyToMany
	@JoinTable(name = "TB_CURSO_ESTUDANTE",
				joinColumns = @JoinColumn(name="CURSO_ID"),
				inverseJoinColumns = @JoinColumn(name="ESTUDANTE_ID")
			)
	private List<Estudante> estudantes  = new ArrayList<>();
	
	public void addEstudante(Estudante estudante) {
		if(estudantes == null) {
			estudantes = new ArrayList<>();
		}
		estudantes.add(estudante);
	}

}
