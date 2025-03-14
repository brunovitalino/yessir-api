package lol.bvlabs.yessir.domain.mesa;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lol.bvlabs.yessir.domain.atendimento.Atendimento;
import lol.bvlabs.yessir.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "mesas")
@Entity(name = "Mesa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mesa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo;

	@OneToMany(mappedBy = "mesa")
	private List<Atendimento> atendimentos;
	
    @OneToOne(mappedBy = "mesa")
    private Usuario usuario;

	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;

	public Mesa(Long id) {
		this.id = id;
	}

	public Mesa(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Mesa(DadosCadastroMesa dadosCadastroMesa) {
		this.nome = dadosCadastroMesa.nome();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoMesa dadosAtualizacaoMesa) {
		if (dadosAtualizacaoMesa.nome() != null) {
			this.nome = dadosAtualizacaoMesa.nome();
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
