package geektime.spring.springbucks.jpademo.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 11:30
 * @Description:
 */
@Data
public class Person {

    @NotNull(message = "name 不能为空")
    String name;

    @NotNull(message = "age 不能为空")
    @Max(value = 20, message = "age 不能大于20")
    Integer age;

}
