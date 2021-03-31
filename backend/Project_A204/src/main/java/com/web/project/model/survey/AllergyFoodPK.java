package com.web.project.model.survey;

import java.io.Serializable;

import lombok.Data;

@Data
public class AllergyFoodPK implements Serializable{

	private int allergyId;
	
	private int foodId;
	
}
