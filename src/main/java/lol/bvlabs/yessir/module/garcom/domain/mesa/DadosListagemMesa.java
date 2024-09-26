package lol.bvlabs.yessir.module.garcom.domain.mesa;

public record DadosListagemMesa(
		Long id,
		String nome,
		Boolean ativo
) {
	
	public DadosListagemMesa(Mesa mesa) {
		this(mesa.getId(), mesa.getNome(), mesa.getAtivo());
	}
}
