package com.web.project.model.video;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	
	private String videoTitle;
	
	private String videoUrl;
	
	private String videoChannel;
	
	private String videoChannelImg;
	
	private String videoThumbnail;
}
