package lol.bvlabs.yessir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lol.bvlabs.yessir.domain.usuario.DadosAutenticacao;
import lol.bvlabs.yessir.domain.usuario.Usuario;
import lol.bvlabs.yessir.infra.security.DadosJWTToken;
import lol.bvlabs.yessir.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<DadosJWTToken> efetuarLogin(@RequestBody DadosAutenticacao dados) {
		var tokenAuthentication = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = authenticationManager.authenticate(tokenAuthentication);
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok(new DadosJWTToken(tokenJWT));
	}

}
