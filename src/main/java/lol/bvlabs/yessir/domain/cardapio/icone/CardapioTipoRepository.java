package lol.bvlabs.yessir.domain.cardapio.icone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioTipoRepository extends JpaRepository<CardapioTipo, Long> {
	
	CardapioTipo getByNome(String nome);
	
	Page<CardapioTipo> findByNome(Pageable paginacao, String nome);

	Page<CardapioTipo> findAllByAtivoTrue(Pageable paginacao);

}
