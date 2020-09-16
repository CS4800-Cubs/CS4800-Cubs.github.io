package com.compucompare.compucompare;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NatalieHTTPController {

    @RequestMapping(value = "/nataliePage", method = RequestMethod.GET)
    public String getText(){
        return "<HTML>"
        +           "<body><h1 style = \"color: lightblue\">Natalie's Page</h1></body>"
        +       "</HTML>";
    }
}
