package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.Estabelecimento;
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

	public Cardapio(DadosCadastroCardapio dadosCadastroCardapio) {
		this.ativo = true;
		this.estabelecimento = new Estabelecimento(dadosCadastroCardapio.estabelecimento_id());
		this.nome = dadosCadastroCardapio.nome();
	}

	public void atualizarInformacoes(DadosAtualizacaoCardapio sadosAtualizacaoCardapio) {
		if (sadosAtualizacaoCardapio.nome() != null) {
			this.nome = sadosAtualizacaoCardapio.nome();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
