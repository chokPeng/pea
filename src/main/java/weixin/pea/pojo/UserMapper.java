package weixin.pea.pojo;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	public User userLogin(User user);				//用户登录	
	public void userRegister(User user);				//用户注册
	public User queryUser(int userId);					//根据userId查找用户
	public int updateUserInfo(User user);			//修改用户信息	
	public Movie queryMovie(Movie movie);				//查找电影		
	public int addMovie(Movie movie);				//添加电影
	public int postComment(Comment comment);			//发表评价
	public int scoreCalculate(int score);			//计算电影评分
	public int updateMovie(Movie movie);				//修改电影信息
	public Comment queryComments(Comment comment);	//查询评价
	public int storeFilePath(User user);			//存储文件绝对路径
}
