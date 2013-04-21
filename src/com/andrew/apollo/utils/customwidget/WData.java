package com.andrew.apollo.utils.customwidget;

public class WData {
	public static final String _HEADER = "apollo_widget";
	public static final String _AUTHOR = "author";
	public static final String _TITS = "title";
	public static final String _VERSION = "version";
	public static String _COLOR_T="title-color";
	public static String _COLOR_A="artist-color";
	public static String _SIZE="size";
	
	

	String NULL = "";
	String author;
	String title;
	String version;
	int titlecolor,artistcolor;
	int ukuran;



	/**
	 * @return the ukuran
	 */
	public int getUkuran() {
		return ukuran;
	}

	/**
	 * @param ukuran the ukuran to set
	 */
	public void setUkuran(int ukuran) {
		this.ukuran = ukuran;
	}

	/**
	 * @return the titlecolor
	 */
	public int getTitlecolor() {
		return titlecolor;
	}

	/**
	 * @param titlecolor the titlecolor to set
	 */
	public void setTitlecolor(int titlecolor) {
		this.titlecolor = titlecolor;
	}

	/**
	 * @return the artistcolor
	 */
	public int getArtistcolor() {
		return artistcolor;
	}

	/**
	 * @param artistcolor the artistcolor to set
	 */
	public void setArtistcolor(int artistcolor) {
		this.artistcolor = artistcolor;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "";
	}

}
