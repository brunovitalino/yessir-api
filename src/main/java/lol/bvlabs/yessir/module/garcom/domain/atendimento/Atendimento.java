package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.module.garcom.domain.atendente.Atendente;
import lol.bvlabs.yessir.module.garcom.domain.mesa.Mesa;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "atendimentos")
@Entity(name = "Atendimento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atendimento {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "mesa_id", nullable = false)
	private Mesa mesa;
	@ManyToOne
	@JoinColumn(name = "atendente_id", nullable = false)
	private Atendente atendente;
	private AtendimentoStatus status;
	private Boolean ativo;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;

	public Atendimento(DadosCadastroAtendimento dadosCadastroAtendimento) {
		this.mesa = new Mesa(dadosCadastroAtendimento.mesaId());
		this.atendente = new Atendente(dadosCadastroAtendimento.atendenteId());
		this.status = dadosCadastroAtendimento.status();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoAtendimento dadosAtualizacaoAtendimento) {
		if (dadosAtualizacaoAtendimento.mesaId() != null) {
			this.mesa = new Mesa(dadosAtualizacaoAtendimento.mesaId());
		}
		if (dadosAtualizacaoAtendimento.atendenteId() != null) {
			this.atendente = new Atendente(dadosAtualizacaoAtendimento.atendenteId());
		}
		if (dadosAtualizacaoAtendimento.status() != null) {
			this.status = dadosAtualizacaoAtendimento.status();
		}
	}

	public void excluir() {
		this.ativo = false;
	}
	
}
