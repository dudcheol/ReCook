package com.web.project.model.hashtag;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagId;
	
	private String hashtagName;
	
	private int hashtagCount;
}	
