package lol.bvlabs.yessir.domain.atendimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lol.bvlabs.yessir.domain.atendimento.validacoes.ValidadorAtendimento;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private List<ValidadorAtendimento> validadores;
	
	public Atendimento getOneByMesa(DadosAtendimento dadosAtendimento) {
		validadores.forEach(v -> v.validar(dadosAtendimento));
		var atendimento = atendimentoRepository.findOneAtivoByMesaId(dadosAtendimento.mesa().id());
		if (atendimento.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return atendimento.get();
	}

}
