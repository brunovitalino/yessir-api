package lol.bvlabs.yessir.domain.cardapio.icone;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.domain.cardapio.Cardapio;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cardapio_tipo")
@Entity(name = "CardapioTipo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CardapioTipo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo;
	
	@OneToMany(mappedBy = "cardapioTipo")
	private List<Cardapio> cardapios;


	public CardapioTipo(Long id) {
		this.id = id;
	}

	public CardapioTipo(DadosCadastroCardapioTipo dadosCadastroCardapio) {
		this.nome = dadosCadastroCardapio.nome();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoCardapioTipo dadosAtualizacaoCardapio) {
		if (dadosAtualizacaoCardapio.nome() != null) {
			this.nome = dadosAtualizacaoCardapio.nome();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
