package com.web.project.model.survey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FoodLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodLikeId;

	private String userId;
	
	private int foodId;
}
