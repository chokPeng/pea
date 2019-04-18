package weixin.pea.pojo;

import java.sql.Date;

public class Comment {
	private int commentId;		
	private int userId;		
	private String userName;
	private String movieName;			//电影名
	private String movieId;				//电影Id
	private String commentContent;		//评论内容
	private Date commontDate;			//评论时间
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommontDate() {
		return commontDate;
	}
	public void setCommontDate(Date commontDate) {
		this.commontDate = commontDate;
	}
}
