package com.web.project.service.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.web.project.model.video.Video;

public interface VideoService {

	public ResponseEntity<Page<Video>> allVideo(Pageable pageable);

	public ResponseEntity<Video> videoDetail(int videoId);
	
}
