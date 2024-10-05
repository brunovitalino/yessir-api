package lol.bvlabs.yessir.module.garcom.domain.pedido;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.Atendimento;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.Cardapio;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "atendimento_id", nullable = false)
	private Atendimento atendimento;
	
	@ManyToOne
	@JoinColumn(name = "cardapio_id", nullable = false)
	private Cardapio cardapio;
	
	private Integer quantidade;

	private Boolean ativo;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;

	public Pedido(DadosCadastroPedido dadosCadastroPedido) {
		this.atendimento = new Atendimento(dadosCadastroPedido.atendimento().id());
 		this.cardapio = new Cardapio(dadosCadastroPedido.cardapio().id());
		this.quantidade = dadosCadastroPedido.quantidade();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoPedido dadosAtualizacaoPedido) {
		if (dadosAtualizacaoPedido.atendimento() != null && dadosAtualizacaoPedido.atendimento().id() != null) {
			this.atendimento = new Atendimento(dadosAtualizacaoPedido.atendimento().id());
		}
		if (dadosAtualizacaoPedido.cardapio() != null && dadosAtualizacaoPedido.cardapio().id() != null) {
			this.cardapio = new Cardapio(dadosAtualizacaoPedido.cardapio().id());
		}
		if (dadosAtualizacaoPedido.quantidade() != null) {
			this.quantidade = dadosAtualizacaoPedido.quantidade();
		}
	}

	public void excluir() {
		this.ativo = false;
	}
	
}
