package com.web.project.dao.ingredients;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.ingredients.IngredientsMiddle;
@Repository
public interface IngredientsMiddleDao extends JpaRepository<IngredientsMiddle,String>{

	List<IngredientsMiddle> findAllIngredientsMiddleByBigId(int bigId);
	
}
