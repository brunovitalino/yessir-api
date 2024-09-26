package lol.bvlabs.yessir.module.garcom.domain.mesa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
	
	Mesa getByNome(String nome);
	
	Page<Mesa> findByNome(Pageable paginacao, String nome);

	Page<Mesa> findAllByAtivoTrue(Pageable paginacao);

}
