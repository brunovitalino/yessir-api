package lol.bvlabs.yessir.domain.atendente;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.domain.atendimento.Atendimento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "atendentes")
@Entity(name = "Atendente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atendente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "atendente")
	private Set<Atendimento> atendimentos;
	private Boolean ativo;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;

	public Atendente(Long id) {
		this.id = id;
	}

	public Atendente(DadosCadastroAtendente dadosCadastroAtendente) {
		this.nome = dadosCadastroAtendente.nome();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoAtendente dadosAtualizacaoAtendente) {
		if (dadosAtualizacaoAtendente.nome() != null) {
			this.nome = dadosAtualizacaoAtendente.nome();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
