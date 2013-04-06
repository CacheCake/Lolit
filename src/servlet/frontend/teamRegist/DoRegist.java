package servlet.frontend.teamRegist;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Player;
import vo.Team;
import dao.interfaces.TeamDAO;
import dao.utils.DAOFactory;

public class DoRegist extends HttpServlet {

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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String pathF = request.getContextPath()
				+ "/Front_End/front_end_prompt.jsp";
		String path = null;

		TeamDAO tDAO = null;
 
		HttpSession se = request.getSession();
		String rand = (String) se.getAttribute("rand");  //		获取session中德验证码
        String input = request.getParameter("rand");   //获取输入的验证码
        //验证验证码
        if (rand.equals(input)) {  
           
                } else {
                	System.out.println("验证码不匹配");
                	path = pathF + "?e=906";
                	response.sendRedirect(path);
                }  

		try {

			tDAO = DAOFactory.getTeamDAOInstance();
			// 获取team_tId
			long tId;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String CurrentTime = formatter.format(new java.util.Date());
			Random rd = new Random();
			CurrentTime = CurrentTime + rd.nextInt(999);
			tId = Long.parseLong(CurrentTime);

			String tName = request.getParameter("tName"); // 获得队伍名称
			// 后台验证，映射到相应的错误代号
			if (tName == null || "".equals(tName)) {
				path = pathF + "?e=901";
			}
			String tPwd1 = request.getParameter("tPwd1");
			String tPwd2 = request.getParameter("tPwd2");
			if (tPwd1 == null || "".equals(tPwd1) || tPwd2 == null
					|| "".equals(tPwd2) || !tPwd1.equals(tPwd2)) {
				path = pathF + "?e=902";
			}

			long tPhone = Long.parseLong(request.getParameter("tPhone")); // 获得队伍联系电话

			String tGrade = request.getParameter("tGrade");
			String tMajor = request.getParameter("tMajor"); // 所属专业 select标签
															// 得到的是value值
			// if(tGrade == null || "".equals(tGrade) || tMajor == null ||
			// "".equals(tMajor)){
			// path = pathF + "?e=903";
			// }
			String tBelonging = tGrade + " " + tMajor; // 获得所属院系年级信息

			if (tBelonging == null || "".equals(tBelonging)) {
				path = pathF + "?e=903";
			}
			// String tZeros = request.getParameter("tZero"); //
			// 是否愿意参加0级赛，1表示愿意，0表示不愿意

			// if (tZeros == null || "".equals(tZeros)) {
			// path = pathF + "?e=904";
			// }
			// boolean tZero = true; // 是否愿意参加0级赛,默认愿意
			// if (tZeros.equals("0")) {
			// tZero = false;
			// }

			String[] pBench = request.getParameterValues("pBench"); // 是否有替补队员

			int j = 1; // j为队员个数
			if (pBench != null) { // 如果有替补就增加队员个数
				for (int i = 0; i < pBench.length; i++) {
					j++;
				}
			}

			ArrayList<Player> players = new ArrayList<Player>(); // 用来存放队员信息
			for (int k = 0; k < j; k++) {

				Player p = new Player();
				p.setpName(request.getParameter("pName" + (k + 1))); //
				p.setpId(Integer.parseInt(request.getParameter("pId" + (k + 1)))); // 必须所有队员的学号都填
				p.setpGender(request.getParameter("pGender" + (k + 1)));
				// p.setpDormitory(request.getParameter("pBuilding" + (k+1)) +
				// " " + request.getParameter("pRoom" + (k+1)));
				// p.setpLolExp(request.getParameter("pLolExp" + (k+1)));
				p.setpServer(request.getParameter("pServer" + (k + 1)));
				p.setpWin(Integer.parseInt(request.getParameter("pWin"
						+ (k + 1))));
				p.setpRank(Integer.parseInt(request.getParameter("pRank"
						+ (k + 1))));
				players.add(p);
			}

			Team team = new Team();
			team.settId(tId);
			team.settName(tName);
			team.settPwd(tPwd1);
			team.settPhone(tPhone);
			team.settBelonging(tBelonging);
			// team.settZero(tZero);
			team.setP1(players.get(0));
			// team.setP2(players.get(1));
			// team.setP3(players.get(2));
			// team.setP4(players.get(3));
			// team.setP5(players.get(4));

			/*
			 * if (j == 6) { team.setP6(players.get(5)); } else if (j == 7) {
			 * team.setP6(players.get(5)); team.setP7(players.get(6)); }
			 */

			// 传入team执行插入方法
			if (path != null) {
				System.out.println("有错误");
			} else {
				try {
					if (tDAO.doSelectTeamName(tName)) {
						if (tDAO.doInsert(team)) {
							path = request.getContextPath()
									+ "/Front_End/Team_Apply/team_regist_success.jsp";
							// V1.0 以后版本可能提供上传Logo注册，即多一步跳转。
							// path = request.getContextPath() +
							// "/Front_End/Team_Apply/team_logo_upload.jsp?tId="
							// + tId;
						} else {
							System.out.println("注册失败！");
							path = pathF;
						}
					} else {
						path = pathF + "?e=905";
					}
				} catch (Exception e) {
					throw e;
				}
			}
		} catch (Exception e) {
			path = pathF + "?e=900";
			e.printStackTrace();
		} finally {
			try {
				tDAO.closeDBC3();
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(path);
		}
	}

}
