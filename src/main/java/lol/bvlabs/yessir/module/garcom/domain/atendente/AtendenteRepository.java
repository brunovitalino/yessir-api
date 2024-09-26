package lol.bvlabs.yessir.module.garcom.domain.atendente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
	
	Atendente getByNome(String nome);
	
	Page<Atendente> findByNome(Pageable paginacao, String nome);

	Page<Atendente> findAllByAtivoTrue(Pageable paginacao);

}
