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
import entity.Search;
import entity.User;

@Controller
public class SearchController 
{
	@RequestMapping("/search")
	public String main(Search se,HttpSession session, Model model)
	{
		Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_name like '%"+se.getContent()+"%' and issaled = '0'");
		session.setAttribute("commodity",commodity);
		return "main";
	}
}
