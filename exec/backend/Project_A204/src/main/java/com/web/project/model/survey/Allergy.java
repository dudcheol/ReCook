package com.web.project.model.survey;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Allergy {

	@Id
	private int allergyId;
	
	private String allergyName;
	
	private String allergyImage;
	
}
