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

	private Connection conn = null; // 定义数据库连接对象
	private PreparedStatement pstmt = null; // 定义数据库操作对象
	private DatabaseConn dbc = null;// 定义数据库连接
	private ResultSet rs = null;

	public TeamImpl() throws Exception { // 设置数据库连接

		this.dbc = new DatabaseConn();// 实例化数据库连接
		this.conn = dbc.getConnection();// 获取数据库连接

	}

	//查询队伍名称是否已经存在
	public boolean doSelectTeamName(String tName) throws Exception {

		String teamName = tName;
		String sql = "select * from Lolit.team where tName = '" + teamName
				+ "' ";// 查看队伍名是否已经存在

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("队伍名已经存在");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	//查询队长学号是否已经存在
	public boolean doSelectPlayerId(int pId) throws Exception {

		String sql = "select * from Lolit.player where pId = '" + pId + "' ";// 查看队长学号是否已经存在

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("学号已经存在");
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

	//插入队伍注册信息
	public boolean doInsert(Team team) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false; // 判断是否注册成功
		boolean teamflag = false; // 判断队伍信息插入是否成功
		boolean playerflag = false; // 判断队员信息插入是否成功

		Long tId = team.gettId();
		String tName = team.gettName();
		String tPwd = team.gettPwd();
		long tPhone = team.gettPhone();
		String tBelonging = team.gettBelonging();
		// boolean tZeros = team.gettZero();
		// int tZero = 0;
		// if (tZeros) {
		// tZero = 1;
		// }

		int pId;
		String pName = null;
		String pGender = null;
		String pDormitory = null;
		String pLolExp = null;
		String pServer = null;
		int pRank;
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
		// 先插入队伍信息
		// teamsql = "select * from Lolit.team where tName = '" + tName +
		// "' ";// 查看队伍名是否已经存在
		// System.out.println(teamsql);
		int inta = 0; // 判断team信息是否插入数据库成功
		int pinta = 0; // 判断player信息是否插入数据库成功
		try {
			// rs = pstmt.executeQuery();
			// if (rs.next()) {
			// System.out.println("队伍名称已存在！");
			// } else {
			// System.out.println(tZero);
			teamsql = "insert into Lolit.team (tId,tName,tPwd,tPhone,tBelonging,tState) values ('"
					+ tId
					+ "','"
					+ tName
					+ "','"
					+ tPwd
					+ "','"
					+ tPhone
					+ "','" + tBelonging + "','1')"; // 插入队伍信息sql语句
			System.out.println(teamsql);
			pstmt = conn.prepareStatement(teamsql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("队伍信息录入成功！");
				teamflag = true;
			}
			// 再插入队员信息,使用循环插入所有队员信息
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
				pRank = p.getpRank();

				playersql = "insert into Lolit.player (pId,pName,pGender,pDormitory,pLolExp,pServer,pWin,team_tId,pRank) values ('"
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
						+ "','" + pWin + "','" + tId + "','" + pRank + "')";
				System.out.println("插入队员：" + playersql);
				pinta = pstmt.executeUpdate(playersql);
				if (pinta > 0) {
					System.out.println("队员信息录入成功！");
					playerflag = true;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		// 如果队员信息和队伍信息都插入成功，则返回注册成功
		if (teamflag && playerflag) {
			flag = true;
		}
		return flag;
	}

	//查询队伍是否登录
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

	//查询是否已经抽签
	public boolean doSelectForIfLotted(Long tId) throws Exception {

		// 999为默认空值，表示没抽签
		int tOrder = 999;

		// 验证是否可以抽签
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

	//录入队伍抽签号
	public boolean doInsertOrder(Long tId) throws Exception {

		int tOrder;

		// 获取所有已抽签的抽签号
		String sqlGetNotNullOrder = "SELECT tOrder FROM team WHERE tOrder <>'999'";
		// 插入抽签号
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

				// 获得唯一的抽签号，通过与已经有的号比较，如果所有的已抽签号都与随机数不相等，付f=1，并跳出最外层循环。
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

	//查询抽签后的信息
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

	// 参数state =1时为注册完成，=2时为审核通过，=3时为正式队伍
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

	// 查看各服务器报名人数
	public List<Integer> doSelectForServerCount() throws Exception {
		String[] servers = { "无30级号", "比尔吉沃特", "艾欧尼亚", "教育网专区", "征服之海", "守望之海",
				"影流", "均衡教派", "水晶之痕", "钢铁烈阳", "暗影岛", "黑色玫瑰", "裁决之地", "雷瑟守备",
				"巨神峰", "扭曲丛林", "战争学院", "恕瑞玛", "皮尔特沃夫", "无畏先锋", "班德尔城", "弗雷尔卓德",
				"诺克萨斯", "德玛西亚", "祖安" };
		String sql = "";
		List<Integer> serverCount = new ArrayList<Integer>();

		try {
			for (int i = 0; i < servers.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pServer = '"
						+ servers[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					serverCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return serverCount;
	}

	// 查看各胜场区间人数
	public List<Integer> doSelectForWinCount() throws Exception {
		List<Integer> winCount = new ArrayList<Integer>();
		String sql = "";
		// 胜场区间
		int[] min = { 0, 100, 500, 1000 };
		int[] max = { 100, 500, 1000, 9999 };

		try {
			for (int i = 0; i < 4; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pWin >= '"
						+ min[i] + "' AND pWin < '" + max[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					winCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return winCount;
	}

	// 查看各学院报名人数
	public List<Integer> doSelectForInstituteCount() throws Exception {
		List<Integer> instituteCount = new ArrayList<Integer>();
		String[] institute = { "%艺术服装", "%计算机软件", "%纺织", "%材料科学", "%环境化学",
				"%机械工程", "%电气自动化", "%电子信息", "%理", "%管理", "%经济", "%人文与法",
				"%外国语", "%应用技术", "%国际教育" };
		String sql = "";

		try {
			for (int i = 0; i < institute.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.team WHERE tBelonging LIKE '"
						+ institute[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					instituteCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}

		return instituteCount;
	}

	// 查看各区报名人数
	public List<Integer> getBelongAreaCount(List<Integer> instituteCount)
			throws Exception {

		// 0%艺术服装 1%计算机软件 2%纺织 3%材料科学 4%环境化学
		// 5%机械工程 6%电气自动化 7%电子信息 8%理 9%管理
		// 10%经济 11%人文与法 12%外国语 13%应用技术 14%国际教育
		List<Integer> areaCount = new ArrayList<Integer>();
		int eastCount = 0, westCount = 0, northCount = 0;
		eastCount = instituteCount.get(3) + instituteCount.get(4)
				+ instituteCount.get(5) + instituteCount.get(6)
				+ instituteCount.get(7) + instituteCount.get(0)
				+ instituteCount.get(14);
		westCount = instituteCount.get(8) + instituteCount.get(9)
				+ instituteCount.get(10) + instituteCount.get(11)
				+ instituteCount.get(12);
		northCount = instituteCount.get(2) + instituteCount.get(1);
		areaCount.add(eastCount);
		areaCount.add(westCount);
		areaCount.add(northCount);
		return areaCount;
	}

	// 查看各rank区间人数
	public List<Integer> doSelectRankCount() throws Exception {
		List<Integer> rankCount = new ArrayList<Integer>();
		String sql = "";
		// rank区间
		int[] min = { 0, 500, 1000, 1300, 1500, 1600, 1800 };
		int[] max = { 1, 1000, 1300, 1500, 1600, 1800, 2800 };

		try {
			for (int i = 0; i < min.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pRank >= '"
						+ min[i] + "' AND pRank < '" + max[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					rankCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return rankCount;
	}

}
