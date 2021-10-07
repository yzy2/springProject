package com.test.microservice.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


// Controller
@RestController
public class HelloWorldController {
	// GET
	// URI - /hello-world
	// method - "Hello World"
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hellow World";
	}
	
	// hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hellow World");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, &s", name));
	}
	
	
	
}
