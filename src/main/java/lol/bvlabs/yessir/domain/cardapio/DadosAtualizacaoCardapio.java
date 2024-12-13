package lol.bvlabs.yessir.domain.cardapio;

import java.math.BigDecimal;

public record DadosAtualizacaoCardapio(
	Long id,
	String nome,
	BigDecimal preco
) {}
