package com.rishabh.HeaderParser.service;

import com.rishabh.HeaderParser.domain.UserRequestHeader;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RequestHeaderService {

    public UserRequestHeader getRequestHeader(HttpServletRequest httpServletRequest) {
        String operatingSoftware = getOperatingSystem(httpServletRequest);
        String ipAddress = getIpAddress(httpServletRequest);
        String language = getAccessLanguage(httpServletRequest);
        UserRequestHeader userRequestHeader = new UserRequestHeader(ipAddress,language,operatingSoftware);
        return userRequestHeader;
    }

    // return the string of operating system.
    private String getOperatingSystem(HttpServletRequest request)
    {
        String userAgent = request.getHeader("user-agent");
        if(userAgent == null){
            return null;
        }
        //regular expression to extract the software from the userAgent
        // example of userAgent string
        // Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0
        String softwareRegularExpression = "[(](.*?)[)]";

        //create a pattern object
        Pattern pattern = Pattern.compile(softwareRegularExpression);

        //Now create matcher object
        Matcher matcher = pattern.matcher(userAgent);

        String software = null;
        if(matcher.find()){
            software = matcher.group(1);
        }
        return software;
    }

    //returns the string of IpAddress
    private String getIpAddress(HttpServletRequest request)
    {
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress!=null){
            return ipAddress;
        }
        return request.getRemoteAddr();
    }

    //returns the string of language of the browser
    private String getAccessLanguage(HttpServletRequest request){
        String accessLanguage = request.getHeader("accept-language");
        String language = null;
        if(accessLanguage!=null){
            //return the first accepted language
            language = accessLanguage.split(",")[0];
        }
        return language;
    }
}
