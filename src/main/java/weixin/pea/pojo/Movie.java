package weixin.pea.pojo;

import java.sql.Date;
public class Movie {
	private int movieId;
	private String movieName;
	private String movieInfo;
	private Date releaseDate;
	private String actor;
	private Double totalScore;				 //总得分
	private Double averageScore;				//平均分
	private int scoreNumber;				//参与评分人数
	private String image;				//图片存储路径
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
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public int getScoreNumber() {
		return scoreNumber;
	}
	public void setScoreNumber(int scoreNumber) {
		this.scoreNumber = scoreNumber;
	}
	public Double getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
