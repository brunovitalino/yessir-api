package lol.bvlabs.yessir.domain.atendimento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
	
	Atendimento getByAtendenteNome(String nomeAtendente);
	
	Page<Atendimento> findByAtendenteNome(Pageable paginacao, String nomeAtendente);

	Page<Atendimento> findAllByAtivoTrue(Pageable paginacao);
	
	List<Atendimento> findAllByMesaId(Long mesaId);
	
	@Query("""
			SELECT a FROM Atendimento a
			WHERE a.ativo = true AND a.status != ENCERRADO
			AND a.mesa.id = :mesaId
			""")
	List<Atendimento> findAllAtivosByMesaId(Long mesaId);

	@Query("""
			SELECT a FROM Atendimento a
			WHERE a.ativo = true AND a.status != ENCERRADO
			AND a.mesa.id = :mesaId
			ORDER BY a.id ASC LIMIT 1
			""")
	Optional<Atendimento> findOneAtivoByMesaId(Long mesaId);

	@Query("""			
			SELECT a FROM Atendimento a
			WHERE a.ativo = true
			AND a.id = :id
			""")
	Optional<Atendimento> findAtivoById(Long id); // ?? Atendimento getByAtivoAndId(Boolean ativo, Long id);

}
