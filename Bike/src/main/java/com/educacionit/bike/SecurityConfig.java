package com.educacionit.bike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.educacionit.bike.services.UserService;

@Configuration//marcamos la clase para que spring sepa que es una clase de configuracion
@EnableWebSecurity //habilito la segurida Web de Spring 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	metodo que se utilizara para codificar la clave 
	@Bean//con @bean defino una instancia singleton en el core containner de spring
	//para que luego en otra capa se pueda llamar y obtener la instancia a traves de un @autowired (en nuestro caso sera la capa de test )
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	
	@Autowired
	private UserService userDetailsService;	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	//sobreEscribo metodo de WebSecurityConfigurerAdapter, aca estoy explicando lo mismo que puse en el application.properties pero a nivel de spring en memoria
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
		//si quiero incluso puedo tener users en memoria , pero para que tenerlos en memorias si puedo disponerlos en la DB
			/*auth.
			inMemoryAuthentication()
			.withUser("usernamealex")
			.password("miclave123")
			.roles("USER")
			.and()
			.withUser("usuarioAdmin1")
			.password("claveAdmin")
			.roles("USER", "ADMIN");*/ //roles de mi app 
		
		//al objeto auth mediante el metodo "userDetailsService()"  le pasamos la implementacion que creamos de UserDetailsService 
		//y tambien le pasamos el password encoder 
		auth.userDetailsService(userDetailsService).passwordEncoder(bCrypt);
		
		}
	
	//sobreEscribo metodo de WebSecurityConfigurerAdapter para decirle que toda peticion que entra debe estar
	//autenticada sino no se podra ver la respuesta 
	@Override
		protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	
	}
	
	
	
}
