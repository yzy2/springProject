package com.test.microservice.restfulwebservice.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.test.microservice.restfulwebservice.exception.UserNotFoundExpection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import java.net.URI;
import java.util.List;


@RestController
public class UserResourceController {
	
	@Autowired
	private UserDataService service;
	
	// Get /users
	// retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	// GET /users/{id}
	// retrieveUser(int id)
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundExpection("id-" + id);
		
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	// POST /users
	// input - details of user
	// output - CREATED & Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Validated @RequestBody User user) {
		User savedUser = service.save(user);
		// CREATED
		// URI -> /user/{id} savedUser.getID()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	// Delete /users/{id}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User user = service.deleteById(id);
		
		if(user==null)
			throw new UserNotFoundExpection("id-" + id);
	}
}
