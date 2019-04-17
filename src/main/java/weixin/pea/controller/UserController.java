package weixin.pea.controller;


import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
import weixin.pea.service.UserService;
@Controller
public class UserController extends HttpServlet{
	@Autowired
	UserService userService;
	//用户注册
	@RequestMapping(value="/userRegister",method=RequestMethod.POST)
	public ModelAndView register(User user,HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		User user1=(User) session.getAttribute("userLogin");
		System.out.println(session.getId());
		System.out.println(user1.getUserId());
		User user2=userService.getUser(user1.getUserId());
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
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public ModelAndView login(User user,HttpServletRequest request,HttpServletResponse response) {
		User user1=userService.userLogin(user);
		if(user1!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("userLogin",user1);
			System.out.println(session.getId());
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject(user1);
			modelAndView.setView(new MappingJackson2JsonView());
			return modelAndView;
		}else {
			return null;
		}
	}
	//根据电影名查询电影
	@RequestMapping(value="/getMovie",method=RequestMethod.POST)
	@ResponseBody
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
	@RequestMapping(value="/getMovie2",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMovie2(Movie movie) {
		Map<String, Object> result=new HashMap<String, Object>();
		System.out.println(movie.getMovieName());
		Movie movie2=userService.getMovie(movie);
		if(movie2!=null) {
			result.put("msg", "获取成功");
			result.put("result", movie2);
		}else {
			result.put("msg", "获取失败");
		}
		return result;
	}
//	@RequestMapping("/test")
//	public ModelAndView test(HttpServletRequest request,HttpServletResponse response) {
//		HttpSession session=request.getSession();
//		User user3=(User) session.getAttribute("user21");
//		System.out.println(session.getId());
//		System.out.println(user3.toString());
//		System.out.println(user3.getUserId());
//		System.out.println(user3.getUserName());
//		ModelAndView modelAndView=new ModelAndView();
//		modelAndView.addObject(user3);
//		return modelAndView;
//	}
}
