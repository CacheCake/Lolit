package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vo.Global;
import vo.Player;
import vo.Team;
import dao.interfaces.TeamDAO;
import dao.utils.DatabaseConn;

public class TeamImpl implements TeamDAO {

	private Connection conn = null; // �������ݿ����Ӷ���
	private PreparedStatement pstmt = null; // �������ݿ��������
	private DatabaseConn dbc = null;// �������ݿ�����
	private ResultSet rs = null;

	public TeamImpl() throws Exception { // �������ݿ�����

		this.dbc = new DatabaseConn();// ʵ�������ݿ�����
		this.conn = dbc.getConnection();// ��ȡ���ݿ�����

	}

	public boolean doSelectTeamName(String tName) throws Exception {

		String teamName = tName;
		String sql = "select * from Lolit.team where tName = '" + teamName
				+ "' ";// �鿴�������Ƿ��Ѿ�����

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("�������Ѿ�����");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}

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

	public boolean doInsert(Team team) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false; // �ж��Ƿ�ע��ɹ�
		boolean teamflag = false; // �ж϶�����Ϣ�����Ƿ�ɹ�
		boolean playerflag = false; // �ж϶�Ա��Ϣ�����Ƿ�ɹ�

		Long tId = team.gettId();
		String tName = team.gettName();
		String tPwd = team.gettPwd();
		long tPhone = team.gettPhone();
		String tBelonging = team.gettBelonging();
		boolean tZeros = team.gettZero();
		int tZero = 0;
		if (tZeros) {
			tZero = 1;
		}

		int pId;
		String pName = null;
		String pGender = null;
		String pDormitory = null;
		String pLolExp = null;
		String pServer = null;
		int pWin;

		String teamsql = "";
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(team.getP1());
		// players.add(team.getP2());
		// players.add(team.getP3());
		// players.add(team.getP4());
		// players.add(team.getP5());
		// if (team.getP6() != null) {
		// players.add(team.getP6());
		// if (team.getP7() != null) {
		// players.add(team.getP7());
		// }
		// }
		// �Ȳ��������Ϣ
		// teamsql = "select * from Lolit.team where tName = '" + tName +
		// "' ";// �鿴�������Ƿ��Ѿ�����
		// System.out.println(teamsql);
		int inta = 0; // �ж�team��Ϣ�Ƿ�������ݿ�ɹ�
		int pinta = 0; // �ж�player��Ϣ�Ƿ�������ݿ�ɹ�
		try {
			// rs = pstmt.executeQuery();
			// if (rs.next()) {
			// System.out.println("���������Ѵ��ڣ�");
			// } else {
			// System.out.println(tZero);
			teamsql = "insert into Lolit.team (tId,tName,tPwd,tPhone,tBelonging,tZero,tState) values ('"
					+ tId
					+ "','"
					+ tName
					+ "','"
					+ tPwd
					+ "','"
					+ tPhone
					+ "','" + tBelonging + "','" + tZero + "','1')"; // ���������Ϣsql���
			System.out.println(teamsql);
			pstmt = conn.prepareStatement(teamsql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("������Ϣ¼��ɹ���");
				teamflag = true;
			}
			// �ٲ����Ա��Ϣ,ʹ��ѭ���������ж�Ա��Ϣ
			for (int i = 0; i < players.size(); i++) {
				String playersql = "";
				Player p = new Player();
				p = players.get(i);
				pId = p.getpId();
				pName = p.getpName();
				pGender = p.getpGender();
				pDormitory = p.getpDormitory();
				pLolExp = p.getpLolExp();
				pServer = p.getpServer();
				pWin = p.getpWin();

				playersql = "insert into Lolit.player (pId,pName,pGender,pDormitory,pLolExp,pServer,pWin,team_tId) values ('"
						+ pId
						+ "','"
						+ pName
						+ "','"
						+ pGender
						+ "','"
						+ pDormitory
						+ "','"
						+ pLolExp
						+ "','"
						+ pServer
						+ "','" + pWin + "','" + tId + "')";
				System.out.println("�����Ա��" + playersql);
				pinta = pstmt.executeUpdate(playersql);
				if (pinta > 0) {
					System.out.println("��Ա��Ϣ¼��ɹ���");
					playerflag = true;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		// �����Ա��Ϣ�Ͷ�����Ϣ������ɹ����򷵻�ע��ɹ�
		if (teamflag && playerflag) {
			flag = true;
		}
		return flag;
	}

	public boolean doSelectForSignIn(Long teamId, String teamPwd)
			throws Exception {

		Long tId = teamId;
		String tPwd = teamPwd;

		String sql = "SELECT tPwd FROM team WHERE tId = '" + tId + "'";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("tPwd").equals(tPwd)) {
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return false;
	}

	public boolean doSelectForIfLotted(Long tId) throws Exception {

		// 999ΪĬ�Ͽ�ֵ����ʾû��ǩ
		int tOrder = 999;

		// ��֤�Ƿ���Գ�ǩ
		String sqlCheck = "SELECT tOrder FROM team WHERE tId = '" + tId
				+ "' AND tState = 2";

		try {
			pstmt = conn.prepareStatement(sqlCheck);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("tOrder") == tOrder) {
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	public boolean doInsertOrder(Long tId) throws Exception {

		int tOrder;

		// ��ȡ�����ѳ�ǩ�ĳ�ǩ��
		String sqlGetNotNullOrder = "SELECT tOrder FROM team WHERE tOrder <>'999'";
		// �����ǩ��
		String sqlInsertOrder = "";

		try {
			pstmt = conn.prepareStatement(sqlGetNotNullOrder);
			rs = pstmt.executeQuery();

			ArrayList<Integer> tOrderList = new ArrayList<Integer>();
			Random rd = new Random();

			if (rs.next()) {
				tOrderList.add(rs.getInt("tOrder"));
				while (rs.next()) {
					tOrderList.add(rs.getInt("tOrder"));
				}

				// ���Ψһ�ĳ�ǩ�ţ�ͨ�����Ѿ��еĺűȽϣ�������е��ѳ�ǩ�Ŷ������������ȣ���f=1�������������ѭ����
				while (true) {
					int t = rd.nextInt(Global.getTeamCount() - 1);
					int f = 0;
					for (int i = 0; i < tOrderList.size(); i++) {
						System.out.println(tOrderList.size());
						if (tOrderList.get(i) == t) {
							f = 0;
							break;
						}
						f = 1;
					}
					if (f == 1) {
						tOrder = t;
						break;
					}
				}
			} else {
				tOrder = rd.nextInt(Global.getTeamCount() - 1);
			}

			sqlInsertOrder = "UPDATE team SET tOrder = '" + tOrder
					+ "' WHERE tId = '" + tId + "'";

			pstmt = conn.prepareStatement(sqlInsertOrder);
			int inta = pstmt.executeUpdate();

			if (inta > 0) {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	public List<Team> doSelectForMatchList() throws Exception {

		String sql = "SELECT * FROM team WHERE tState = '2' ORDER BY tOrder";
		ArrayList<Team> tList = new ArrayList<Team>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Team team = new Team();
				team.settId(rs.getLong("tId"));
				team.settName(rs.getString("tName"));
				team.settLogo(rs.getString("tLogo"));
				team.settOrder(rs.getInt("tOrder"));
				tList.add(team);
			}
			return tList;
		} catch (Exception e) {
			throw e;
		}
	}

	// ����state =1ʱΪע����ɣ�=2ʱΪ���ͨ����=3ʱΪ��ʽ����
	public List<Team> doSelectForTeamList(int state) throws Exception {

		String sql = "SELECT * FROM team INNER JOIN player ON tId = team_tId WHERE tState = '"
				+ state + "'";
		System.out.println("teamList s1 sql:" + sql);

		ArrayList<Team> tList = new ArrayList<Team>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Team team = new Team();
				team.settId(rs.getLong("tId"));
				team.settName(rs.getString("tName"));
				team.settLogo(rs.getString("tLogo"));
				team.settOrder(rs.getInt("tOrder"));
				team.settPhone(rs.getLong("tPhone"));
				team.settZero(rs.getBoolean("tZero"));
				team.settBelonging(rs.getString("tBelonging"));
				for (int i = 0; i < 1; i++) {
					Player player = new Player();
					player.setpName(rs.getString("pName"));
					player.setpServer(rs.getString("pServer"));
					player.setpWin(rs.getInt("pWin"));

					switch (i + 1) {
					case 1:
						team.setP1(player);
						break;
					default:
						break;
					}
				}
				tList.add(team);
			}
			return tList;
		} catch (Exception e) {
			throw e;
		}
	}

	// �鿴����������������
	public List<Integer> doSelectForServerCount() throws Exception {
		String[] servers = { "��30����", "�ȶ�������", "��ŷ����", "������ר��", "����֮��", "����֮��",
				"Ӱ��", "�������", "ˮ��֮��", "��������", "��Ӱ��", "��ɫõ��", "�þ�֮��", "��ɪ�ر�",
				"�����", "Ť������", "ս��ѧԺ", "ˡ����", "Ƥ�����ַ�", "��η�ȷ�", "��¶���", "���׶�׿��",
				"ŵ����˹", "��������", "�氲" };
		String sql = "";
		List<Integer> serverCount = new ArrayList<Integer>();

		try {
			for (int i = 0; i < servers.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pServer = '"
						+ servers[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					serverCount.add(rs.getInt(0));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return serverCount;
	}

	// �鿴��ʤ����������
	public List<Integer> doSelectForWinCount() throws Exception {
		List<Integer> winCount = new ArrayList<Integer>();
		String sql = "";
		// ʤ������
		int[] min = { 0, 100, 500, 1000 };
		int[] max = { 100, 500, 1000, 9999 };

		try {
			for (int i = 0; i < 4; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pWin >= '"
						+ min[i] + "' AND pWin < '" + max[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					winCount.add(rs.getInt(0));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return winCount;
	}

	// �鿴��ѧԺ��������
	public List<Integer> doSelectForInstituteCount() throws Exception {
		List<Integer> instituteCount = new ArrayList<Integer>();
		String[] institute = { "%��֯ѧ��", "%���Ͽ�ѧ�빤��ѧԺ", "%�����뻯ѧ����ѧԺ", "%��е����ѧԺ",
				"%�������ѧ�����ѧԺ", "%�����������Զ���ѧԺ", "%��������Ϣ����ѧԺ", "%��ѧԺ", "%�������װѧԺ",
				"%����ѧԺ", "%����ѧԺ", "%�����뷨ѧԺ", "%�����ѧԺ", "%Ӧ�ü���ѧԺ����������ѧԺ", "%���ʽ���ѧԺ" };
		String sql = "";
		
		try{
			for(int i = 0;i<institute.length;i++){
				sql = "SELECT COUNT(*) FROM lolit.team WHERE tBelonging LIKE '"+institute[i]+"'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					instituteCount.add(rs.getInt(0));
				}
			}
		}catch (Exception e) {
			throw e;// TODO: handle exception
		}

		return instituteCount;
	}
	
	//�鿴������������
	public List<Integer> getBelongAreaCount(List<Integer> instituteCount) throws Exception {
		List<Integer> areaCount = new ArrayList<Integer>();
		try{
			
		}catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return areaCount;
	}
}
