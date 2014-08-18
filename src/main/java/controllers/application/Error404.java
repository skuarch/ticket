package controllers.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
* @author skuarch
*/
@Controller
public class Error404 {
    
    @RequestMapping("/error404")
    public String error404(){
    
        return "application/error404";
        
    }
    
}