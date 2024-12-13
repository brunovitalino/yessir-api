package lol.bvlabs.yessir.domain.cardapio;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lol.bvlabs.yessir.domain.cardapio.icone.DadosListagemCardapioTipo;
import lol.bvlabs.yessir.domain.cardapio.tipo.DadosListagemCardapioIcone;

public record DadosListagemCardapio(
		@NotNull
		Long id,
		String nome,
		BigDecimal preco,
		DadosListagemCardapioTipo cardapioTipo,
		DadosListagemCardapioIcone cardapioIcone
) {
	
	public DadosListagemCardapio(Cardapio cardapio) {
		this(
			cardapio.getId(),
			cardapio.getNome(),
			cardapio.getPreco(),
			cardapio.getCardapioTipo() == null ? null : new DadosListagemCardapioTipo(cardapio.getCardapioTipo()),
			cardapio.getCardapioIcone() == null ? null : new DadosListagemCardapioIcone(cardapio.getCardapioIcone())
		);
	}
}
