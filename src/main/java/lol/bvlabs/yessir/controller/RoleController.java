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
import lol.bvlabs.yessir.domain.role.DadosAtualizacaoRole;
import lol.bvlabs.yessir.domain.role.DadosCadastroRole;
import lol.bvlabs.yessir.domain.role.DadosListagemRole;
import lol.bvlabs.yessir.domain.role.Role;
import lol.bvlabs.yessir.domain.role.RoleRepository;

@RestController
@RequestMapping("/roles")
@SecurityRequirement(name = "bearer-key")
public class RoleController {
	
	@Autowired
	RoleRepository roleRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemRole>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(roleRepository.findByNome(paginacao, nome).map(DadosListagemRole::new));
		}
		return ResponseEntity.ok(roleRepository.findAllByAtivoTrue(paginacao).map(DadosListagemRole::new));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemRole> post(@RequestBody @Valid DadosCadastroRole dadosCadastrorRole, UriComponentsBuilder uriBuilder) {
		Role role = roleRepository.save(new Role(dadosCadastrorRole));
		DadosListagemRole dadosListagemRole = new DadosListagemRole(role);
		URI uri = uriBuilder.path("/roles/{id}").buildAndExpand(dadosListagemRole.id()).toUri();
		return ResponseEntity.created(uri).body(dadosListagemRole);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemRole> put(@RequestBody DadosAtualizacaoRole dadosAtualizacaoRole) {
		var role = roleRepository.getReferenceById(dadosAtualizacaoRole.id());
		role.atualizarInformacoes(dadosAtualizacaoRole);
		return ResponseEntity.ok(new DadosListagemRole(role));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var role = roleRepository.getReferenceById(id);
		role.excluir();
		return ResponseEntity.noContent().build();
	}
}
