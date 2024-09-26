package lol.bvlabs.yessir.module.garcom.domain.mesa;

import java.math.BigDecimal;

public record DadosCadastroMesa(
		Long estabelecimento_id,
		String nome,
		BigDecimal preco
) {}
