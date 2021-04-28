package com.educacionit.bike.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.educacionit.bike.models.Usuario;
import com.educacionit.bike.repositories.UsuarioRepository;

@Service
//aca en este servicio le vamos a indicar al user de donde debe sacar los datos de los usuarios , los roles , etc...
public class UserService implements UserDetailsService{

	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//buscamos un usuario por su nombre 
		Usuario us = repo.findByNombre(username);
		
		//spring posee la interface GrantedAuthority para definir los roles de la app 
		//esto aca lo vamos a harcodear pero deberia venir de la base 
		List<GrantedAuthority> roles = new ArrayList<>();
		//agrego un role a la lista de roles 
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		//el metodo debe retornar un objeto UserDetail entonces creamos un objeto de este tipo
		//y le pasamos (usuario , clave , roles )
		UserDetails userDet = new User(us.getNombre() , us.getClave() , roles );
		
		
		//esto no es dificil , no asustarse solo debemos adaptarnos a la forma que Spring maneja la seguridad , imaginen
		//si fuera al reves Spring deberia adaptarse a todos los modelos de las bases de datos y no existiria algo standarizado para trabajaar
		//con seguridad de spring 
		
		return userDet;
	}

}