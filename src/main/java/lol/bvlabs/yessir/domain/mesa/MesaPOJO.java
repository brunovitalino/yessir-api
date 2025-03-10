package lol.bvlabs.yessir.domain.mesa;

public record MesaPOJO(
		Long id,
		String nome
) {
	public MesaPOJO(Mesa mesa) {
		this(mesa.getId(), mesa.getNome());
	}
}
