package lol.bvlabs.yessir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lol.bvlabs.yessir.domain.usuario.DadosAutenticacao;
import lol.bvlabs.yessir.domain.usuario.Usuario;
import lol.bvlabs.yessir.infra.security.DadosJWTToken;
import lol.bvlabs.yessir.infra.security.TokenService;

@RestController
@RequestMapping("/auth/login")
public class AutenticacaoController {

	Counter authUserSuccess;
	Counter authUserError;
	
	public AutenticacaoController(MeterRegistry registry) {
		authUserSuccess = Counter.builder("auth_user_success")
				.description("usuarios autenticados")
				.register(registry);

		authUserError = Counter.builder("auth_user_error")
				.description("erros de login")
				.register(registry);
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<DadosJWTToken> efetuarLogin(@RequestBody DadosAutenticacao dados) {
		var loginInfo = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

		try {
			var authentication = authenticationManager.authenticate(loginInfo);
			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
			authUserSuccess.increment();
			return ResponseEntity.ok(new DadosJWTToken(tokenJWT));

		} catch (Exception e) {
			authUserError.increment();
			return ResponseEntity.badRequest().build();
		}
	}

}
