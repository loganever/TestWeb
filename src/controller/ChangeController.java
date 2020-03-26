package controller;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import service.JDBC;
import entity.Commodity;
import entity.Search;
import entity.User;

@Controller
public class ChangeController 
{
	@RequestMapping("/change")
	public String change(HttpServletRequest request,User u,HttpSession session,Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		if(Pattern.matches("^[0-9a-zA-Z]{6,12}$", u.getPassword())==false)
		{
			model.addAttribute("message0","密码格式不对");
			request.getRequestDispatcher("person/change");
			return "person";
		}
		else
		{
			String pass = u.getPassword();
			JDBC.sql("update user_account set password = '"+pass+"',school = '"+u.getSchool()+"',grade = '"+u.getGrade()+"',sex = '"+u.getSex()+"' where username = '"+user.getUsername()+"' ");
			return "person";
		}
		
	}
}