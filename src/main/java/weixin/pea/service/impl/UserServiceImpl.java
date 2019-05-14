package weixin.pea.service.impl;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.pea.pojo.Comment;
import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
import weixin.pea.pojo.UserMapper;
import weixin.pea.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	public User userLogin(User user) {				//用户登录
		return userMapper.userLogin(user);
	}
	public int userRegister(User user) {			//用户注册
		return userMapper.userRegister(user);
	}
	public User queryUser(User user) {				//查询用户
		return userMapper.queryUser(user);
	}
	public int  updateUserInfo(User user) {			//修改用户信息
		return userMapper.updateUserInfo(user);
	}
	public int addMovie(Movie movie) {				//添加电影
		return userMapper.addMovie(movie);
	}
	public LinkedList<Movie> queryMovie(Movie movie) {				//查询电影
		return userMapper.queryMovie(movie);
	}
	public int postComment(Comment comment) {		//发表评论
		return userMapper.postComment(comment);
	}
	public int scoreCalculate(int score) {			//计算电影平均分
		return userMapper.scoreCalculate(score);
	}
	public LinkedList<Comment> queryComments(Comment comment){	//查询电影评价
		return userMapper.queryComments(comment);
	}
	public int updateMovie(Movie movie) {
		return userMapper.updateMovie(movie);
	}
	public int storeFilePath(User user) {
		return userMapper.storeFilePath(user);
	}
}
