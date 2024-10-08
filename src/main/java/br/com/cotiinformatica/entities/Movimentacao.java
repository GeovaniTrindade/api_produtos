package br.com.cotiinformatica.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movimentacao")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmovimentacao")
	private Integer idMovimentacao;

	@Column(name = "tipo", nullable = false)
	private Integer tipo;

	@Column(name = "observacoes", length = 500, nullable = false)
	private String observacoes;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datamovimentacao", nullable = false)
	private Date dataMovimentacao;

	@ManyToOne // Muitas movimentações para 1 Produto
	@JoinColumn(name = "idproduto") // chave estrangeira
	private Produto produto; // composição
}
