package lol.bvlabs.yessir.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	// Configuracoes de autenticacao
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		        .authorizeHttpRequests(req -> {
	                req.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
	                req.requestMatchers("/h2-console/**").permitAll();
	                req.requestMatchers("/assets/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll();  // Libera acesso ao console e arquivos estáticos
		            req.requestMatchers(HttpMethod.GET, "/hello").permitAll();
		            req.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
		            req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
		            req.requestMatchers("/actuator/**").permitAll();
		            req.anyRequest().authenticated();
		        })
	            .headers(headers -> headers
                    .frameOptions().sameOrigin() // Permite iframes da mesma origem (necessário para o H2 Console)
                )
		        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	// Configuracoes de token
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// Configuracoes de token
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
