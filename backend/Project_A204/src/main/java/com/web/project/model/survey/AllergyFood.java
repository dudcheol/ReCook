package com.web.project.model.survey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Data
@Entity
@IdClass(AllergyFoodPK.class)
public class AllergyFood {
	
	@Id
	private int allergyId;
	
	@Id
	private int smallId;
	
}
