package com.example.board.upload;

import java.time.LocalDateTime;

import com.example.board.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="image_files")
@NoArgsConstructor
@Getter
public class ImageFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String originalFilename; // 원본파일명
	
	@Column(nullable= false, unique=true)
	private String storedFilename; // 실제로 저장된 파일명
	
	@Column(nullable=false )
	private String contentType;
	
	@Column(nullable=false)
	private Long Size; // 파일 크기
	
	@Column(length=300)
	private String url;
	
	@Column(length=300)
	private String path;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "uploader_id")
	private User uploader;

	public ImageFile( String originalFilename, String storedFilename, String contentType, Long size, String url,
			String path,User uploader) {
	super();
		
		this.originalFilename = originalFilename;
		this.storedFilename = storedFilename;
		this.contentType = contentType;
		Size = size;
		this.url = url;
		this.path = path;
		this.createdAt = LocalDateTime.now();
		this.uploader = uploader;
	}
	
	
}
