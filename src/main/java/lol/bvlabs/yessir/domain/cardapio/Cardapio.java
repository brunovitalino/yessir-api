package lol.bvlabs.yessir.domain.cardapio;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.domain.cardapio.icone.CardapioTipo;
import lol.bvlabs.yessir.domain.cardapio.tipo.CardapioIcone;
import lol.bvlabs.yessir.domain.estabelecimento.Estabelecimento;
import lol.bvlabs.yessir.domain.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cardapios")
@Entity(name = "Cardapio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cardapio {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal preco;
	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "estabelecimento_id", nullable = false)
	private Estabelecimento estabelecimento;

	@ManyToOne
	@JoinColumn(name = "cardapio_tipo_id", nullable = false)
	private CardapioTipo cardapioTipo;

	@ManyToOne
	@JoinColumn(name = "cardapio_icone_id", nullable = false)
	private CardapioIcone cardapioIcone;
	
	@OneToMany(mappedBy = "cardapio")
	private List<Pedido> pedidos;


	public Cardapio(Long id) {
		this.id = id;
	}

	public Cardapio(DadosCadastroCardapio dadosCadastroCardapio) {
		this.nome = dadosCadastroCardapio.nome();
		this.preco = dadosCadastroCardapio.preco();
		this.ativo = true;
		this.estabelecimento = new Estabelecimento(dadosCadastroCardapio.estabelecimento_id());
	}

	public void atualizarInformacoes(DadosAtualizacaoCardapio dadosAtualizacaoCardapio) {
		if (dadosAtualizacaoCardapio.nome() != null) {
			this.nome = dadosAtualizacaoCardapio.nome();
		}
		if (dadosAtualizacaoCardapio.preco() != null) {
			this.preco = dadosAtualizacaoCardapio.preco();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
