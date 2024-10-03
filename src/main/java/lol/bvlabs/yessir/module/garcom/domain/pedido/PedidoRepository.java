package lol.bvlabs.yessir.module.garcom.domain.pedido;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Page<Pedido> findAllByAtivoTrue(Pageable paginacao);
	
	@Query("SELECT p FROM Pedido p WHERE p.ativo = true")
	Page<Pedido> findAllAtivoById(Pageable paginacao);

	@Query("SELECT p FROM Pedido p WHERE p.atendimento.id = :atendimentoId AND p.ativo = true")
	List<Pedido> findAllAtivoByAtendimentoId(Long atendimentoId);
	

}
