package lol.bvlabs.yessir.domain.usuario;

public record DadosAtualizacaoUsuario(
	Long id,
	String nome,
	String email,
	String senha,
	String novaSenha,
	Boolean ativo
) {}
