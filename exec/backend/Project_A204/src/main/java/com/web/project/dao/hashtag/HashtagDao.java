package com.web.project.dao.hashtag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.hashtag.Hashtag;
import com.web.project.model.recipe.Recipe;

@Repository
public interface HashtagDao extends JpaRepository<Hashtag, String>{

	Hashtag findHashtagByHashtagName(String hashtag);
	
	Hashtag findHashtagByHashtagId(int hashtagId);
	
	List<Hashtag> findTop10ByOrderByHashtagCountDesc();
}
