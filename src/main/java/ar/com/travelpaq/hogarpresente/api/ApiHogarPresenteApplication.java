package ar.com.travelpaq.hogarpresente.api;

import com.mercadopago.MercadoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiHogarPresenteApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiHogarPresenteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MercadoPago.SDK.setAccessToken(System.getenv("MP_ACCESS_TOKEN"));

		String password = "12345";

		for (int i = 0; i<4; i++){
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
	}
}
