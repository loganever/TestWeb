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
@RequestMapping("/pageCar")
public class PageCarController 
{
	@RequestMapping("/pre")
	public String pre(HttpSession session, Model model)
	{
		session.setAttribute("pageCar",Math.min(0, Integer.parseInt(session.getAttribute("page").toString())-1));
		return "main";
	}
	@RequestMapping("/next")
	public String next(HttpSession session, Model model)
	{
		session.setAttribute("pageCar",Integer.parseInt(session.getAttribute("page").toString())+1);
		return "main";
	}
}