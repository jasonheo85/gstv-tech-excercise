package com.gstv.domain.last.fm.album;

import javax.xml.bind.annotation.XmlAttribute;

import com.gstv.domain.last.fm.Artist;

public class Track {

	private int rank;
	private String name;
	private int duration;
	private String mbid;
	private String url;
	private Artist artist;
	
	@XmlAttribute
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getMbid() {
		return mbid;
	}
	public void setMbid(String mbid) {
		this.mbid = mbid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + duration;
		result = prime * result + ((mbid == null) ? 0 : mbid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rank;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (duration != other.duration)
			return false;
		if (mbid == null) {
			if (other.mbid != null)
				return false;
		} else if (!mbid.equals(other.mbid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rank != other.rank)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Track [rank=" + rank + ", name=" + name + ", duration="
				+ duration + ", mbid=" + mbid + ", url=" + url + ", artist="
				+ artist + "]";
	}
}
