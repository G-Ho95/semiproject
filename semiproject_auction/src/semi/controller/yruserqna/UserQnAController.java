package semi.controller.yruserqna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import semi.dao.yr.BoardDao;
import semi.vo.yr.BoardVo;


/**@author yurae
 * 
 * @author yurae
 * 문의게시판
 * 리스트 가져오기
 * 답변 ajax json으로 가져오기
 *
 */
@WebServlet("/board/userQnA.do")
public class UserQnAController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원번호가져오기
		HttpSession session = req.getSession();
		int mnum = (Integer)session.getAttribute("m_num"); 
		
		BoardDao dao = new BoardDao();
		Map<BoardVo, String> qnalist = new LinkedHashMap<BoardVo, String>();
		int boardListSize = 0;
		
		try {

			ArrayList<BoardVo> list = dao.listBoard(mnum);
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
				String anwer = dao.listAnswer(boardVo.getB_num());
				System.out.println(anwer);
				qnalist.put(boardVo, anwer);
			}
			
		}catch (NullPointerException e) {
			// TODO: handle exception
			boardListSize = 0 ;
		}
		
		boardListSize = qnalist.size();
		
		req.setAttribute("BoardListSize", boardListSize);
		req.setAttribute("BoardList", qnalist);
//		req.setAttribute("header", "header.jsp");
		req.setAttribute("file", "/uboard/userQnA.jsp");
		req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//	    req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/board/userQnA.jsp").forward(req, resp);

	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		
//		String sbnum = req.getParameter("bnum");
//		int bnum =Integer.parseInt(sbnum);
//		
//		BoardDao dao = new BoardDao();
//		String answer = dao.listAnswer(bnum);		
//		String question = dao.listQuestion(bnum);
//		
//		JSONObject json = new JSONObject();
//		resp.setCharacterEncoding("utf-8");
//		PrintWriter out = resp.getWriter(); 
//		try {
//			if(answer == null) {
//				json.put("question", question);
//				json.put("answer", "잠시만 기다려주세요..");
//			}else {
//				json.put("question", question);					
//				json.put("answer", answer);					
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		out.print(json);
//	}
}
