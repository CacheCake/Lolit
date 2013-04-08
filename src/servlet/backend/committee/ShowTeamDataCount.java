package servlet.backend.committee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.TeamData;

import dao.interfaces.TeamDAO;
import dao.utils.DAOFactory;

public class ShowTeamDataCount extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TeamData teamData = new TeamData();
		TeamDAO tDAO = null;
		String url = "/Back_End/back_end_prompt.jsp";

		List<Integer> serverCount = new ArrayList<Integer>();
		List<Integer> winCount = new ArrayList<Integer>();
		List<Integer> instituteCount = new ArrayList<Integer>();
		List<Integer> areaCount = new ArrayList<Integer>();
		List<Integer> rankCount = new ArrayList<Integer>();
		try {
			tDAO = DAOFactory.getTeamDAOInstance();
			serverCount = tDAO.doSelectForServerCount();
			winCount = tDAO.doSelectForWinCount();
			instituteCount = tDAO.doSelectForInstituteCount();
			areaCount = tDAO.getBelongAreaCount(instituteCount);
			rankCount = tDAO.doSelectRankCount();

			teamData.setServerCount(serverCount);
			teamData.setWinCount(winCount);
			teamData.setInstituteCount(instituteCount);
			teamData.setAreaCount(areaCount);
			teamData.setRankCount(rankCount);

			request.setAttribute("teamData", teamData);

			url = "/Back_End/Committee/team_data.jsp";
		} catch (Exception e) {
			url = url + "?e=101";
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
