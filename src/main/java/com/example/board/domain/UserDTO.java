package com.example.board.domain;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotNull(message = "username은 null이면 안됩니다")
	@NotBlank(message = "username은 필수입력해야합니다")
	@Size(min=1, max=20, message="username은 1~20글자로 입력")
	private String username;
	
	@Size(min = 3, max =100, message="password는 3~100글자로 입력해야함")
	private String password;
	
	@NotNull(message = "email은 null안됨")
	@NotNull(message="email은 공백입력안됨")
	@Email(message = "이메일형식이아님")
	private String email;
	
}
