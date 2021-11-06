package com.userAuth.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@NoArgsConstructor
@AllArgsConstructor
@Builder //Project Lombok's @Builder is a useful mechanism for using the Builder 
		//pattern without writing boilerplate code. We can apply this annotation to a Class or a method.

@Entity
@Table(name="users")
public class Users extends BaseEntity {
	
	@NotNull
	@Size(min=2, max=30,message = "username must be between 2 and 30 characters long")
	private String username;
	
	//@NotNull: a constrained CharSequence, Collection, Map, or Array is valid as long as it's not null, but it can be empty
	//@NotEmpty: a constrained CharSequence, Collection, Map, or Array is valid as long as it's not null and its size/length is greater than zero
	//@NotBlank: a constrained String is valid as long as it's not null and the trimmed length is greater than zero
	@NotBlank(message = "Email is mandatory")
	private String emailId;
	
	@NotNull
	@Min(5)
	private String password;
}
