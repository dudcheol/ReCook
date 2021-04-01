package com.web.project.dao.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.video.Video;

@Repository
public interface VideoDao extends JpaRepository<Video, String>{

	Page<Video> findAll(Pageable pageable);
	
}
