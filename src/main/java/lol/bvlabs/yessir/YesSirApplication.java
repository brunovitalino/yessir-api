package lol.bvlabs.yessir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class YesSirApplication {

	public static void main(String[] args) {
		SpringApplication.run(YesSirApplication.class, args);

		var bcrypt = new BCryptPasswordEncoder();
		System.err.println("PASS ENCODED " + bcrypt.encode("1234"));
	}

}
