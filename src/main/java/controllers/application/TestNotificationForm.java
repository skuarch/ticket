package controllers.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@Controller
public class TestNotificationForm {
    
    @RequestMapping(value = "/testNotificationForm")
    public ModelAndView testNotificationForm(ModelAndView mav){
    
        mav.setViewName("application/testNotificationForm");        
        return mav;
    
    }
    
} // end class
