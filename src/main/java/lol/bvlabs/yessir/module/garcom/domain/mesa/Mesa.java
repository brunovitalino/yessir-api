package lol.bvlabs.yessir.module.garcom.domain.mesa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
