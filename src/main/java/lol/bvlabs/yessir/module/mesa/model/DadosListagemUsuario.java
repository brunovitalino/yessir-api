package lol.bvlabs.yessir.module.mesa.model;

import java.util.List;

import lol.bvlabs.yessir.module.garcom.domain.role.DadosListagemRole;

public record DadosListagemUsuario(
		Long id,
		String nome,
		String email,
		List<DadosListagemRole> roles,
		Boolean ativo
) {
	
	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRoles().stream().map(DadosListagemRole::new).toList(), usuario.getAtivo());
	}
}
