/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.application;

import model.logic.NotificationBroadcast;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author skuarch
 */
@Controller
public class TestNotification {

    @RequestMapping(value = "/testNotification")
    public @ResponseBody void testNotification(@RequestParam("text") String text) {

        if (text == null || text.length() < 1) {
            text = "hello word";
        }

        NotificationBroadcast.sendMessage(text);

        //return "ok";

    }

}
