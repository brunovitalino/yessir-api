package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
	
	Atendimento getByAtendenteNome(String nomeAtendente);
	
	Page<Atendimento> findByAtendenteNome(Pageable paginacao, String nomeAtendente);

	Page<Atendimento> findAllByAtivoTrue(Pageable paginacao);
	
	List<Atendimento> findAllByMesaId(Long mesaId);
	
	@Query("SELECT a FROM Atendimento a WHERE a.mesa.id = :mesaId AND a.status = EM_ATENDIMENTO AND a.ativo = true")
	List<Atendimento> findAllAtivoByMesaId(Long mesaId);

}
