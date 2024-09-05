package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import java.math.BigDecimal;

public record DadosListagemCardapio(
		Long id,
		String nome,
		BigDecimal preco,
		Boolean ativo
) {
	
	public DadosListagemCardapio(Cardapio cardapio) {
		this(cardapio.getId(), cardapio.getNome(), cardapio.getPreco(), cardapio.getAtivo());
	}
}
