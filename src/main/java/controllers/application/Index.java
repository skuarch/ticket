package controllers.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@Controller
public class Index {
    
    @RequestMapping(value = {"","/","/index"})
    public ModelAndView login(){
        return new ModelAndView("application/login");
    }
    
}
