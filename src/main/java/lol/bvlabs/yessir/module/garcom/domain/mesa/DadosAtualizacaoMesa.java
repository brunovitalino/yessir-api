package lol.bvlabs.yessir.module.garcom.domain.mesa;

import java.math.BigDecimal;

public record DadosAtualizacaoMesa(
	Long id,
	String nome,
	BigDecimal preco
) {}
