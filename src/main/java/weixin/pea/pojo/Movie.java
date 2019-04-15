package weixin.pea.pojo;

import java.sql.Date;
public class Movie {
	private int movieId;
	private String movieName;
	private String movieInfo;
	private Date releaseDate;
	private String actor;
	private Double score;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieInfo() {
		return movieInfo;
	}
	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
}
