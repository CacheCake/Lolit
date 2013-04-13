<%@ page language="java" import="java.util.*,vo.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'showTeamData.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style_b.css">

</head>

<body>
	<%
		TeamData teamData = (TeamData) request.getAttribute("teamData");
		List<Integer> serverCount = new ArrayList<Integer>();
		List<Integer> winCount = new ArrayList<Integer>();
		List<Integer> instituteCount = new ArrayList<Integer>();
		List<Integer> areaCount = new ArrayList<Integer>();
		List<Integer> rankCount = new ArrayList<Integer>();

		serverCount = teamData.getServerCount();
		winCount = teamData.getWinCount();
		instituteCount = teamData.getInstituteCount();
		areaCount = teamData.getAreaCount();
		rankCount = teamData.getRankCount();
	%>
	<table>
		<%
			String[] server = { "无30级号", "比尔吉沃特", "艾欧尼亚", "教育网专区", "征服之海",
					"守望之海", "影流", "均衡教派", "水晶之痕", "钢铁烈阳", "暗影岛", "黑色玫瑰",
					"裁决之地", "雷瑟守备", "巨神峰", "扭曲丛林", "战争学院", "恕瑞玛", "皮尔特沃夫",
					"无畏先锋", "班德尔城", "弗雷尔卓德", "诺克萨斯", "德玛西亚", "祖安" };
			String[] institute = { "艺术服装", "计算机软件", "纺织", "材料科学", "环境化学",
					"机械工程", "电气自动化", "电子信息", "理", "管理", "经济", "人文与法", "外国语",
					"应用技术", "国际教育" };
		%>
		<th>各服务器人数</th>
		<tr>
			<%
				for (int i = 0; i < 5; i++) {
			%>
			<td><%=server[i]%></td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 0; i < 5; i++) {
			%>
			<td><%=serverCount.get(i)%>队</td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 5; i < 10; i++) {
			%>
			<td><%=server[i]%></td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 5; i < 10; i++) {
			%>
			<td><%=serverCount.get(i)%>队</td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 10; i < 15; i++) {
			%>
			<td><%=server[i]%></td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 10; i < 15; i++) {
			%>
			<td><%=serverCount.get(i)%>队</td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 15; i < 20; i++) {
			%>
			<td><%=server[i]%></td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 15; i < 20; i++) {
			%>
			<td><%=serverCount.get(i)%>队</td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 20; i < 25; i++) {
			%>
			<td><%=server[i]%></td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 20; i < 25; i++) {
			%>
			<td><%=serverCount.get(i)%>队</td>
			<%
				}
			%>
		</tr>
	</table>
	<table>
		<th>各胜场区间人数</th>
		<tr>
			<td>0~99</td>
			<td>100~499</td>
			<td>500~999</td>
			<td>1000以上</td>
		</tr>
		<tr>
			<%
				for (int i = 0; i < 4; i++) {
			%>
			<td><%=winCount.get(i)%></td>
			<%
				}
			%>
		</tr>
	</table>
	<table>
		<th>各学院人数</th>
		<tr>
			<%
				for (int i = 0; i < institute.length; i++) {
			%>
			<td><%=institute[i]%>学院</td>
			<%
				}
			%>
		</tr>
		<tr>
			<%
				for (int i = 0; i < institute.length; i++) {
			%>
			<td><%=instituteCount.get(i)%></td>
			<%
				}
			%>
		</tr>
	</table>
	<table>
		<th>各区人数</th>
		<tr>
			<td>东苑</td>
			<td>西苑</td>
			<td>软件园</td>
		</tr>
		<%
			for (int i = 0; i < areaCount.size(); i++) {
		%>
		<td><%=areaCount.get(i)%></td>
		<%
			}
		%>
	</table>
	<table border="1">
		<th>各rank区间人数</th>
		<tr>
			<td>无rank值</td>
			<td>500~1000</td>
			<td>1001~1300</td>
			<td>1301~1500</td>
			<td>1501~1600</td>
			<td>1601~1800</td>
			<td>1800~3000</td>
		</tr>
		<tr>
			<%
				for (int i = 0; i < rankCount.size(); i++) {
			%>
			<td><%=rankCount.get(i)%></td>
			<%
				}
			%>
		</tr>
	</table>
</body>
</html>
