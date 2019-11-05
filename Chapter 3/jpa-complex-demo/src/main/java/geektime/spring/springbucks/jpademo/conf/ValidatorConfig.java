package geektime.spring.springbucks.jpademo.conf;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 18:12
 * @Description: 校验failFast
 */
@Configuration
public class ValidatorConfig {
//
//    @Bean
//    public Validator validate() {
//        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
//                .configure()
//                .failFast(false)//default false, 全部验证
//                .buildValidatorFactory();
//        Validator validator = factory.getValidator();
//        return validator;
//    }
}
