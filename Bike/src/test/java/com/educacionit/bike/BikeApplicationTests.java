package com.educacionit.bike;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.educacionit.bike.models.Usuario;
import com.educacionit.bike.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BikeApplicationTests {

	@Autowired
	private UsuarioRepository repo;

	@Test
	public void crearUserTest() {
		Usuario us = new Usuario();
		us.setId(1);
		us.setNombre("alex");
		us.setClave("1234");

		Usuario retorno = repo.save(us);

		assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));

	}

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void crearUserTestConPasswordEncryptada() {
		Usuario us = new Usuario();
		us.setId(2);
		us.setNombre("mati");
		us.setClave(encoder.encode("1234encriptada")); //el encoder no genera la clave con el mismo patron ! , si ejecutamos dos veces vamos 
		//a ver que aunque las dos ejecuciones sean con la misma clave se van a codificar de manera diferente 
		//no existe proceso inverso al encoder de eso se encargara BCryptPasswordEncoder :)

		Usuario retorno = repo.save(us);

		assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));

	}

}
