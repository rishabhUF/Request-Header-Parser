package com.rishabh.HeaderParser.web;

import com.rishabh.HeaderParser.domain.UserRequestHeader;
import com.rishabh.HeaderParser.service.RequestHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestHeaderController {

    @Autowired
    RequestHeaderService requestHeaderService;

    @RequestMapping(value = "get/userRequestHeader", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<UserRequestHeader> getUserRequestHeader(HttpServletRequest httpServletRequest){
       try{
           UserRequestHeader userRequestHeader = requestHeaderService.getRequestHeader(httpServletRequest);
           return new ResponseEntity<>(userRequestHeader,HttpStatus.OK);
       }
       catch (Exception e)
       {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
