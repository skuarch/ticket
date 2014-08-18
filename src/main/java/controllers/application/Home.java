package controllers.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@Controller
public class Home {
    
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView home(){
        
        return new ModelAndView("ticket/home");
        
    }
    
}
