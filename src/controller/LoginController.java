package controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.JDBC;
import entity.Commodity;
import entity.Ip;
import entity.User;

@Controller
public class LoginController 
{
	@RequestMapping("/login")
	public String login(HttpSession session, Model model)
	{
		return "login";
	}
	@RequestMapping("/quit")
	public String quit(HttpSession session, Model model)
	{
		session.setAttribute("user", null);
		session.setAttribute("school", null);
		session.setAttribute("person_page", null);
		session.setAttribute("authcode", null);
		session.setAttribute("commodity", null);
		session.setAttribute("car", null);
		session.setAttribute("page", null);
		session.setAttribute("pageCar", null);
		return "login";
	}
	@RequestMapping("/login/check")
	public String check(HttpServletRequest request,User user,HttpSession session, Model model)
	{
		String auth = (String)session.getAttribute("authcode");
		User newUser = JDBC.sqlUser("select * from user_account where username = '"+user.getUsername()+"'");
		if(newUser==null)
		{
			model.addAttribute("message","用户名不存在");
			return "login";
		}
		else if(!user.getAuthcode().equals(auth))
		{
			model.addAttribute("message","验证码错误");
			return "login";
		}
		else if(!newUser.getPassword().equals(user.getPassword()))
		{
			model.addAttribute("message","密码错误");
			return "login";
		}
		else
		{
			if(newUser.getPrivilege().equals("用户"))
			{
				String ip = request.getRemoteAddr();
				Vector<Ip> queryIp = JDBC.sqlIp("select * from ip where ip = '"+ip+"'");
				if(queryIp.size()==0)
				{
					JDBC.sql("insert into ip values('"+ip+"','正常','"+getDate()+"','1')");
				}
				else
				{
					Ip queryIp1 = queryIp.get(0);
					if(queryIp1.getStatus().equals("禁止"))
					{
						model.addAttribute("message","您已被封禁");
						return "login";
					}
					else
					{
						JDBC.sql("update ip set sum = '"+(queryIp1.getSum()+1)+"',lasttime='"+getDate()+"' where ip = '"+queryIp1.getIpAddr()+"'");
					}
				}
				JDBC.sql("update user_account set usual_ip = '"+ip+"' where username = '"+newUser.getUsername()+"'");
				String school = newUser.getSchool(); 
				Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity where commodity_school = '"+school+"' and issaled = '0'");
				newUser.setShoppingRecord(JDBC.sqlCommodity("select * from commodity where saler_username='"+newUser.getUsername()+"'"));
				newUser.setGeZi(JDBC.sqlGeZi("select * from gezi where username = '"+newUser.getUsername()+"'"));
				session.setAttribute("commodity",commodity);
				session.setAttribute("user", newUser);
				session.setAttribute("page", 0);
				session.setAttribute("pageCar", 0);
				session.setAttribute("car",new Vector<Commodity>());
				return "main";
			}
			else
			{
				Vector<Commodity> commodity= JDBC.sqlCommodity("select * from commodity");
				Vector<Ip> vIp = JDBC.sqlIp("select * from ip");
				session.setAttribute("ip", vIp);
				session.setAttribute("user", newUser);
				session.setAttribute("commodity",commodity);
				return "manage";
			}
		}	
	}	
	public static String getDate() 
    {
    	Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化为日期/时间字符串
        String cc=sdf.format(d);
        return cc;
	}
}
