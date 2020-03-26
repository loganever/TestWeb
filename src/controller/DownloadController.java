package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PageAttributes.MediaType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import service.JDBC;
import service.JavaPDF;
import entity.Commodity;
import entity.Search;
import entity.User;

@Controller 
public class DownloadController {
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,HttpSession session,Model model) throws IOException 
    {
    	User user = (User)session.getAttribute("user");
    	if(user==null)
    		return null;
    	Vector<Commodity> shopCar = (Vector<Commodity>)session.getAttribute("car");
    	try {
			JavaPDF.exPDF(shopCar, user,request.getServletContext().getRealPath( "/html/" + user.getUsername() + ".pdf" ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        File file = new File(request.getServletContext().getRealPath( "/html/" + user.getUsername() + ".pdf" ));
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;
    }
    @RequestMapping("/car")
    public String car(HttpServletRequest request,HttpSession session,Model model) throws IOException 
    {
    	User user = (User)session.getAttribute("user");
    	if(user==null)
    		return "login";
    	return "car";
    }
    @RequestMapping("/addCar")
    public String addCar(@RequestParam(value ="id") String sId,HttpServletRequest request,HttpSession session,Model model) throws IOException 
    {
    	User user = (User)session.getAttribute("user");
    	if(user==null)
    		return "login";
    	Vector<Commodity> commoditys = (Vector<Commodity>)session.getAttribute("commodity");
    	Vector<Commodity> car = (Vector<Commodity>)session.getAttribute("car");
    	int x = 0;
    	for(int i = 0;i<commoditys.size();i++)
    	{
    		if(commoditys.get(i).getCommodityId()==Integer.parseInt(sId))
    		{
    			x = i;
    			break;
    		}
    	}
    	if(car==null)
    		car = new Vector();
    	car.add(commoditys.get(x));
    	return "car";
    }
}
