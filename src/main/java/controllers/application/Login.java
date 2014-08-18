package controllers.application;

import java.util.Locale;
import model.util.HandlerExceptionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller.
 * @author skuarch
 */
@Controller
public class Login {

    @Autowired
    MessageSource messageSource;
    private static final Logger logger = Logger.getLogger(Login.class);    

    //==========================================================================
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mav,Locale locale) {
       
        try {            
           
            mav.setViewName("application/login");
            
        } catch (Exception e) {
            HandlerExceptionUtil.handleException(mav, messageSource, e, logger, locale);
        }
        
        return mav;
        
    } // end login

} // end class
