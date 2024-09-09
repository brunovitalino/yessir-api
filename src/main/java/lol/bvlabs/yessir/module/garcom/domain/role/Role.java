package lol.bvlabs.yessir.module.garcom.domain.role;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "roles")
@Entity(name = "Role")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;

	@Override
	public String getAuthority() {
		return nome;
	}
	
	public Role(Long id) {
		this.id = id;
	}

	public Role(DadosCadastroRole dadosRole) {
		this.ativo = true;
		this.nome = dadosRole.nome().toUpperCase().replace("_ROLE", "") + "_ROLE";
	}

	public void atualizarInformacoes(DadosAtualizacaoRole dadosAtualizacaoRole) {
		if (dadosAtualizacaoRole.nome() != null) {
			this.nome = dadosAtualizacaoRole.nome().replace("_ROLE", "") + "_ROLE";
		}
		if (dadosAtualizacaoRole.ativo() != null) {
			this.ativo = dadosAtualizacaoRole.ativo();
		}
	}

	public void excluir() {
		this.ativo = false;
	}
}
