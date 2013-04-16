package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.TeamAction;

import dao.interfaces.TeamActionDAO;
import dao.utils.DatabaseConn;

public class TeamActionImpl implements TeamActionDAO {

	private Connection conn = null; // 定义数据库连接对象
	private PreparedStatement pstmt = null; // 定义数据库操作对象
	private DatabaseConn dbc = null;// 定义数据库连接
	private ResultSet rs = null;

	public TeamActionImpl() throws Exception { // 设置数据库连接

		this.dbc = new DatabaseConn();// 实例化数据库连接
		this.conn = dbc.getConnection();// 获取数据库连接

	}

	public void closeDBC3() throws Exception {
		try {
			dbc.close(conn, pstmt, rs);
		} catch (Exception e) {
			throw e;
		}
	}

	public void closeDBC2() throws Exception {
		try {
			dbc.close(conn, pstmt);
		} catch (Exception e) {
			throw e;
		}
	}

	// 判断数据库中ip是否已经存在
	public boolean doSelectTeamActionIp(long ip) throws Exception {
		String sql = "";
		try {
			sql = "SELECT * FROM lolit.teamaction WHERE ip = '" + ip + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("欢迎再次访问");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;

	}

	// 向数据库录入用户操作信息，第一次访问
	public boolean doInsertTeamAction(TeamAction tAction) throws Exception {

		long ip = tAction.getIp();
		String firstTime = tAction.getTime();
		String firstBrowser = tAction.getBrowser();
		int registCount = tAction.getRegistCount();

		int inta = 0;
		String sql = "";
		try {
			sql = "INSERT INTO lolit.teamaction (ip,firstTime,firstBrowser,visitCount,registCount) VALUES ('"
					+ ip
					+ "','"
					+ firstTime
					+ "','"
					+ firstBrowser
					+ "','"
					+ registCount + "')";
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("用户第一次访问操作信息录入数据库成功！");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	// 向数据库录入用户操作信息，再次访问
	public boolean doInsertTeamActionAgain(TeamAction tAction) throws Exception {

		String lastTime = tAction.getTime();
		String lastBrowser = tAction.getBrowser();

		int inta = 0;
		String sql = "";
		try {
			sql = "INSERT INTO lolit.teamaction (lastTime,lastBrowser) VALUES ('"
					+ lastTime + "','" + lastBrowser + "')";
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("用户再次访问操作信息录入数据库成功！");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	// 更新用户注册成功次数
	public boolean doUpdateRegistSuccess(long ip) throws Exception {
		String sql = "";
		int registCount = 0;
		int inta = 0;
		try {
			sql = "SELECT registCount FORM lolit.teamaction WHERE ip = '" + ip
					+ "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				registCount = rs.getInt("registCount") + 1;
			}
			sql = "UPDATE lolit.teamaction SET registCount = '" + registCount
					+ "' WHERE ip = '" + ip + "' ";
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if(inta >0){
				System.out.println("数据库注册次数更新成功！");
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	// 向数据库录入用户注册异常信息
	public boolean doInsertErrorNumber(long ip, int eNumber) throws Exception {
		int inta = 0;
		String sql = "";
		try {
			sql = "INSERT INTO lolit.teamaction_has_errorprompt (teamaction_ip,errorPrompt_eNumber)VALUES('"
					+ ip + "','" + eNumber + "')";
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("注册异常信息录入成功！");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

}
