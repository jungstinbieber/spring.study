package com.example.board.upload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFileDTO {

	private Long id;
	private String originalFilename;
	private String storedFilename;
	private String contentType;
	private Long size;
	private String url;
	private String path;
	private LocalDateTime createdAt;
	private String uploader;
	
	// ImageFile객체를 받아서 ImageFileDTO객체를 생성해주는 메서드
	public static ImageFileDTO from(ImageFile e) {
		
		return new ImageFileDTO(
				e.getId(),
				e.getOriginalFilename(),
				e.getStoredFilename(),
				e.getContentType(),
				e.getSize(),
				e.getUrl(),
				e.getPath(),
				e.getCreatedAt(),
				e.getUploader().getUsername()
				);
	}
	
}

// 클라이언트 이미지파일을 업로드 요철
// 서버에서 해당 이미지파일을 받음
// 이미지파일을 DB와 서버에 저장
// 처리가 다끝나면 DB에 저장된 내용을 담아서 응답처리