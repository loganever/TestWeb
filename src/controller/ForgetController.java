package controller;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.JDBC;
import service.JavaMail;
import entity.Commodity;
import entity.User;

@Controller
public class ForgetController 
{
	@RequestMapping("/forget")
	public String forget(HttpSession session, Model model)
	{
		return "forget";
	}
	@RequestMapping("/forget/check")
	public String forgetCheck(User user,HttpSession session, Model model) throws Exception
	{
		String codes = (String)session.getAttribute("authcode");
		if(JDBC.sqlUser("select * from user_account where username = '"+user.getUsername()+"'")==null)
		{
			model.addAttribute("messageforget","此邮箱用户不存在");
			return "forget";
		}
		if(Pattern.matches("^[0-9a-zA-Z]{6,12}$", user.getPassword())==false)
		{
			model.addAttribute("messageforget","密码不规范");
			return "forget";
		}
		if(!user.getAuthcode().equals(codes))
		{
			model.addAttribute("messageforget","验证码不正确");
			return "forget";
		}
		JavaMail mail = new JavaMail();
		mail.mailSend(user.getUsername());
		System.out.println("邮件已发送"+" "+mail.getCode());
		int code = mail.getCode();
		session.setAttribute("forgetUser",user);
		session.setAttribute("mailCode",String.valueOf(code));
		return "mailForget";
	}
	@RequestMapping("/forget/authcodeCheck")
	public String forgetAuthcodeCheck(User user,HttpSession session,Model model) throws Exception
	{
		User tUser = (User)session.getAttribute("forgetUser");
		String mCode = (String)session.getAttribute("mailCode");
		if(!user.getAuthcode().equals(mCode))
		{
			model.addAttribute("messageforget","邮箱验证码不正确");
			return "forget";
		}
		JDBC.sql("update user_account set password = '"+tUser.getPassword()+"' where username = '"+tUser.getUsername()+"'");
		model.addAttribute("message","密码已修改");
		return "login";
	}
}
