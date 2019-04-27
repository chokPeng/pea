package weixin.pea.service;
import java.util.LinkedList;

import weixin.pea.pojo.Comment;
import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
public interface UserService {
	public User userLogin(User user);		//用户登录
	public void userRegister(User user);		//用户注册
	public User queryUser(int userId);			//根据userId,查找用户
	public int updateUserInfo(User user);	//修改用户信息
	public LinkedList<Movie> queryMovie(Movie movie);		//查找电影
	public int addMovie(Movie movie);		//添加电影
	public int postComment(Comment comment); //发表评论
	public int scoreCalculate(int score);	//计算电影平均分
	public Comment queryComments(Comment comment);//查询电影评价
	public int updateMovie(Movie movie);		//修改电影信息
	public int storeFilePath(User user);	//在数据库存储文件绝对路径
}
