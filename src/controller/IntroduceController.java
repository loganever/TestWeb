package controller;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.JDBC;
import entity.Commodity;
import entity.User;

@Controller
public class IntroduceController 
{
	@RequestMapping("/introduce")
	public String introduce(HttpSession session, Model model)
	{
		return "introduce";
	}
}
