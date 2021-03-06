package weixin.pea.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import weixin.pea.aop.DataValidate;
import weixin.pea.pojo.Comment;
import weixin.pea.pojo.Movie;
import weixin.pea.pojo.User;
import weixin.pea.service.UserService;
@Controller
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	int result;

	@Autowired
	UserService userService;
	@Autowired
	DataValidate dataValidate;			//每注入一个bean就得写多一个Autowired注解
	//用户注册
	@RequestMapping(value="/userRegister",method=RequestMethod.POST)
	public ModelAndView register(@Validated User user,BindingResult bindingResult) {
		ModelAndView modelAndView=new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("msg","注册失败");
			modelAndView.addObject(dataValidate.getErrors(bindingResult));
		}else {
			result=userService.userRegister(user);
			if (result!=0) {
				modelAndView.addObject("msg","注册成功");
				modelAndView.addObject(user);
			} else {
				modelAndView.addObject("msg","注册失败");
			}
		}
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}
	//用户登录
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public ModelAndView login(User user) {
		System.out.println("Id:"+user.getUserId()+"密码："+user.getUserPassword());
		User user1=userService.userLogin(user);
		ModelAndView modelAndView=new ModelAndView();
		if(user1!=null) {
			modelAndView.addObject(user1);
			modelAndView.addObject("msg", "登录成功");
		}else {
			modelAndView.addObject("msg", "登录失败");
		}
		modelAndView.setView(new MappingJackson2JsonView());
	   return modelAndView;
	}
	//修改用户信息
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	public ModelAndView alterUserInfo(User user) {
		result=userService.updateUserInfo(user);
		System.out.println(result);
		ModelAndView modelAndView=new ModelAndView();
		if(result==1) {
			User user2=userService.queryUser(user);	//修改用户信息后,返回修改后的用户信息
			modelAndView.addObject("user",user2);
			modelAndView.addObject("msg", "修改成功");
			modelAndView.setView(new MappingJackson2JsonView());			
		}else {
			modelAndView.addObject("msg","修改失败");
		}
		return modelAndView;
	}
	//查询用户信息
	@RequestMapping(value="/queryUser",method=RequestMethod.POST)
	public ModelAndView queryUser(User user) {
		System.out.println(user.getUserId());
		User user2=userService.queryUser(user);
		ModelAndView modelAndView=new ModelAndView();
		if(user2!=null) {
			modelAndView.addObject(user2);
			modelAndView.addObject("msg","查询成功");
		}else {
			modelAndView.addObject("msg","查询失败");
		}
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}
	//添加电影
	@RequestMapping(value="/addMovie",method=RequestMethod.POST)
	public  ModelAndView addMovie( Movie movie) {
		int result1=userService.addMovie(movie);
		ModelAndView modelAndView=new ModelAndView();
		
		if (result1!=0) {
			modelAndView.addObject(movie);
			modelAndView.addObject("msg", "添加成功");
			modelAndView.setView(new MappingJackson2JsonView());
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
	@ResponseBody
	public Map<String,LinkedList<Movie>> queryMovie(Movie movie) {
		Map<String,LinkedList<Movie>> map=new HashMap<>();
		map.put("movieList", userService.queryMovie(movie));
		return map;
	}
	//发表电影评论
	@RequestMapping(value="/postComment",method=RequestMethod.POST)
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
		//System.out.println(comment.getUserName());
		System.out.println(comment.getMovieName());
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("comment",userService.queryComments(comment));
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}
	//电影评分计算,先从查询电影总得分totalScore,并存入数据库,再计算平均分averageScore,最后返回averageScore
//	@RequestMapping(value="/scoreCalculate",method=RequestMethod.POST)
//	public ModelAndView scoreCalculate(Movie movieId,Double score) {		//传入movieId和用户打的分
//		ModelAndView modelAndView=new ModelAndView();
//		LinkedList<Movie> list=userService.queryMovie(movieId);
//		Double totalScore=movie.getTotalScore()+score;
//		int scoreNumber=movie.getScoreNumber()+1;
//		movie.setTotalScore(totalScore);
//		movie.setAverageScore(totalScore/scoreNumber);
//		return modelAndView;
//	}
	//上传用户头像
	 @RequestMapping(value="/uploadFile",method = RequestMethod.POST)
	 public  ModelAndView uploadImg(MultipartFile img,User user) throws IOException {
	    		ModelAndView modelAndView=new ModelAndView();	    	
	    		/* 因为img.getContentType()返回值为image/jpeg（或者图片的其他类型后缀名，如jpg等等),所以截取第六个字符串后的字符就可以获得图片的后缀名jpeg或者其它后缀名*/
	    		String uploadFileName=img.getContentType().substring(6);			
	    		uploadFileName=user.getUserName()+"."+uploadFileName;//设置用户的头像
	    		user.setUserAvatar("http://localhost:8080/image/"+uploadFileName);
	        File f=new File("/Users/pengchunkao/image/"+uploadFileName);
	        img.transferTo(f);							//写入磁盘
	        String path=f.getAbsolutePath();				//获取文件存储的绝对路径
	        result=userService.storeFilePath(user);		//存储文件到指定路径
	        if(result!=0) {
	        	 	modelAndView.addObject("path", path);
	        	 	modelAndView.addObject("msg", "上传成功");
	    	        modelAndView.setView(new MappingJackson2JsonView());
	        }else {
	        		modelAndView.addObject("msg", "上传失败");
	        }
	        return modelAndView;
   }
	 @RequestMapping(value="/test",method=RequestMethod.POST)
	 public ModelAndView test(@Validated User user,BindingResult bindingResult) {
		 ModelAndView modelAndView=new ModelAndView();
		 if(bindingResult.hasErrors()){    
			 modelAndView.addObject("msg","注册失败!");
			 modelAndView.addObject(dataValidate.getErrors(bindingResult));
		 }else {
			 result=userService.userRegister(user);
			 if (result==0) {
				 modelAndView.addObject("msg","注册失败!");
			} else {
				 modelAndView.addObject("msg", "注册成功!");
			}	
		 }
		 modelAndView.setView(new MappingJackson2JsonView());
		 return modelAndView;	 
	 }
}
