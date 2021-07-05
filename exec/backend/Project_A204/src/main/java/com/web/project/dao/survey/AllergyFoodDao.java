package com.web.project.dao.survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.survey.AllergyFood;

@Repository
public interface AllergyFoodDao extends JpaRepository<AllergyFood, String>{

	List<AllergyFood> findAllByAllergyId(int allergyId);
	
}
