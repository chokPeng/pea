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
		User user2=userService.queryUser(user1.getUserId());
		ModelAndView modelAndView=new ModelAndView();
		if(user2==null) {
			userService.userRegister(user);
			modelAndView.addObject(user);
			modelAndView.addObject("msg", "注册成功");
			modelAndView.setView(new MappingJackson2JsonView());
			return modelAndView;
		}else {
			modelAndView.addObject("msg", "注册失败");
		}
		return modelAndView;
	}
	//用户登录
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public ModelAndView login(User user) {
		User user1=userService.userLogin(user);
		ModelAndView modelAndView=new ModelAndView();
		if(user1!=null) {
			//HttpSession session=request.getSession();
			//session.setAttribute("userLogin",user1);
			System.out.println(user.getUserId());
			System.out.println(user.getUserPassword());

			modelAndView.addObject(user1);
			modelAndView.addObject("msg", "登录成功");
			modelAndView.setView(new MappingJackson2JsonView());
		}else {
			modelAndView.addObject("msg", "登录失败");
		}
	   return modelAndView;
	}
	//修改用户信息
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	public ModelAndView alterUserInfo(User user) {
		System.out.println(user.getUserId());
		result=userService.updateUserInfo(user);
		ModelAndView modelAndView=new ModelAndView();
		if(result!=0) {
			User user2=userService.queryUser(user.getUserId());	//修改用户信息后,返回修改后的用户信息
			
			modelAndView.addObject(user2);
			modelAndView.addObject("msg", "修改成功");
			modelAndView.setView(new MappingJackson2JsonView());			
		}else {
			modelAndView.addObject("msg","修改失败");
		}
		return modelAndView;
	}
	//添加电影
	@RequestMapping(value="/addMovie",method=RequestMethod.POST)
	public  ModelAndView addMovie(Movie movie) {
		result=userService.addMovie(movie);
		ModelAndView modelAndView=new ModelAndView();
		if (result!=0) {
			modelAndView.addObject(movie);
			modelAndView.addObject("msg", "添加成功");
		} else {
			modelAndView.addObject("msg", "添加失败");
		}
		return modelAndView;
	}
	//修改电影信息
	@RequestMapping(value="/updateMovieInfo",method=RequestMethod.POST)
	public ModelAndView updateMovie(Movie movie) {
		System.out.println(movie.getMovieId());
		result=userService.updateMovie(movie);
		ModelAndView modelAndView=new ModelAndView();
		if(result!=0) {
			modelAndView.addObject(movie);
			modelAndView.addObject("msg", "修改成功");
			modelAndView.setView(new MappingJackson2JsonView());
		}else {
			modelAndView.addObject("msg","修改失败");
		}
		return modelAndView;
	}
	//根据电影名查询电影,可模糊查找
	@RequestMapping(value="/queryMovie",method=RequestMethod.POST)
	public ModelAndView getMovie(Movie movie) {
		System.out.println(movie.getMovieName());
		Movie movie2=userService.queryMovie(movie);
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
		result=userService.postComment(comment);		//插入评论
		ModelAndView modelAndView=new ModelAndView();
		if(result!=0) {
			modelAndView.addObject(comment);
			modelAndView.addObject("msg", "评论成功");
			modelAndView.setView(new MappingJackson2JsonView());
		}else {
			modelAndView.addObject("msg", "评论失败");
		}
		return modelAndView;
	}
	//查看电影评价
	@RequestMapping(value="/queryComments",method=RequestMethod.POST)
	public ModelAndView queryComments(Comment comment) {
		Comment comment2=userService.queryComments(comment);
		ModelAndView modelAndView=new ModelAndView();
		if(comment2!=null) {
			modelAndView.addObject(comment2);
			modelAndView.addObject("msg", "查询成功");
			modelAndView.setView(new MappingJackson2JsonView());
		}else {
			modelAndView.addObject("msg", "查询失败");
		}
		return modelAndView;
	}
	//电影评分计算,先从查询电影总得分totalScore,并存入数据库,再计算平均分averageScore,最后返回averageScore
	@RequestMapping(value="/scoreCalculate",method=RequestMethod.POST)
	public ModelAndView scoreCalculate(Movie movieId,Double score) {		//传入movieId和用户打的分
		ModelAndView modelAndView=new ModelAndView();
		Movie movie=userService.queryMovie(movieId);
		Double totalScore=movie.getTotalScore()+score;
		int scoreNumber=movie.getScoreNumber()+1;
		movie.setTotalScore(totalScore);
		movie.setAverageScore(totalScore/scoreNumber);
		return modelAndView;
	}
}
