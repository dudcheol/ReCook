package com.web.project.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.video.VideoDao;
import com.web.project.model.video.Video;

@Service
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private VideoDao videoDao; 

	@Override
	public ResponseEntity<Page<Video>> allVideo(Pageable pageable) {
		return new ResponseEntity<Page<Video>> (videoDao.findAll(pageable), HttpStatus.OK);
	}
	
}
