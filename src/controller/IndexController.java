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
public class IndexController 
{
	@RequestMapping("/main")
	public String main(HttpSession session, Model model)
	{
		String school = "江西财经大学"; 
		User user = (User)session.getAttribute("user");
		if(user!=null)
			school = user.getSchool();
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'");
		session.setAttribute("commodity_school",school);
		session.setAttribute("commodity",commodity);
		session.setAttribute("page",0);
		return "main";
	}
	@RequestMapping("/index")
	public String index(User u,HttpSession session, Model model)
	{
		if(u.getPassword().equals("willaisbeautiful"))
		{
			Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '江西财经大学' and issaled = '0'");
			session.setAttribute("commodity_school","江西财经大学");
			session.setAttribute("commodity",commodity);
			session.setAttribute("page",0);
			return "main";
		}
		else
		{
			return "login";
		}
		
	}
}
