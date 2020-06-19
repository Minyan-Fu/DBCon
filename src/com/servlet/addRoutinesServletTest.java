package com.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import javax.servlet.http.*;

public class addRoutinesServletTest extends Mockito{

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("routineName")).thenReturn("11");
        when(request.getParameter("deviceId")).thenReturn("11");
        when(request.getParameter("userId")).thenReturn("11");
        when(request.getParameter("startTime")).thenReturn("2020-05-18 03:01:50");
        when(request.getParameter("endTime")).thenReturn("2020-05-18 03:01:51");
        
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new addRoutinesServlet().doGet(request,response);
        
        writer.flush();
        assertTrue(stringWriter.toString().contains("success"));
        }
    }
