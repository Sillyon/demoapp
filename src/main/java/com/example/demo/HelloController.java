package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "This is the index!\n";
	}

    @GetMapping("/hello")
    public String index2() {
        return "Hello World!\n";
    }

}