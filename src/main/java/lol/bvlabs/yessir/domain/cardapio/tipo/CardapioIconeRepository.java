package lol.bvlabs.yessir.domain.cardapio.tipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioIconeRepository extends JpaRepository<CardapioIcone, Long> {
	
	CardapioIcone getByNome(String nome);
	
	Page<CardapioIcone> findByNome(Pageable paginacao, String nome);

	Page<CardapioIcone> findAllByAtivoTrue(Pageable paginacao);

}
