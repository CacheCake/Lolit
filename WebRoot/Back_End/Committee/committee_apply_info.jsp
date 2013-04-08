<%@ page language="java" import="java.util.*,vo.*;" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>Lolit 仪表盘 - 赛组委</title>

<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style_b.css">

</head>

<body onload="panelHeight(1)" onresize="panelHeight(1)">
	<%@include file="/Back_End/back_end_header.jsp"%>

	<article class="HR_panel shadow_a" id="panel">

		<header class="HR_title">Team application information</header>

		<section class="member_div shadow_c">
			<iframe src="Back_End/Committee/team_list.jsp" height="497px"
				width="720px" frameborder="0" id="member_list"></iframe>
		</section>

		<section class="op_div">
			<input type="search" class="search">
			<button class="search_button">S</button>
			<a href="committee/ShowTeamDataCount"><button class="addMember">ShowTeamData</button>
			</a>
		</section>

		<%
			ArrayList<Team> tList = (ArrayList<Team>) request
					.getAttribute("tList");
			HttpSession se = request.getSession();
			se.setAttribute("tList", tList);
			for (int i = 0; i < tList.size(); i++) {
				Team team = tList.get(i);
				Player captain = team.getP1();
		%>
		<section class="details_div" id="dPanel<%=i%>">
			<ul>
				<li><label>ID：</label><span><%=team.gettId()%></span></li>
				<li><label>队伍名：</label><span><%=team.gettName()%></span></li>
				<li><label>代表院系：</label><span><%=team.gettBelonging()%></span></li>
				<li><label>所在大区：</label><span><%=captain.getpServer()%></span></li>
				<li><label>平均胜场：</label><span><%=captain.getpWin()%></span></li>
				<li><label>队长：</label><span><%=captain.getpName()%></span></li>
				<li><label>联系电话：</label><span><%=team.gettPhone()%></span></li>
				<li><label>0级赛：</label><span><%=team.gettZero()%></span></li>
			</ul>
		</section>
		<%
			}
		%>
	</article>

</body>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/global.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/back_end.js"></script>

</html>
