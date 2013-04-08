<%@ page language="java" import="java.util.*,vo.*;" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>全部State1组信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<table>
			<tr>
				<td>ID</td><td>队伍名</td><td>队长</td><td>电话号码</td><td>所在大区</td><td>代表院系</td><td>胜场</td>
			</tr>
			<%
				HttpSession se = request.getSession();
				ArrayList<Team> tList = (ArrayList<Team>) se.getAttribute("tList");
				for (int i = 0; i < tList.size(); i++) {
					Team team = tList.get(i);
					Player captain = team.getP1();
			%>
			<tr>.setpServer
				<td><%=team.gettId()%></td><td><%=team.gettName()%></td><td><%=captain.getpName()%></td>
				<td><%=team.gettPhone()%></td><td><%=captain.getpServer()%></td><td><%=team.gettBelonging() %></td>
				<td><%=captain.getpWin()%></td>
			</tr>
			<%
				}
			%>
		</section>
	</table>
</body>
</html>
