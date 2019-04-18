package weixin.pea.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import weixin.pea.pojo.Comment;
import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
import weixin.pea.service.UserService;
@Controller
public class UserController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int result;
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
	//修改用户信息
	@RequestMapping(value="/alterUserInfo",method=RequestMethod.POST)
	public ModelAndView alterUserInfo(User user) {
		result=userService.updateUserInfo(user);
		ModelAndView modelAndView=new ModelAndView();
		if(result!=0) {
			User user2=userService.getUser(user.getUserId());	//修改用户信息后,返回修改后的用户信息
			modelAndView.addObject(user2);
			modelAndView.addObject("msg", "修改成功");
			modelAndView.setView(new MappingJackson2JsonView());			
		}else {
			modelAndView.addObject("msg","修改失败");
		}
		return modelAndView;
	}
	//根据电影名查询电影
	@RequestMapping(value="/getMovie",method=RequestMethod.POST)
	public ModelAndView getMovie(Movie movie) {
		System.out.println(movie.getMovieName());
		Movie movie2=userService.getMovie(movie);
		ModelAndView modelAndView=new ModelAndView();
		if(movie2!=null) {	
			modelAndView.addObject(movie2);
			modelAndView.addObject("msg", "获取成功");
			modelAndView.setView(new MappingJackson2JsonView());			
		}else {
			modelAndView.addObject("msg","获取失败");
		}
		return modelAndView;
	}
	//发表电影评论
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public ModelAndView comment(Comment comment) {
		ModelAndView modelAndView=new ModelAndView();
		result=userService.postComment(comment);
		if(result!=0) {
			modelAndView.addObject(comment);
			modelAndView.addObject("msg", "评论成功");
		}else {
			modelAndView.addObject("msg", "评论失败");
		}
		return modelAndView;
	}

}
