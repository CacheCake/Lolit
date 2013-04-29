<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<base href="<%=basePath%>">

<title>IE Apply success</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="UTF-8">


<link rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/js/global.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>

<script>
	$(document).ready(checkBrowers());
</script>

</head>

<body>
	<div id="panel_div" class="bg_1"></div>
	<div class="apply_suc_panel_div">
		<div class="apply_suc_panel shadow_b">

			<div class="apply_suc_bg"></div>
			<div class="apply_suc_panel_header" style="width: 1000px;">
				<img src="images/apply/congratulation.png" class="congra"><br>
				<label class="apply_suc_panel_label" style="color: #333;"><%=session.getAttribute("tName")%></label><br>
				<span class="apply_suc_panel_span" style="color: #333;"><%=session.getAttribute("tBelonging")%></span><span
					class="apply_suc_panel_span" style="color: #333;">队长：<%=session.getAttribute("pName")%></span><br>
				<div class="apply_suc_panel_aside GRa">非常感谢您的耐心注册，并恭喜您的队伍已完成第一步报名任务！</div>
				<br>
				<div class="GRb">
					<div class="step_done apply_step_section radius_a shadow_a1">
						<h2 class="apply_step_h2">1</h2>
						<p class="apply_step_p">已完成</p>
					</div>
					<div class="apply_step_section radius_a shadow_a1">
						<h2 class="apply_step_h2">2</h2>
						<p class="">完善队伍信息，赛组委将与您的队伍取得联系，并审核信息，以确保赛事有序地进行。</p>
					</div>
					<div class="apply_step_section radius_a shadow_a1">
						<h2 class="apply_step_h2">3</h2>
						<p class="">
							参加并完成2场测试分段赛，<br>领取皮肤！
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="skin_list shadow_a">
		<div class="skin_list_div">
			<img src="images/hero_skin/45_1.jpg" class="shadow_b skin_list_img">
			<img src="images/hero_skin/39_1.jpg" class="shadow_b skin_list_img">
			<img src="images/hero_skin/39_2.jpg" class="shadow_b skin_list_img">
			<img src="images/hero_skin/23_1.jpg" class="shadow_b skin_list_img">
			<img src="images/hero_skin/23_2.jpg" class="shadow_b skin_list_img">
		</div>
	</div>
</body>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/global.js"></script>

</html>
