package lol.bvlabs.yessir.module.garcom.domain.role;

public record DadosListagemRole(
		Long id,
		String nome,
		Boolean ativo
) {
	
	public DadosListagemRole(Role role) {
		this(role.getId(), role.getNome(), role.getAtivo());
	}
}
