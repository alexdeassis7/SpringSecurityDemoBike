package com.educacionit.bike.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educacionit.bike.models.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

	
	//modificamos la consulta y buscamos por una columna (nombre) para que luego podamos buscar un registro por nombre al momento que se haga un login 
	//findBy${nombreDeLaColumna} el findBy es una palabra reservada de spring donde le indicamos por que columan queremos hacer el where
	Usuario findByNombre(String nombre); //esto es como si hicieramos un : select * from usuario where nobre = "${algo}";
	
	
}
