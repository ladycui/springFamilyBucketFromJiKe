package geektime.spring.springbucks.jpademo.controller;

import com.alibaba.fastjson.JSONObject;
import geektime.spring.springbucks.jpademo.model.InputObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 10:23
 * @Description:
 */
@RestController
@Slf4j
@Validated
public class TestController {

    /**
     * 1. 单个参数校验，要在类上加上Spring的@Validated注解；
     *  若bean的话，只需在bean前@Valid or @Validated即可。
     * 2. 若有异常，会抛出ConstraintViolationException；
     *  统一异常捕获，使用@ControllerAdvice（@RestControllerAdvice）注解的类处理异常，详见ExceptionHandler类
     */
    @GetMapping("/test/get")
    public String testGet(@NotNull String name) {
        log.info("testGet name: {}", name);
        return name;
    }


    @GetMapping("/test/get2")
    public String testGet(@NotBlank(message = "name is blank") String name, @Min(value = 100) Integer age) {
        log.info("testGet name: {}, age: {}", name, age);
        return name;
    }



    @GetMapping("/test/get4")
    public String testGet4(@NotEmpty String name) {
        log.info("testGet4 name: {}", name);
        return name;
    }

    /**
     * 此时返回数据格式是默认格式
     */
    @PostMapping("/test/get5")
    public String testGet5(@Valid InputObject input) {
        log.info("/test/get5, input: {}", JSONObject.toJSONString(input));
        return JSONObject.toJSONString(input);
    }

    /**
     * 参数校验结果会被存到BindingResult中
     * 两种校验模式：1. 普通模式： 校验完所有属性，并返回所有验证失败的信息
     *          2. 快速模式：只要有一个校验失败，则返回
     * 普通模式（默认），快速模式详见ValidatorConfig类（配置了ValidatorFactory，设置其failFast为true）
     * @param input
     * @param bindingResult
     * @return
     */
    @PostMapping("/test/get6")
    public String testGet6(@Validated InputObject input, BindingResult bindingResult) {
        log.info("/test/get6, input: {}", JSONObject.toJSONString(input));
        validateInput(bindingResult);
        return JSONObject.toJSONString(input);
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
