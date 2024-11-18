package lol.bvlabs.yessir.module.mesa.model;

import java.util.List;

import lol.bvlabs.yessir.module.garcom.domain.role.Role;

public record DadosCadastroUsuario(
		String nome,
		String email,
		String senha,
		List<Role> roles
) {}
