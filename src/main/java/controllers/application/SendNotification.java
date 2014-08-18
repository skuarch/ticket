package controllers.application;

import javax.ws.rs.QueryParam;
import model.logic.NotificationBroadcast;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * send notification to all clients (web browsers).
 * @author skuarch
 */
@Controller
public class SendNotification {
    
    @RequestMapping(value = "/sendNotification")
    public @ResponseBody String sendNotification(@QueryParam("text") String text,ModelAndView mav){
    
        NotificationBroadcast.sendMessage(text);     
        return "";
        
    }
    
}
