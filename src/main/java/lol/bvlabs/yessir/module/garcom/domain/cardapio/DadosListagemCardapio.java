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
		this(cardapio.getId(), cardapio.getNome(), cardapio.getPreco(), new DadosListagemCardapioTipo(cardapio.getCardapioTipo()), new DadosListagemCardapioIcone(cardapio.getCardapioIcone()));
	}
}
