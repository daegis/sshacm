﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.awt.Color" %>
<%@ page import="java.awt.Font" %>
<%@ page import="java.awt.Graphics" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%
    int width = 80;
    int height = 32;
    //create the image
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    // set the background color
    g.setColor(new Color(0xDCDCDC));
    g.fillRect(0, 0, width, height);
    // draw the border
    g.setColor(Color.black);
    g.drawRect(0, 0, width - 1, height - 1);
    // create a random instance to generate the codes
    Random rdm = new Random();
    String line = "WERTYPASDFGHJKZXCVBNM23456789";
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < 4; i++) {
        stringBuffer.append(line.charAt(rdm.nextInt(line.length())));
    }
    for (int i = 0; i < 50; i++) {
        int x = rdm.nextInt(width);
        int y = rdm.nextInt(height);
        g.drawOval(x, y, 0, 0);
    }
    // generate a random code
    String capstr = stringBuffer.toString();
    session.setAttribute("captcha", capstr);
    g.setColor(new Color(0, 100, 0));
    g.setFont(new Font("Candara", Font.BOLD, 24));
    g.drawString(capstr, 8, 24);
    g.dispose();
    response.setContentType("image/jpeg");
    out.clear();
    out = pageContext.pushBody();
    OutputStream strm = response.getOutputStream();
    ImageIO.write(image, "jpeg", strm);
    strm.close();
%>