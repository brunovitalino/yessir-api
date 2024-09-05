package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
	
	Cardapio getByNome(String nome);
	
	Page<Cardapio> findByNome(Pageable paginacao, String nome);

	Page<Cardapio> findAllByAtivoTrue(Pageable paginacao);

}
