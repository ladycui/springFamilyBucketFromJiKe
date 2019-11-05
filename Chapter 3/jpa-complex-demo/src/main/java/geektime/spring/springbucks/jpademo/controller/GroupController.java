package geektime.spring.springbucks.jpademo.controller;

import com.alibaba.fastjson.JSONObject;
import geektime.spring.springbucks.jpademo.model.GroupA;
import geektime.spring.springbucks.jpademo.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-11-05 11:00
 * @Description: 使用@Validated分组校验注解
 * Default表示参数bean中未分组的属性参数
 */
@RestController
public class GroupController {

    /**
     * 只校验GroupA标注的参数
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(path = "/group/1")
    public String group1(@Validated({GroupA.class}) User user, BindingResult bindingResult) {
        validateInput(bindingResult);
        return JSONObject.toJSONString(user);
    }

    /**
     * 只校验未分组的参数
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(path = "/group/2")
    public String group2(@Validated({Default.class}) User user, BindingResult bindingResult) {
        validateInput(bindingResult);
        return JSONObject.toJSONString(user);
    }

    /**
     * 校验GroupA分组和Default的参数
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(path = "/group/3")
    public String group3(@Validated({GroupA.class, Default.class}) User user, BindingResult bindingResult) {
        validateInput(bindingResult);
        return JSONObject.toJSONString(user);
    }


    private void validateInput(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();//StringBuffer vs StringBuilder
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                sb.append(error.getDefaultMessage());
            }
            throw new ValidationException(sb.toString());
        }
    }
}
