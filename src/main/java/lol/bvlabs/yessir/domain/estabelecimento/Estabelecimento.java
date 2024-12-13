package lol.bvlabs.yessir.domain.estabelecimento;

import java.util.Set;

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

@Table(name = "estabelecimentos")
@Entity(name = "Estabelecimento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estabelecimento {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo;
	@OneToMany(mappedBy = "estabelecimento")
	private Set<Cardapio> cardapios;
	
	public Estabelecimento(Long id) {
		this.id = id;
	}

	public Estabelecimento(DadosCadastroEstabelecimento dadosEstabelecimento) {
		this.ativo = true;
		this.nome = dadosEstabelecimento.nome();
	}

	public void atualizarInformacoes(DadosAtualizacaoEstabelecimento dadosAtualizacaoEstabelecimento) {
		if (dadosAtualizacaoEstabelecimento.nome() != null) {
			this.nome = dadosAtualizacaoEstabelecimento.nome();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
