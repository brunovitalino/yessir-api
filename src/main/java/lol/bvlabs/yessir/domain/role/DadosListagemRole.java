package lol.bvlabs.yessir.domain.role;

public record DadosListagemRole(
		Long id,
		String nome,
		Boolean ativo
) {
	
	public DadosListagemRole(Role role) {
		this(role.getId(), role.getNome(), role.getAtivo());
	}
}
