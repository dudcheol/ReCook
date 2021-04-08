package com.web.project.dao.survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.survey.AllergyGroup;

@Repository
public interface AllergyGroupDao extends JpaRepository<AllergyGroup, String>{

	List<AllergyGroup> findAllByUserId(String userId);
	
}
