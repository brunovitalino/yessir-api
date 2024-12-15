package lol.bvlabs.yessir.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import lol.bvlabs.yessir.domain.atendimento.Atendimento;
import lol.bvlabs.yessir.domain.atendimento.AtendimentoRepository;
import lol.bvlabs.yessir.domain.atendimento.DadosCadastroAtendimento;
import lol.bvlabs.yessir.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.domain.mesa.DadosListagemMesa;
import lol.bvlabs.yessir.domain.mesa.Mesa;
import lol.bvlabs.yessir.domain.mesa.MesaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AtendimentoControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private JacksonTester<DadosCadastroAtendimento> dadosCadastroAtendimentojson;
	
	@Autowired
	private JacksonTester<DadosListagemAtendimento> dadosListagemAtendimentojson;
	
	@MockBean
	private MesaRepository mesaRepository;
	
	@MockBean
	private AtendimentoRepository atendimentoRepository;

	@Test
	@DisplayName("Deve retornar codigo http 400 quando as informacoes estao invalidas")
	@WithMockUser
	void testPostWithInvalidInformation() throws Exception {
		//WHEN
		var response = mock
				.perform(post("/atendimentos"))
				.andReturn().getResponse();
		//THEN
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("Deve retornar codigo http 200 quando as informacoes estao validas")
	@WithMockUser
	void testPostWithValidInformation() throws Exception {
		//GIVEN
		var dadosListagemMesa = new DadosListagemMesa(1L, null);
		var dadosCadastroAtendimento = new DadosCadastroAtendimento(dadosListagemMesa, null);
		
		var mesaOpt = Optional.of(new Mesa(dadosListagemMesa.id()));
		var atendimento = new Atendimento(1L);
		//WHEN
		when(mesaRepository.findById(dadosListagemMesa.id())).thenReturn(mesaOpt);
		when(atendimentoRepository.save(any())).thenReturn(atendimento);
		
		var response = mock
				.perform(
						post("/atendimentos")
								.contentType(MediaType.APPLICATION_JSON)
								.content(dadosCadastroAtendimentojson.write(dadosCadastroAtendimento).getJson())
				).andReturn().getResponse();
		//THEN
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		
		//GIVEN
		var jsonEsperado = dadosListagemAtendimentojson.write(
				new DadosListagemAtendimento(atendimento.getId(), dadosListagemMesa, null, atendimento.getStatus())
		).getJson();
		//THEN
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}

}
