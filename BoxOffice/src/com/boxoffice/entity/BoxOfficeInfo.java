package com.boxoffice.entity;

import java.util.Comparator;

/**
 * 封装实时电影信息的实体类
 * 并已上映的天数进行排序
 * @author Han
 *
 */
public class BoxOfficeInfo {
	private String boxOfficeToday;
	private String beOnDays;
	private String movieName;
	private String boxOfficeTotal;
	public String getBoxOfficeToday() {
		return boxOfficeToday;
	}
	public void setBoxOfficeToday(String boxOfficeToday) {
		this.boxOfficeToday = boxOfficeToday;
	}
	public String getBeOnDays() {
		return beOnDays;
	}
	public void setBeOnDays(String beOnDays) {
		this.beOnDays = beOnDays;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getBoxOfficeTotal() {
		return boxOfficeTotal;
	}
	public void setBoxOfficeTotal(String boxOfficeTotal) {
		this.boxOfficeTotal = boxOfficeTotal;
	}
	@Override
	public String toString() {
		return "BoxOfficeInfo [boxOfficeToday=" + boxOfficeToday
				+ ", beOnDays=" + beOnDays + ", movieName=" + movieName
				+ ", boxOfficeTotal=" + boxOfficeTotal + "]";
	}
	
	
	
}
