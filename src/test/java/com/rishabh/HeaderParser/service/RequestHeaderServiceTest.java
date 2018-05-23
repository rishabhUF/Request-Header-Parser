package com.rishabh.HeaderParser.service;

import com.rishabh.HeaderParser.domain.UserRequestHeader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class RequestHeaderServiceTest {

    @InjectMocks
    private RequestHeaderService requestHeaderService;

    @Test
    public void TestGetUserRequestHeader(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        request.addHeader("accept-language", "us");
        UserRequestHeader userRequestHeader = new UserRequestHeader(request.getRemoteAddr(),"us","Windows NT 6.1");
        Assert.assertEquals(requestHeaderService.getRequestHeader(request).getLanguage(),userRequestHeader.getLanguage());
    }

    @Test
    public void TestGetUserRequestHeaderWithIpAddress()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        request.addHeader("accept-language", "us");
        request.addHeader("x-forwarded-for","120.0.0.12");
        UserRequestHeader userRequestHeader = new UserRequestHeader("120.0.0.12","us","Windows NT 6.1");
        Assert.assertEquals(requestHeaderService.getRequestHeader(request).getLanguage(),userRequestHeader.getLanguage());
    }
}
