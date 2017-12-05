package com.cmpe275.cusr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";
	
    @RequestMapping(value = PATH)
    void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(response.getStatus());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    
}
