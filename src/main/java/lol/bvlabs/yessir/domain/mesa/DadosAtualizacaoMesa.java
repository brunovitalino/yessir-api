package lol.bvlabs.yessir.domain.mesa;

public record DadosAtualizacaoMesa(
	Long id,
	String nome
) {
	
	public DadosAtualizacaoMesa(Mesa mesa) {
		this(mesa.getId(), mesa.getNome());
	}

}
