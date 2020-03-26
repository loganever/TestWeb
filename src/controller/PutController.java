package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import service.JDBC;
import entity.Commodity;
import entity.Search;
import entity.User;

@Controller
@MultipartConfig
public class PutController 
{
	@RequestMapping(value = "/putnew", method = RequestMethod.POST)
	public String putnew(Commodity c,@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpSession session,Model model) throws IOException
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		if(c.getDetail().length()>400)
		{
			model.addAttribute("messageput","详细介绍字数过多");
			return "put";
		}
		else if(c.getCommodityName().length()>50)
		{
			model.addAttribute("messageput","名称字数过多");
			return "put";
		}
		else if(c.getIntroducation().length()>50)
		{
			model.addAttribute("messageput","简介字数过多");
			return "put";
		}
		int num = JDBC.sqlNum("select * from image");
		JDBC.sql("update image set id = '"+(num+1)+"' where id = '"+num+"'");
		String numStr = String.valueOf(num);
		System.out.println(request.getServletContext().getRealPath( "/images/" ));
		file.transferTo(new File(request.getServletContext().getRealPath( "/images/"+numStr+".jpg" )));
		JDBC.sql("insert into commodity values('"+numStr+"','"+c.getCommodityName()+"','"+user.getUsername()+"','"+c.getPrice()+"','"+getDate().split(" ")[0]+"','"+c.getLocation()+"','"+numStr+".jpg','"+c.getIntroducation()+"','"+c.getSchool()+"','0','"+c.getCommodityClass()+"','"+c.getDetail()+"')");
		String school = (String)session.getAttribute("school");
		if(school==null)
			school = user.getSchool();
		user.setShoppingRecord(JDBC.sqlCommodity("select * from commodity where saler_username='"+user.getUsername()+"'"));
		session.setAttribute("user",user);
		session.setAttribute("commodity",JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'"));
		return "main";
	}
	@RequestMapping("/put")
	public String put(Commodity c,HttpSession session,MultipartFile multipartFile)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		return "put";
	}
	public static String getDate() 
    {
    	Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化为日期/时间字符串
        String cc=sdf.format(d);
        return cc;
	}
}