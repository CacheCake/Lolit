package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;
import dao.interfaces.MemberDAO;
import dao.utils.DatabaseConn;

public class MemberImpl implements MemberDAO {

	private Connection conn = null; // �������ݿ����Ӷ���
	private PreparedStatement pstmt = null; // �������ݿ��������
	private DatabaseConn dbc = null;// �������ݿ�����
	private ResultSet rs = null;

	public MemberImpl() throws Exception { // �������ݿ�����

		this.dbc = new DatabaseConn();// ʵ�������ݿ�����
		this.conn = dbc.getConnection();// ��ȡ���ݿ�����

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

	// ��̨��¼
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
