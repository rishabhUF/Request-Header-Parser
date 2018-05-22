package com.rishabh.HeaderParser.web;

import com.rishabh.HeaderParser.domain.UserRequestHeader;
import com.rishabh.HeaderParser.service.RequestHeaderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class RequestHeaderControllerTest {

    private MockMvc mockMvc;

    @Mock
    RequestHeaderService requestHeaderService;

    @InjectMocks
    RequestHeaderController requestHeaderController;


    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(requestHeaderController).build();
    }

    @Test
    public void testRequestHeaderController() throws Exception{
        UserRequestHeader userRequestHeader = setUserRequestHeader();
        when(requestHeaderService.getRequestHeader(any())).thenReturn(userRequestHeader);
        mockMvc.perform(get("/get/userRequestHeader"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("ipAddress").value("216.175.30.50"))
                .andDo(print());

    }

    private UserRequestHeader setUserRequestHeader(){
        String ipAddress = "216.175.30.50";
        String language = "en-US";
        String operatingSystem = "Windows NT 10.0; Win64; x64";
        UserRequestHeader userRequestHeader = new UserRequestHeader(ipAddress,language,operatingSystem);
        return userRequestHeader;
    }
}
