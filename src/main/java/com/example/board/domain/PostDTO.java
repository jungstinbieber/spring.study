package com.example.board.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	@NotNull(message = "title은 반드시 입력")
	@NotBlank(message = "title은 공백 불가")
	@Size(min=1, max=20 , message="title은 1~20글자 입력")
	private String title;
	
	@NotNull(message = "content는 반드시 입력")
	@NotBlank(message ="content는 공백 불가")
	@Size(min=1,max=100, message= "content는 1~100글자 입력")
	private String content;
	
}