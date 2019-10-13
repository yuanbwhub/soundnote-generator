package com.yuanbw.lanshan.voicenote.res;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexRes {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
