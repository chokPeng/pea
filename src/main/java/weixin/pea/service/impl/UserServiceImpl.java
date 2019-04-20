package weixin.pea.service.impl;

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
	public void userRegister(User user) {			//用户注册
		userMapper.userRegister(user);
	}
	public User getUser(int userId) {				//查询用户
		return userMapper.getUser(userId);
	}
	public int  updateUserInfo(User user) {			//修改用户信息
		return userMapper.updateUserInfo(user);
	}
	public int addMovie(Movie movie) {				//添加电影
		return userMapper.addMovie(movie);
	}
	public Movie getMovie(Movie movie) {				//查询电影
		return userMapper.getMovie(movie);
	}
	public int postComment(Comment comment) {		//发表评论
		return userMapper.postComment(comment);
	}
	public int scoreCalculate(int score) {			//计算电影平均分
		return userMapper.scoreCalculate(score);
	}
	public Comment queryComments(Comment comment) {	//查询电影评价
		return userMapper.queryComments(comment);
	}
	public int updateMovie(Movie movie) {
		return userMapper.updateMovie(movie);
	}
}
