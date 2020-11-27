package org.johnpc.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "hello")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "hello-bean")
    public HelloBean helloBean(){
        return new HelloBean("Hello from beans");
    }

    @GetMapping(path = "i18n-message")
    public String intlMessage(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("hello.world.message", null, locale);
    }

    @GetMapping(path = "message-long")
    public String intlMessageLong(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("long.message.text", null, locale);
    }

    // Alternate method to do the internationalization think, instead of using the Locale things.
    @GetMapping(path = "message-long-alternate")
    public String intlMessageAlternate(){
        return messageSource.getMessage("long.message.text", null, LocaleContextHolder.getLocale());
    }

}
