package br.com.bbnsdevelop.hibernate.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_CURSO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name ="query_get_all_cursos", query = "select c from Curso c")
@NamedQuery(name ="query_get_curso_by_id", query = "select c from Curso c where c.id = :id")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="nome", nullable = false, length = 50)
	private String nome;
	
	@CreationTimestamp
	@Column(name = "dh_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@UpdateTimestamp
	@Column(name = "dh_atualizacao", nullable = true)
	private LocalDateTime ultimaAtualizacao;
	

}
