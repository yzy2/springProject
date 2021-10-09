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
import java.util.Optional;


@RestController
public class UserJPAResourceController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// Get /users
	// retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	// GET /users/{id}
	// retrieveUser(int id)
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id){
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundExpection("id-" + id);
		
		EntityModel<Optional<User>> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	// POST /users
	// input - details of user
	// output - CREATED & Return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Validated @RequestBody User user) {
		User savedUser = userRepository.save(user);
		// CREATED
		// URI -> /user/{id} savedUser.getID()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/jpa/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isEmpty()) {
			throw new UserNotFoundExpection("id-" + id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		
		// CREATED
		// URI -> /user/{id} savedUser.getID()
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/jpa/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	// Delete /users/{id}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
		
}
