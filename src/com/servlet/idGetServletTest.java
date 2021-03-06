package com.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import javax.servlet.http.*;

public class idGetServletTest extends Mockito{

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("userName")).thenReturn("1");        
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new idGetServlet().doGet(request,response);
        
        writer.flush();
        assertNotNull(stringWriter.toString());
        }
    }
