package com.web.project.dao.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.hashtag.Hashtag;

@Repository
public interface HashtagDao extends JpaRepository<Hashtag, String>{

	Hashtag findHashtagByHashtagName(String hashtag);
	
}
