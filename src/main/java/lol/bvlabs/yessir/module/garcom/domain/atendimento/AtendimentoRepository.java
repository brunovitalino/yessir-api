package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
	
	Atendimento getByAtendenteNome(String nomeAtendente);
	
	Page<Atendimento> findByAtendenteNome(Pageable paginacao, String nomeAtendente);

	Page<Atendimento> findAllByAtivoTrue(Pageable paginacao);

}
