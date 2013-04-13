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
		System.out.println(mRole);
		String sql = "";

		try {
			// 判断是否验证管理员或者超级管理员权限,赛组委
			if (mRole <= 2) {
				sql = "SELECT mPwd FROM lolit.member WHERE mAcName = '" + mName
						+ "' AND mRole <= '" + mRole + "'";
			} else {
				//验证营销官,传播人员登录,管理员和超级管理员可以登录
				sql = "SELECT mPwd FROM lolit.member WHERE mAcName = '" + mName
						+ "' AND (mRole = '" + mRole + "' or mRole < '2')";
			}
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
