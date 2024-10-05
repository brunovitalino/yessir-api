package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.module.garcom.domain.atendente.Atendente;
import lol.bvlabs.yessir.module.garcom.domain.mesa.Mesa;
import lol.bvlabs.yessir.module.garcom.domain.pedido.Pedido;
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
	
	@Enumerated(EnumType.STRING)
	private AtendimentoStatusEnum status;
	
	@OneToMany(mappedBy = "atendimento")
	private List<Pedido> pedidos;

	private Boolean ativo;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;


	public Atendimento(Long id) {
		this.id = id;
	}

	public Atendimento(DadosCadastroAtendimento dadosCadastroAtendimento) {
		this.mesa = new Mesa(dadosCadastroAtendimento.mesa().id());
		if (dadosCadastroAtendimento.atendente() != null && dadosCadastroAtendimento.atendente().id() != null) {
			this.atendente = new Atendente(dadosCadastroAtendimento.atendente().id());
		}
		this.status = AtendimentoStatusEnum.AGUARDANDO;
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoAtendimento dadosAtualizacaoAtendimento) {
		if (dadosAtualizacaoAtendimento.mesa() != null && dadosAtualizacaoAtendimento.mesa().id() != null) {
			this.mesa = new Mesa(dadosAtualizacaoAtendimento.mesa().id());
		}
		if (dadosAtualizacaoAtendimento.atendente() != null && dadosAtualizacaoAtendimento.atendente().id() != null) {
			this.atendente = new Atendente(dadosAtualizacaoAtendimento.atendente().id());
		}
		if (dadosAtualizacaoAtendimento.status() != null) {
			this.status = dadosAtualizacaoAtendimento.status();
		}
	}

	public void excluir() {
		this.ativo = false;
	}
	
}
