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
	public User userLogin(User user) {
		return userMapper.userLogin(user);
	}
	public void userRegister(User user) {
		userMapper.userRegister(user);
	}
	public User getUser(int userId) {
		return userMapper.getUser(userId);
	}
	public int  updateUserInfo(User user) {
		return userMapper.updateInfo(user);
	}
	public Movie getMovie(Movie movie) {
		return userMapper.getMovie(movie);
	}
	public int postComment(Comment comment) {
		return userMapper.postComment(comment);
	}
}
