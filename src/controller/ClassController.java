package controller;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.JDBC;
import entity.Commodity;
import entity.User;

@Controller
@RequestMapping("/class")
public class ClassController 
{
	@RequestMapping("/all")
	public String all(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		String school = (String)session.getAttribute("commodity_school");
		if(school==null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0' ");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/book")
	public String book(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		String school = (String)session.getAttribute("commodity_school");
		if(school==null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'  and class = '书'");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/ele")
	public String ele(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		String school = (String)session.getAttribute("commodity_school");
		if(school==null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'  and class = '电子产品'");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/food")
	public String food(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		String school = (String)session.getAttribute("commodity_school");
		if(school==null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'  and class = '食品'");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/life")
	public String life(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		String school = (String)session.getAttribute("commodity_school");
		if(school==null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0' and class = '生活用品' ");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/other")
	public String other(HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		String school = (String)session.getAttribute("commodity_school");
		if(school==null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0' and class = '其他'");
		session.setAttribute("commodity",commodity);
		return "main";
	}
}
