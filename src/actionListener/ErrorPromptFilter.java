package actionListener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dao.interfaces.TeamActionDAO;
import dao.utils.DAOFactory;

public class ErrorPromptFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// 从cookie里面获取错误信息，分析后存入数据库
		long ip = 0;
		int eNumber = 0;

		TeamActionDAO teamActionDAO = null;
		try {
			teamActionDAO = DAOFactory.getTeamActionDAOInstance();
			if (teamActionDAO.doInsertErrorNumber(ip, eNumber)) {
				System.out.println("出错信息录入成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
