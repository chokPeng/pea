package weixin.pea.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	//通过Spring的autowired注解获取spring默认配置的request
		@Autowired
		private HttpServletRequest request;
		//上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
		@RequestMapping("fileUpload")
		public String fileUpload(@RequestParam("file") MultipartFile file) {
			// 判断文件是否为空
			if (!file.isEmpty()) {
				try {
					// 文件保存路径
					String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
							+ file.getOriginalFilename();
					// 转存文件
					file.transferTo(new File(filePath));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 重定向
			return "redirect:/list.html";
		}
		// 读取上传文件中得所有文件并返回
		@RequestMapping("list")
		public ModelAndView list() {
			String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
			ModelAndView modelAndView = new ModelAndView("list");
			File uploadDest = new File(filePath);
			String[] fileNames = uploadDest.list();
			for (int i = 0; i < fileNames.length; i++) {
				//打印出文件名
				System.out.println(fileNames[i]);
			}
			return modelAndView;
		}
}
