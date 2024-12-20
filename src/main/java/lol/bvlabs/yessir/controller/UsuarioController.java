package lol.bvlabs.yessir.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lol.bvlabs.yessir.domain.usuario.DadosAtualizacaoUsuario;
import lol.bvlabs.yessir.domain.usuario.DadosCadastroUsuario;
import lol.bvlabs.yessir.domain.usuario.DadosListagemUsuario;
import lol.bvlabs.yessir.domain.usuario.Usuario;
import lol.bvlabs.yessir.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(usuarioRepository.findByNome(paginacao, nome).map(DadosListagemUsuario::new));
		}
		return ResponseEntity.ok(usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemUsuario> post(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioRepository.save(new Usuario(dadosCadastroUsuario));
		DadosListagemUsuario dadosListagemUsuario = new DadosListagemUsuario(usuario);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dadosListagemUsuario.id()).toUri();
		return ResponseEntity.created(uri).body(dadosListagemUsuario);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemUsuario> put(@RequestBody DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
		var usuario = usuarioRepository.getReferenceById(dadosAtualizacaoUsuario.id());
		usuario.atualizarInformacoes(dadosAtualizacaoUsuario);
		return ResponseEntity.ok(new DadosListagemUsuario(usuario));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var usuario = usuarioRepository.getReferenceById(id);
		usuario.excluir();
		return ResponseEntity.noContent().build();
	}
}
