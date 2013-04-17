package actionListener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import vo.TeamAction;

import dao.utils.DAOFactory;
import dao.interfaces.TeamActionDAO;

public class TeamActionDataFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// ��cookie�л�ȡ������Ϊ���ݣ�������������ݿ�

		Cookie[] cookies = (Cookie[]) ((HttpServletRequest) request)
				.getCookies();
		if (cookies == null) {
			filterChain.doFilter(request, response);
		} else {
			TeamAction tAction = new TeamAction();
			tAction.setIp(cookies[0].getValue());
			tAction.setBrowser(cookies[2].getValue());
			tAction.setTime(cookies[3].getValue());
			int registCount = Integer.parseInt(cookies[4].getValue());

			String ip = tAction.getIp();
			TeamActionDAO teamActionDAO = null;
			try {
				teamActionDAO = DAOFactory.getTeamActionDAOInstance();
				// �ж���Go���滹��ע��ɹ�����ץ��������Ϣ
				if (registCount == 0) {
					// �ж��û��Ƿ��ǵ�һ�η��ʱ���վ
					if (teamActionDAO.doSelectTeamActionIp(ip)) {
						if (teamActionDAO.doUpdateTeamActionAgain(tAction)) {
							System.out.println("�û��ٴβ�����Ϣ¼��ɹ���");
						}
					} else {
						if (teamActionDAO.doInsertTeamAction(tAction)) {
							System.out.println("�û���һ�β�������¼��ɹ���");
						}
					}
				} else {
					if (teamActionDAO.doUpdateRegistSuccess(ip)) {
						System.out.println("�û�ע��������³ɹ���");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			filterChain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
