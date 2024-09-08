package lol.bvlabs.yessir.domain.usuario;

public record DadosListagemUsuario(
		Long id,
		String nome,
		String email,
		Boolean ativo
) {
	
	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getAtivo());
	}
}
