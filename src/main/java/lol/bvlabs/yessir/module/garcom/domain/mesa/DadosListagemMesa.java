package lol.bvlabs.yessir.module.garcom.domain.mesa;

public record DadosListagemMesa(
		Long id,
		String nome
) {
	
	public DadosListagemMesa(Mesa mesa) {
		this(mesa.getId(), mesa.getNome());
	}
}
