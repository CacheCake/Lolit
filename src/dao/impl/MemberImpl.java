package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;
import dao.interfaces.MemberDAO;
import dao.utils.DatabaseConn;

public class MemberImpl implements MemberDAO {

	private Connection conn = null; // 定义数据库连接对象
	private PreparedStatement pstmt = null; // 定义数据库操作对象
	private DatabaseConn dbc = null;// 定义数据库连接
	private ResultSet rs = null;

	public MemberImpl() throws Exception { // 设置数据库连接

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

	// 后台登录
	public boolean doSelectForSignIn(Member member) throws Exception {
		String mName = member.getmAcName();
		String mPwd = member.getmPwd();
		int mRole = member.getmRole();
		String sql = "";

		try {
			sql = "SELECT mPwd FROM lolit.member WHERE mAcName = '" + mName
					+ "' AND mRole <= '" + mRole + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("mPwd").equals(mPwd)) {
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

}
