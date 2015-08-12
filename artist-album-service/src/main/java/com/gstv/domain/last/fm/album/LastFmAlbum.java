package com.gstv.domain.last.fm.album;

import javax.xml.bind.annotation.XmlRootElement;

import com.gstv.domain.last.fm.LastFm;

@XmlRootElement(name="lfm")
public class LastFmAlbum extends LastFm{
	
	private Album album;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((album == null) ? 0 : album.hashCode());
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
		LastFmAlbum other = (LastFmAlbum) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LastFmAlbum [album=" + album + ", error=" + getErrors()
				+ "]";
	}
}
