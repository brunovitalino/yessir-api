package lol.bvlabs.yessir.module.garcom.domain.cardapio.tipo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.Cardapio;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cardapio_icone")
@Entity(name = "CardapioIcone")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CardapioIcone {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo;
	
	@OneToMany(mappedBy = "cardapioIcone")
	private List<Cardapio> cardapios;


	public CardapioIcone(Long id) {
		this.id = id;
	}

	public CardapioIcone(DadosCadastroCardapioIcone dadosCadastroCardapio) {
		this.nome = dadosCadastroCardapio.nome();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoCardapioIcone dadosAtualizacaoCardapio) {
		if (dadosAtualizacaoCardapio.nome() != null) {
			this.nome = dadosAtualizacaoCardapio.nome();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
