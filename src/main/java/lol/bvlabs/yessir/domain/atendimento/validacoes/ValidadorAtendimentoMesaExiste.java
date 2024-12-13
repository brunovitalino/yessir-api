package lol.bvlabs.yessir.domain.atendimento.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lol.bvlabs.yessir.domain.ValidacaoException;
import lol.bvlabs.yessir.domain.atendimento.DadosAtendimento;
import lol.bvlabs.yessir.domain.mesa.MesaRepository;

@Component
public class ValidadorAtendimentoMesaExiste implements ValidadorAtendimento {

	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public void validar(DadosAtendimento dadosAtendimento) {
		if (!mesaRepository.existsById(dadosAtendimento.mesa().id())) {
			throw new ValidacaoException("Id da mesa informada não existe!");
		}
	}

}
