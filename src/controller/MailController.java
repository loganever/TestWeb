package controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.JDBC;
import service.JavaMail;

import java.util.regex.*;

import entity.User;

@Controller
public class MailController 
{
	@RequestMapping("/mailauthcode")
	public String mailcheck(User user,HttpSession session, Model model)
	{	 
		User newUser = (User)session.getAttribute("new");
		if(user.getAuthcode().equals(session.getAttribute("mailCode")))
		{
			JDBC.sql("insert into user_account values('"+newUser.getUsername()+"','"+newUser.getPassword()+"','用户','"+newUser.getSex()+"','"+newUser.getGrade()+"','"+newUser.getSchool()+"','60')");
			return "login";
		}
		else
		{
			model.addAttribute("message1","邮箱验证码不正确");
			return "register";
		}
	}	
}
