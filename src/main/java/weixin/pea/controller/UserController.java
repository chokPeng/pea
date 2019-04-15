package weixin.pea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
import weixin.pea.service.UserService;
@Controller
public class UserController {
	@Autowired
	UserService userService;
	//用户注册
	@RequestMapping("/userRegister")
	public ModelAndView register(User user) {
		System.out.println(user.getUserId());
		System.out.println(user.getUserPassword());
		User user2=userService.getUser(user.getUserId());
		if(user2==null) {
			userService.userRegister(user);
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject(user);
			modelAndView.setView(new MappingJackson2JsonView());
			return modelAndView;
		}else {
			return null;
		}
	}
	//用户登录
	@RequestMapping("/userLogin")
	public ModelAndView login(User user) {
		System.out.println(user.getUserId());
		System.out.println(user.getUserPassword());
		User user2=userService.userLogin(user);
		if(user2!=null) {
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject(user2);
			modelAndView.setView(new MappingJackson2JsonView());
			return modelAndView;
		}else {
			return null;
		}
	}
	//根据电影名查询电影
	@RequestMapping("/getMovie")
	public ModelAndView getMovie(Movie movie) {
		System.out.println(movie.getMovieName());
		Movie movie2=userService.getMovie(movie);
		if(movie2!=null) {
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject(movie2);
			modelAndView.setView(new MappingJackson2JsonView());
			return modelAndView;
		}else {
			return null;
		}
	}
}
