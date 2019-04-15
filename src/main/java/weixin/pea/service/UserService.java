package weixin.pea.service;
import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
public interface UserService {
	public User userLogin(User user);
	public void userRegister(User user);
	public User getUser(int userId);
	public Movie getMovie(Movie movie);
}
