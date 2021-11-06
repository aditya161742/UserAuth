package com.userAuth.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userAuth.models.Users;
import com.userAuth.services.UserDetailsService;
import com.userAuth.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody String userName) {
		String token = jwtUtil.generateToken(userName);

		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	/*@PostMapping("/auth/register")
	public ResponseEntity<String> register(@RequestBody String userName) {
		// Persist user to some persistent storage
		System.out.println("Info saved...");

		return new ResponseEntity<String>("Registered", HttpStatus.OK);
	}*/
	
	//@ModelAttribute is used for binding data from request param (in key value pairs),
		//but @RequestBody is used for binding data from whole body of the request like POST,PUT.. request types which contains other format like json, xml.
		//@RequestMapping(value="registerUser",method=RequestMethod.POST, consumes = "multipart/form-data")
		@PostMapping(path = "/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE)
		
		/*public ResponseEntity<Users> registerUser(@RequestBody Users user) {
			
			ResponseEntity is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.

			@ResponseBody is a marker for the HTTP response body and @ResponseStatus declares the status code of the HTTP response.
			
			@ResponseStatus isn't very flexible. It marks the entire method so you have to be sure that your handler method will always behave the same way. And you still can't set the headers. You'd need the HttpServletResponse.
			
			Basically, ResponseEntity lets you do more.

			try {
				user = userService.addUser(user);
				//System.out.println("country name = "+user.getEmailId());
				return new ResponseEntity<Users>(user, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			
		}*/
		
		public ResponseEntity<Users> registerUser(@Valid @RequestBody Map<String, String> payloadO) {
			
			//helpers.printAllRequestParameters(request);
			
			ObjectMapper mapper = new ObjectMapper();
			Users users = mapper.convertValue(payloadO, Users.class);   //Convert Map to Java Object
			
			//System.out.println(payloadO.get("username"));
			//System.out.println(payloadO.get("email_id"));
			Users saved_user = userDetailsService.save(users);
			
			try {
				
				//return mapper.writeValueAsString(saved_user);   //Object to JSON
				return new ResponseEntity<Users>(saved_user, HttpStatus.CREATED);
			}
			catch (Exception  e) {
			    // catch various errors
			    e.printStackTrace();
			    return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
}
