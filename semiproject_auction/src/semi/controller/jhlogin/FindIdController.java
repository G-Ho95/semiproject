package semi.controller.jhlogin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.LoginDao;
@WebServlet("/login/findId.jh")
public class FindIdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String m_name=req.getParameter("m_name");
		String m_email=req.getParameter("m_email");
		System.out.println("1이름:"+m_name);
		System.out.println("1이메일:"+m_email);
		
		LoginDao dao=LoginDao.getLoginDao();
		String id=dao.findId(m_name, m_email);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		if(id!=null) {//아이디가 있을 경우
			json.put("msg", "ok");
			json.put("id", id);
		}else {
			json.put("msg", "no");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
