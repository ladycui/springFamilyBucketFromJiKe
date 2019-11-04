package geektime.spring.springbucks.jpademo.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 11:30
 * @Description:
 */
@Data
public class InputObject {

    @NotNull(message = "name 不能为空")
    String name;

    @NotNull(message = "age 不能为空")
    @Max(value = 20, message = "age 不能大于20")
    Integer age;

}
