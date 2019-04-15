package weixin.pea.pojo;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	public User userLogin(User user);
	public void userRegister(User user);
	public User getUser(int userId);
	public Movie getMovie(Movie movie);
	public void updateInfo(User user);
}
