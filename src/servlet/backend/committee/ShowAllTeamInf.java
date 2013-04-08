package servlet.backend.committee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Team;
import dao.interfaces.TeamDAO;
import dao.utils.DAOFactory;

public class ShowAllTeamInf extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		ArrayList<Team> tList = new ArrayList<Team>();
		TeamDAO tDAO = null;
		String url = "/Back_End/back_end_prompt.jsp";

		try {

			tDAO = DAOFactory.getTeamDAOInstance();
			tList = (ArrayList<Team>) tDAO.doSelectForTeamList(1);

			request.setAttribute("tList", tList);
			url = "/Back_End/Committee/all_team_inf.jsp";
			System.out.println("teamList size:" + tList.size());

		} catch (Exception e) {
			url = url + "?e=GR101";
			e.printStackTrace();
		} finally {
			try {
				tDAO.closeDBC3();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
