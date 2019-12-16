package com.ss.common.utils.easemob;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET},maxAge = 3600)
@RestController
@RequestMapping("/easemob")
public class EasemobController {


    @RequestMapping(value = "/1",produces="application/json")
    public String test1(){
        return "";
    }




}
