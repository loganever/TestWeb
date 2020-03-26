package controller;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.JDBC;
import entity.Commodity;
import entity.Ip;
import entity.User;

@Controller
public class ManageController 
{
	@RequestMapping("/manage")
	public String manage(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		return "manage";
	}
	@RequestMapping("/manage/ip")
	public String manageIp(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		return "manageip";
	}
	@RequestMapping("/manage/user")
	public String manageUser(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		Vector<User> users = JDBC.sqlUsers("select * from user_account");
		session.setAttribute("users", users);
		return "manageuser";
	}
	@RequestMapping("/manage/jie")
	public String manageJie(@RequestParam(value ="ip") String vip,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		JDBC.sql("update ip set status = '正常' where ip = '"+vip+"' and status='禁止' ");
		Vector<Ip> vIp = JDBC.sqlIp("select * from ip");
		session.setAttribute("ip", vIp);
		request.getRequestDispatcher("/manageip");
		return "manage";
	}
	@RequestMapping("/manage/feng")
	public String managefeng(@RequestParam(value ="ip") String vip,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		JDBC.sql("update ip set status = '禁止' where ip = '"+vip+"' and status='正常' ");
		Vector<Ip> vIp = JDBC.sqlIp("select * from ip");
		session.setAttribute("ip", vIp);
		request.getRequestDispatcher("/manageip");
		return "manage";
	}
	@RequestMapping("/manage/commoditydelete")
	public String managedelete(@RequestParam(value ="id") String vid,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		int id = Integer.parseInt(vid);
		JDBC.sql("delete from commodity where commodity_id = '"+id+"' ");
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity");
		session.setAttribute("commodity", commodity);
		return "manage";
	}
	@RequestMapping("/manage/userdelete")
	public String userdelete(@RequestParam(value ="username") String username,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null||(!user.getPrivilege().equals("管理员")))
			return "login";
		JDBC.sql("delete from user_account where username = '"+username+"' ");
		Vector<User> users = JDBC.sqlUsers("select * from user_account");
		session.setAttribute("users", users);
		request.getRequestDispatcher("/manageuser");
		return "manage";
	}
}
