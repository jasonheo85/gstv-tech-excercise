package com.gstv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gstv.domain.TopAlbums;
import com.gstv.service.TopAlbumService;

@RestController
public class TopAlbumController {
	
	private static final Logger log = LoggerFactory.getLogger(TopAlbumController.class);
	
	@Autowired
	TopAlbumService topAlbumService;
	
	@RequestMapping("/topAlbums/{artist}")
	public TopAlbums topAlbums(@PathVariable(value="artist") String artistName) {

		log.info("artistName: " + artistName);
		
		return topAlbumService.getTopAlbums(artistName);
	}
}
