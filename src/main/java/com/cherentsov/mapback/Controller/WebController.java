package com.cherentsov.mapback.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new String("fdgfdgfd");
    }
    @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new String("fdgfdgfddfer");
    }

}
