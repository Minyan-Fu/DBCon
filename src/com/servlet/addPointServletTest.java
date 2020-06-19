package com.servlet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import javax.servlet.http.*;

public class addPointServletTest extends Mockito{

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("routineId")).thenReturn("1");
        when(request.getParameter("content")).thenReturn("1");
        when(request.getParameter("location")).thenReturn("POINT(1,1)");
        
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new addPointServlet().doGet(request,response);
        
        writer.flush();
        assertTrue(stringWriter.toString().contains("insert successfully"));
        }
    }
