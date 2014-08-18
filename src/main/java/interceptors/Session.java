package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
public class Session implements HandlerInterceptor{

    //==========================================================================
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {        
        //System.out.println("prehandle");
        return true;
    }

    //==========================================================================
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {        
        
        //Object username = request.getSession().getAttribute("user");        
        //mav.setViewName("ticket/home");
        
        //System.out.println("posthandle");
    }

    //==========================================================================
    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        //System.out.println("afterCompletation");
    }
    
} // end class
