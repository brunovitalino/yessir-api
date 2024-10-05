package lol.bvlabs.yessir.module.garcom.domain.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Page<Pedido> findAllByAtivoTrue(Pageable paginacao);

	@Query("SELECT p FROM Pedido p WHERE p.ativo = true AND p.atendimento.id = :atendimentoId ORDER BY p.id ASC")
	List<Pedido> findAllByAtendimentoId(Long atendimentoId);

	@Query("SELECT p FROM Pedido p WHERE p.ativo = true AND p.atendimento.id = :atendimentoId AND p.cardapio.id = :cardapioId ORDER BY p.id DESC LIMIT 1")
	Optional<Pedido> findAllAtivoByAtendimentoIdAndCardapioId(Long atendimentoId, Long cardapioId);

}
