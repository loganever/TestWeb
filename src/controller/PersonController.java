package controller;
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
import entity.Search;
import entity.User;

@Controller
public class PersonController 
{
	@RequestMapping("/person")
	public String person(Search se,HttpSession session, Model model)
	{
		if(session.getAttribute("user")==null)
			return "login";
		session.setAttribute("person_page", "个人信息");
		return "person";
	}
	@RequestMapping("/person/cancalGeZi")
	public String cancalGezi(@RequestParam(value ="id") String sId,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		String school = (String)session.getAttribute("school");
		if(school==null)
			school = user.getSchool();
		if(user==null)
			return "login";
		int id = Integer.parseInt(sId);
		JDBC.sql("update gezi set username = '无' where id = '"+id+"' and username = '"+user.getUsername()+"'");
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'");
		user.setShoppingRecord(JDBC.sqlCommodity("select * from commodity where saler_username='"+user.getUsername()+"'"));
		user.setGeZi(JDBC.sqlGeZi("select * from gezi where username = '"+user.getUsername()+"'"));
		session.setAttribute("user",user);
		session.setAttribute("commodity", commodity);
		request.getRequestDispatcher("person/cage");
		return "person";
	}
	@RequestMapping("/person/cancal")
	public String cancal(@RequestParam(value ="id") String sId,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		String school = (String)session.getAttribute("school");
		if(school==null)
			school = user.getSchool();
		if(user==null)
			return "login";
		int id = Integer.parseInt(sId);
		JDBC.sql("delete from commodity where commodity_id = '"+id+"' and saler_username = '"+user.getUsername()+"'");
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'");
		user.setShoppingRecord(JDBC.sqlCommodity("select * from commodity where saler_username='"+user.getUsername()+"'"));
		user.setGeZi(JDBC.sqlGeZi("select * from gezi where username = '"+user.getUsername()+"'"));
		session.setAttribute("user",user);
		session.setAttribute("commodity", commodity);
		request.getRequestDispatcher("person/commodity");
		return "person";
	}
	@RequestMapping("/person/change")
	public String personChange(HttpSession session, Model model)
	{
		if(session.getAttribute("user")==null)
			return "login";
		session.setAttribute("person_page", "修改信息");
		return "person";
	}
	@RequestMapping("/person/commodity")
	public String personCommodity(HttpSession session, Model model)
	{
		if(session.getAttribute("user")==null)
			return "login";
		session.setAttribute("person_page", "我发布的商品");
		return "person";
	}
	@RequestMapping("/person/cage")
	public String personCage(HttpSession session, Model model)
	{
		if(session.getAttribute("user")==null)
			return "login";
		session.setAttribute("person_page", "我的格子铺");
		return "person";
	}
	@RequestMapping("/person/rent")
	public String personRent(HttpSession session, Model model)
	{
		if(session.getAttribute("user")==null)
			return "login";
		User user = (User)session.getAttribute("user");
		user.setShoppingRecord(JDBC.sqlCommodity("select * from commodity where saler_username='"+user.getUsername()+"'"));
		user.setGeZi(JDBC.sqlGeZi("select * from gezi where username = '"+user.getUsername()+"'"));
		session.setAttribute("user",user);
		session.setAttribute("person_page", "租用格子铺");
		return "person";
	}
	@RequestMapping("/person/rentGeZi")
	public String personGeZi(@RequestParam(value ="id") String sId,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		String school = (String)session.getAttribute("school");
		if(school==null)
			school = user.getSchool();
		if(user==null)
			return "login";
		int id = Integer.parseInt(sId);
		session.setAttribute("payid",id);
		return "pay";
	}
	@RequestMapping("/person/rentGeZiOK")
	public String personGeZiOK(HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		String school = (String)session.getAttribute("school");
		if(school==null)
			school = user.getSchool();
		if(user==null)
			return "login";
		int id = (int) session.getAttribute("payid");
		JDBC.sql("update gezi set username = '"+user.getUsername()+"' where id = '"+id+"' and username = '无'");
		//Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'");
		user.setShoppingRecord(JDBC.sqlCommodity("select * from commodity where saler_username='"+user.getUsername()+"'"));
		user.setGeZi(JDBC.sqlGeZi("select * from gezi where username = '"+user.getUsername()+"'"));
		session.setAttribute("user",user);
		//session.setAttribute("commodity", commodity);
		request.getRequestDispatcher("person/rent");
		return "person";
	}
}
