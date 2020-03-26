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
@RequestMapping("/school")
public class SchoolController 
{
	@RequestMapping("/jxufe")
	public String jxufe(HttpSession session, Model model)
	{
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '江西财经大学' and issaled = '0' ");
		session.setAttribute("commodity_school","江西财经大学");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/unc")
	public String unc(HttpSession session, Model model)
	{
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '南昌大学' and issaled = '0'");
		session.setAttribute("commodity_school","南昌大学");
		session.setAttribute("commodity",commodity);
		return "main";
	}
	@RequestMapping("/jxun")
	public String jxun(HttpSession session, Model model)
	{
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '江西师范大学' and issaled = '0'");
		session.setAttribute("commodity_school","江西师范大学");
		session.setAttribute("commodity",commodity);
		return "main";
	}
}
