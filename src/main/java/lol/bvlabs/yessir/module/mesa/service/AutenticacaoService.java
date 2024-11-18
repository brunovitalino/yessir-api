package lol.bvlabs.yessir.module.mesa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lol.bvlabs.yessir.module.mesa.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username);
	}

	public static void main(String[] args) {
		var bcrypt = new BCryptPasswordEncoder();
		System.err.println("PASS ENCODED " + bcrypt.encode("1234"));
	}
}
