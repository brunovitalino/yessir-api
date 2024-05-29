package lol.bvlabs.yessir.garcom.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(username);
	}
	
	public static void gerarPass() {
		var bcrypt = new BCryptPasswordEncoder();
		System.err.println("PASS ENCODED " + bcrypt.encode("1234"));
	}
	
	public static void main(String[] args) {
		gerarPass();
	}

}
