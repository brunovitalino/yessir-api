package lol.bvlabs.yessir.module.mesa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import lol.bvlabs.yessir.module.mesa.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	UserDetails findByEmail(String email);

	Page<Usuario> findByNome(Pageable paginacao, String nome);

	Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

}
