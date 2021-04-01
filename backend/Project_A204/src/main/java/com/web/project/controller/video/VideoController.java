package com.web.project.controller.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.model.video.Video;
import com.web.project.service.video.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Video", description = "영상 API")
@CrossOrigin
@RestController
@RequestMapping("/video")
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@GetMapping("/all")
	@ApiOperation(value = "모든 영상 가져오기")
	public ResponseEntity<Page<Video>> allVideo(Pageable pageable){
		return videoService.allVideo(pageable);
	}
}
