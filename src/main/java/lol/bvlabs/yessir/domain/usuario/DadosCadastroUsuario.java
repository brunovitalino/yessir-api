package lol.bvlabs.yessir.domain.usuario;

import java.util.List;

import lol.bvlabs.yessir.module.garcom.domain.role.Role;

public record DadosCadastroUsuario(
		String nome,
		String email,
		String senha,
		List<Role> roles
) {}
