package lol.bvlabs.yessir.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lol.bvlabs.yessir.module.mesa.model.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API YesSir")
		        .withSubject(usuario.getUsername())
		        .withPayload(Map.of(
		        		"mesaId", usuario.getMesa() == null || usuario.getMesa().getId() == null ? "" : usuario.getMesa().getId(),
		        		"roles", usuario.getRoles().stream()
		        		.filter(r -> r.getAtivo())
		        		.map(r -> r.getNome().substring(0, r.getNome().indexOf("_ROLE")))
		        		.collect(Collectors.joining(","))))
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar token jwt", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        .withIssuer("API YesSir")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		} catch (JWTVerificationException exception){
			throw new RuntimeException("Token JWT inválido ou expirado.");
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
