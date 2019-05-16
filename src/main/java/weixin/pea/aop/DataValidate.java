package weixin.pea.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@Controller
//这个类用来校验数据
public class DataValidate {
	 public Map<String,String> getErrors(BindingResult bindingResult){
		 Map<String, String> map=new HashMap<>();
		 List<FieldError> errorList=bindingResult.getFieldErrors();
		 for(FieldError objectError:errorList) {
			 map.put(objectError.getField(), objectError.getDefaultMessage());
		 }
		 return map;
	 }
}
