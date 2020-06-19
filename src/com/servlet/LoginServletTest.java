package com.servlet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import javax.servlet.http.*;

public class LoginServletTest extends Mockito{

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("account")).thenReturn("12"); 
        when(request.getParameter("password")).thenReturn("123"); 
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new LoginServlet().doGet(request,response);
        
        writer.flush();
        assertTrue(stringWriter.toString().contains("200:login successfully"));
        }
    }
