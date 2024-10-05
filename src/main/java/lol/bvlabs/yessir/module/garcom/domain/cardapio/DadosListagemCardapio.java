package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import java.math.BigDecimal;

import lol.bvlabs.yessir.module.garcom.domain.cardapio.icone.DadosListagemCardapioTipo;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.tipo.DadosListagemCardapioIcone;

public record DadosListagemCardapio(
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
