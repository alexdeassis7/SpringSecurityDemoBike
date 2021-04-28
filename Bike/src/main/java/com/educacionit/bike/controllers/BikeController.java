package com.educacionit.bike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educacionit.bike.models.Bike;
import com.educacionit.bike.repositories.BikeRepository;

@RestController
//mediante esta anotacion sera suficiente para que spring la publique como un Spring Rest Service
//URI BASE
@RequestMapping("api/v1/bikes") //localhost:8888/api/v1/bikes
public class BikeController {
	@Autowired
	private BikeRepository bk;
	
//	@GetMapping
//	public String holaMundo () {
//		return "Hola mundo soy una Api RESt de Spring Boot";
//	}
//	
	@GetMapping
	public List<Bike> lista () {
		return bk.findAll();
		
	}
	
	// http://localhost:8888/api/v1/bikes/${id}	
	//este recurso retona una bike mediante el id recibido en la url 
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Bike getBike(@PathVariable("id")Long id) {
		System.out.println("recibimos por path variable el id : " + id );
		return bk.getOne(id);
	}

	//este metodo recibe en el body de la peticion http un 
	//JSON (Java Script Object Notation) que sera persistido en el SQlite
	@PostMapping
	public void create(@RequestBody Bike bike) {
		bk.save(bike);
	}

	
	
}
