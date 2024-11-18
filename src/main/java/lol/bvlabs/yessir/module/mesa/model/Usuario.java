package lol.bvlabs.yessir.module.mesa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lol.bvlabs.yessir.module.garcom.domain.mesa.Mesa;
import lol.bvlabs.yessir.module.garcom.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String email;
	@NotEmpty
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    private Mesa mesa;
	
	private Boolean ativo;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;


	public Usuario(DadosCadastroUsuario dadosUsuario) {
		this.ativo = true;
		this.nome = dadosUsuario.nome();
		this.email = dadosUsuario.email();
		if (dadosUsuario.senha() != null ) {			
			var encoder = new BCryptPasswordEncoder();
			this.senha = encoder.encode(dadosUsuario.senha());
		}
		this.roles = dadosUsuario.roles();
	}

	public void atualizarInformacoes(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
		if (dadosAtualizacaoUsuario.nome() != null) {
			this.nome = dadosAtualizacaoUsuario.nome();
		}
		if (dadosAtualizacaoUsuario.email() != null) {
			this.email = dadosAtualizacaoUsuario.email();
		}
		var encoder = new BCryptPasswordEncoder();
		if (dadosAtualizacaoUsuario.senha() != null && dadosAtualizacaoUsuario.novaSenha() != null
				&& encoder.matches(dadosAtualizacaoUsuario.senha(), this.senha)) {
			this.senha = encoder.encode(dadosAtualizacaoUsuario.novaSenha());
		}
		if (dadosAtualizacaoUsuario.ativo() != null) {
			this.ativo = dadosAtualizacaoUsuario.ativo();
		}
		if (dadosAtualizacaoUsuario.roles() != null) {
			this.roles = dadosAtualizacaoUsuario.roles();
		}
	}

	public void excluir() {
		this.ativo = false;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles; // List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public String getPassword() {
		return senha;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
}
