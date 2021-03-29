package com.web.project.dao.ingredients;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.ingredients.IngredientsSmall;

@Repository
public interface IngredientsSmallDao extends JpaRepository<IngredientsSmall,String>{

	List<IngredientsSmall> findAllIngredientsSmallByMidId(int midId);
	IngredientsSmall findIngredientsSmallBySmallName(String smallName);
}
