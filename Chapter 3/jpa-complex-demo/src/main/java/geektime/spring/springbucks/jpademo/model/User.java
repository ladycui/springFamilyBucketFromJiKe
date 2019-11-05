package geektime.spring.springbucks.jpademo.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-11-05 11:01
 * @Description:
 */
@Data
public class User {

    @NotNull(groups = {GroupA.class}, message = "id is null")
    Integer id;

    @NotNull(message = "name is null")
    String name;


    /**
     * NotEmpty只能保证list非空，但里面元素是否为空不再校验
     * 在元素前添加校验注解
     */
    @NotEmpty(message = "parents 不能为空")
    List<@NotNull@Valid Person> parents;
}
