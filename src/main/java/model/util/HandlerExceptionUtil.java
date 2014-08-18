package model.util;

import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
public class HandlerExceptionUtil {    
    
    //==========================================================================
    /**
     * this class doesn't need a public constructor.
     */
    private HandlerExceptionUtil() {
    } // end HandlerExceptionUtil

    //==========================================================================
    public static void handleException(ModelAndView mav,MessageSource messageSource,Exception exception,Logger logger,Locale locale) {        
        
        logger.error(" error ", exception);        
        mav.addObject("js","alert(\'"+messageSource.getMessage("text4",null, locale)+"\')");

    } // end handleException

} // end class
