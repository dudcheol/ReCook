package com.web.project.dao.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.survey.FoodLike;

@Repository
public interface FoodLikeDao extends JpaRepository<FoodLike, String>{

}
