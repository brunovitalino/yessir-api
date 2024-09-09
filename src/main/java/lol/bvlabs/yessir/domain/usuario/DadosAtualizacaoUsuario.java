package lol.bvlabs.yessir.domain.usuario;

import java.util.List;

import lol.bvlabs.yessir.module.garcom.domain.role.Role;

public record DadosAtualizacaoUsuario(
	Long id,
	String nome,
	String email,
	String senha,
	String novaSenha,
	List<Role> roles,
	Boolean ativo
) {}
