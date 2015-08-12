package com.gstv.domain.last.fm.artist;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gstv.domain.last.fm.LastFm;

@XmlRootElement(name="lfm")
public class LastFmArtist extends LastFm {

	private TopAlbums topAlbums;
	private String status;

	@XmlElement(name="topalbums")
	public TopAlbums getTopAlbums() {
		return topAlbums;
	}

	public void setTopAlbums(TopAlbums topAlbums) {
		this.topAlbums = topAlbums;
	}
	
	@XmlAttribute
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((topAlbums == null) ? 0 : topAlbums.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LastFmArtist other = (LastFmArtist) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (topAlbums == null) {
			if (other.topAlbums != null)
				return false;
		} else if (!topAlbums.equals(other.topAlbums))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LastFmArtist [topAlbums=" + topAlbums + ", status=" + status
				+ ", getError()=" + getErrors() + "]";
	}
}
