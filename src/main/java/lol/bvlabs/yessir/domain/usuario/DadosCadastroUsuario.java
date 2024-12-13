package lol.bvlabs.yessir.domain.usuario;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lol.bvlabs.yessir.domain.role.Role;

public record DadosCadastroUsuario(
		@NotBlank
		String nome,
		@NotBlank @Email
		String email,
		@NotBlank @Pattern(regexp = "\\d{4,30}")
		String senha,
		@NotNull
		List<Role> roles
) {}
