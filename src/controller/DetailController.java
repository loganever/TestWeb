package controller;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.JDBC;
import entity.*;


@Controller
public class DetailController
{
	@RequestMapping("/detail")
	public String manageJie(@RequestParam(value ="id") String tid,HttpServletRequest request,HttpSession session, Model model)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		int id = Integer.parseInt(tid);
		Vector<Commodity> v = (Vector<Commodity>)session.getAttribute("commodity");
		for(int i = 0;i<v.size();i++)
		{
			if(v.get(i).getCommodityId()==id)
			{
				session.setAttribute("commodity_detail",v.get(i) );
				break;
			}
		}
		return "detail";
	}
}
