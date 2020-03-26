package controller;
import javax.servlet.http.HttpServletRequest;
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
public class RegisterController 
{
	@RequestMapping("/register")
	public String register(HttpSession session, Model model)
	{
		return "register";
	}
	@RequestMapping("/register/check")
	public String check(HttpServletRequest request,User user,HttpSession session, Model model) throws Exception
	{	 
		if(JDBC.sqlIp("select * from ip where status = '禁止' and ip='"+request.getRemoteAddr()+"'").size()!=0)
		{
			model.addAttribute("message1","您已被封禁");
			return "register";
		}
		User oldUser = JDBC.sqlUser("select * from user_account where username = '"+user.getUsername()+"'");
		if(oldUser!=null)
		{
			model.addAttribute("message1","用户名已存在");
			return "register";
		}
		else if(Pattern.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$", user.getUsername())==false)
		{
			model.addAttribute("message1","用户名不规范");
			return "register";
		}
		else if(Pattern.matches("^[0-9a-zA-Z]{6,12}$", user.getPassword())==false)
		{
			model.addAttribute("message1","密码不规范");
			return "register";
		}
		else if(!session.getAttribute("authcode").equals(user.getAuthcode()))
		{
			System.out.println(session.getAttribute("authcode")+" "+user.getAuthcode());
			model.addAttribute("message1","验证码不正确");
			return "register";
		}
		else
		{
			JavaMail mail = new JavaMail();
			mail.mailSend(user.getUsername());
			System.out.println("邮件已发送"+" "+mail.getCode());
			int code = mail.getCode();
			session.setAttribute("new", user);
			session.setAttribute("mailCode",String.valueOf(code));
			return "mail";
		}
	}	
}
