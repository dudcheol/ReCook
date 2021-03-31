package com.web.project.dao.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.survey.Allergy;

@Repository
public interface AllergyDao extends JpaRepository<Allergy, String>{

}
