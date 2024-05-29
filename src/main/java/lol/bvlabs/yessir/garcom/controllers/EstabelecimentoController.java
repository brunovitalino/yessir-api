package lol.bvlabs.yessir.garcom.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lol.bvlabs.yessir.garcom.domain.estabelecimento.Estabelecimento;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(List.of(
				new Estabelecimento(1L, "Coco bambu"),
				new Estabelecimento(2L, "Orbita blue"),
				new Estabelecimento(3L, "Sunset")
		));
	}
}
