package com.gstv.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gstv.domain.Album;
import com.gstv.domain.Artist;
import com.gstv.domain.Error;
import com.gstv.domain.TopAlbums;
import com.gstv.domain.Track;
import com.gstv.domain.last.fm.artist.LastFmArtist;

@Service
public class TopAlbumServiceImpl implements TopAlbumService {
	
	private static final Logger log = LoggerFactory.getLogger(TopAlbumServiceImpl.class);
	
	@Value("${com.gstv.last.fm.api.artist.top.album.url}")
	private String topAlbumApiUrl;
	@Value("${com.gstv.last.fm.api.album.info.url}")
	private String albumInfoApiUrl;
	@Value("${com.gstv.last.fm.api.key}")
	private String apiKey;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public TopAlbums getTopAlbums(String artistName) {
		
		TopAlbums topAlbums = new TopAlbums();
        
        String getTopAlbumURL = topAlbumApiUrl + apiKey;
        log.info("getTopAlbumURL: " + getTopAlbumURL);
        
		LastFmArtist lastFmArtist = restTemplate.getForObject(getTopAlbumURL, LastFmArtist.class, artistName);
		log.info("LastFmArtist: " + lastFmArtist);
		
		if(lastFmArtist != null && lastFmArtist.getStatus().trim().equalsIgnoreCase("ok")) {	
			
			if(lastFmArtist.getTopAlbums() != null && lastFmArtist.getTopAlbums().getAlbums() != null &&
			  !lastFmArtist.getTopAlbums().getAlbums().isEmpty()) {
				
				if(lastFmArtist.getTopAlbums().getAlbums().get(0).getArtist() != null) {
					Artist artist = new Artist();
					BeanUtils.copyProperties(lastFmArtist.getTopAlbums().getAlbums().get(0).getArtist(), artist);
					log.info("LastFm Artist:  " + lastFmArtist.getTopAlbums().getAlbums().get(0).getArtist());
					log.info("Artist: " + artist);
					topAlbums.setArtist(artist);
				}
				List<Album> albums = new ArrayList<Album>();
				topAlbums.setAlbums(albums);
				
				for(com.gstv.domain.last.fm.artist.Album lastFmArtistAlbum: lastFmArtist.getTopAlbums().getAlbums()) {
					
					log.info("lastFmArtistAlbum: " + lastFmArtistAlbum);
					
					Album album = new Album();
					BeanUtils.copyProperties(lastFmArtistAlbum, album);
					albums.add(album);
					
					String getAlbumInfoURL = albumInfoApiUrl + apiKey;
					log.info("getAlbumInfoURL: " + getAlbumInfoURL);
					
					com.gstv.domain.last.fm.album.LastFmAlbum lastFmAlbumInfo = restTemplate.getForObject(getAlbumInfoURL,
							com.gstv.domain.last.fm.album.LastFmAlbum.class, artistName, lastFmArtistAlbum.getName());
					log.info("lastFmAlbumInfo: " + lastFmAlbumInfo);
					
					if(lastFmAlbumInfo != null && lastFmAlbumInfo.getAlbum() != null && lastFmAlbumInfo.getAlbum().getTracks() != null &&
					  !lastFmAlbumInfo.getAlbum().getTracks().getTracks().isEmpty()) {

						// copy releaseDate and listeners
						com.gstv.domain.last.fm.album.Album lastFmAlbum = lastFmAlbumInfo.getAlbum();
						album.setReleaseDate(lastFmAlbum.getReleaseDate());
						album.setListeners(lastFmAlbum.getListeners());
						
						// copy tracks
						List<Track> tracks = new ArrayList<Track>();
						album.setTracks(tracks);
						
						for(com.gstv.domain.last.fm.album.Track lastFmTrack: lastFmAlbum.getTracks().getTracks()) {
							Track track = new Track();
							BeanUtils.copyProperties(lastFmTrack, track);
							tracks.add(track);
						}
					}
				}
			} else {
				List<Error> errors = new ArrayList<Error>();
				Error error = new Error();
				error.setMessage("Artist not found or no album found for the artist");
				errors.add(error);
				topAlbums.setErrors(errors);
			}
		} else {
			if(lastFmArtist == null || !lastFmArtist.getStatus().trim().equalsIgnoreCase("ok")) {
				List<Error> errors = new ArrayList<Error>();
				Error error = new Error();
				error.setMessage("Artist not found");
				errors.add(error);
				topAlbums.setErrors(errors);
			}
		}
		return topAlbums;
	}
}
