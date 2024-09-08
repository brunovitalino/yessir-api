package lol.bvlabs.yessir.module.garcom.controller;

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

import lol.bvlabs.yessir.module.garcom.domain.role.DadosAtualizacaoRole;
import lol.bvlabs.yessir.module.garcom.domain.role.DadosCadastroRole;
import lol.bvlabs.yessir.module.garcom.domain.role.DadosListagemRole;
import lol.bvlabs.yessir.module.garcom.domain.role.Role;
import lol.bvlabs.yessir.module.garcom.domain.role.RoleRepository;

@RestController
@RequestMapping("/roles")
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
	public void post(@RequestBody DadosCadastroRole dadosCadastrorRole) {
		roleRepository.save(new Role(dadosCadastrorRole));
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoRole dadosAtualizacaoRole) {
		var role = roleRepository.getReferenceById(dadosAtualizacaoRole.id());
		role.atualizarInformacoes(dadosAtualizacaoRole);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var role = roleRepository.getReferenceById(id);
		role.excluir();
	}
}
