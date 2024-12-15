package lol.bvlabs.yessir.domain.atendimento;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import lol.bvlabs.yessir.domain.atendente.Atendente;
import lol.bvlabs.yessir.domain.atendente.DadosAtualizacaoAtendente;
import lol.bvlabs.yessir.domain.atendente.DadosCadastroAtendente;
import lol.bvlabs.yessir.domain.atendente.DadosListagemAtendente;
import lol.bvlabs.yessir.domain.mesa.DadosAtualizacaoMesa;
import lol.bvlabs.yessir.domain.mesa.DadosCadastroMesa;
import lol.bvlabs.yessir.domain.mesa.DadosListagemMesa;
import lol.bvlabs.yessir.domain.mesa.Mesa;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class AtendimentoRepositoryTest {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	TestEntityManager em;

	@Test
	@DisplayName("Deve retornar o atendimento mais recente da mesa escolhida")
	void testFindOneRecentByMesaId() {
		// GIVEN ou arrange
		var mesa1 = cadastrarMesa("Mesa 1");

		var atendente1 = cadastrarAtendente("João");
		cadastrarAtendimento(mesa1, atendente1);

		var atendente2 = cadastrarAtendente("Roberto");
		cadastrarAtendimento(mesa1, atendente2);
		
		// WHEN ou act
		Optional<Atendimento> atendimentoOptional = atendimentoRepository.findOneAtivoByMesaId(mesa1.getId());


		// THEN ou assert
		assertThat(atendimentoOptional).isPresent();
		assertThat(atendimentoOptional.get().getAtendente().getId()).isEqualTo(atendente1.getId());
	}

	@Test
	@DisplayName("Deve retornar o atendimento mais recente da mesa escolhida que não esteja encerrado")
	void testFindOneRecentAndAtivoByMesaId() {
		// GIVEN
		var mesa1 = cadastrarMesa("Mesa 1");

		var atendente1 = cadastrarAtendente("João");
		var atendimento1 = cadastrarAtendimento(mesa1, atendente1);

		var atendente2 = cadastrarAtendente("Roberto");
		cadastrarAtendimento(mesa1, atendente2);
		
		atualizarAtendimento(atendimento1.getId(), null, null, AtendimentoStatusEnum.ENCERRADO);
		
		// WHEN
		Optional<Atendimento> atendimentoOptional = atendimentoRepository.findOneAtivoByMesaId(mesa1.getId());

		// THEN
		assertThat(atendimentoOptional).isPresent();
		assertThat(atendimentoOptional.get().getAtendente().getId()).isEqualTo(atendente2.getId());
	}

	private Atendimento atualizarAtendimento(Long atendimentoId, Mesa mesa, Atendente atendente, AtendimentoStatusEnum atendimentoStatusEnum) {
		var atendimento = em.find(Atendimento.class, atendimentoId);
		atendimento.atualizarInformacoes(dadosAtualizacaoAtendimento(atendimentoId, mesa, atendente, AtendimentoStatusEnum.ENCERRADO));
		return atendimento;
	}

	private Atendimento cadastrarAtendimento(Mesa mesa, Atendente atendente) {
		var atendimento = new Atendimento(dadosCadastroAtendimento(mesa, atendente));
		em.persist(atendimento);
		return atendimento;
	}
	private Atendente cadastrarAtendente(String nome) {
		var atendente = new Atendente(dadosCadastroAtendente(nome));
		em.persist(atendente);
		return atendente;
	}
	private Mesa cadastrarMesa(String nome) {
		var mesa = new Mesa(dadosCadastroMesa(nome));
		em.persist(mesa);
		return mesa;
	}

	private DadosAtualizacaoAtendimento dadosAtualizacaoAtendimento(Long atendimentoId, Mesa mesa, Atendente atendente, AtendimentoStatusEnum atendimentoStatusEnum) {
		var dadosListagemMesa = (mesa == null) ? null : new DadosAtualizacaoMesa(mesa);
		var dadosListagemAtendente = (atendente == null) ? null : new DadosAtualizacaoAtendente(atendente);
		return new DadosAtualizacaoAtendimento(atendimentoId, dadosListagemMesa, dadosListagemAtendente, atendimentoStatusEnum);
	}

	private DadosCadastroAtendimento dadosCadastroAtendimento(Mesa mesa, Atendente atendente) {
		var dadosListagemMesa = new DadosListagemMesa(mesa);
		var dadosListagemAtendente = new DadosListagemAtendente(atendente);
		return new DadosCadastroAtendimento(dadosListagemMesa, dadosListagemAtendente);
	}
	private DadosCadastroAtendente dadosCadastroAtendente(String nome) {
		return new DadosCadastroAtendente(nome);
	}
	private DadosCadastroMesa dadosCadastroMesa(String nome) {
		return new DadosCadastroMesa(nome);
	}

}
