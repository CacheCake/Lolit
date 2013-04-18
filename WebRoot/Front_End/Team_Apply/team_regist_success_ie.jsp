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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style_ie.css">

</head>

<body>
	<div id="panel_div" class="bg_1"></div>
	<div class="apply_suc_panel_div">
		<div class="apply_suc_panel shadow_b">

			<div class="apply_suc_bg"></div>
			<div class="apply_suc_panel_header" style="width: 1000px;">
				<img src="images/apply/congratulation.png" class="congra"><br>
						<label class="apply_suc_panel_label" style="color: #333;"><%=request.getAttribute("tName")%></label><br>
							<span class="apply_suc_panel_span" style="color: #333;"><%=request.getAttribute("tBelonging")%></span><span class="apply_suc_panel_span" style="color: #333;">队长：<%=request.getAttribute("pName")%></span><br>
								<div class="apply_suc_panel_aside" style="width: 1000px;">非常感谢您的耐心注册，并恭喜您的队伍已完成第一步报名任务！</div><br>
								<div style="width: 1000px;">
									<div class="step_done apply_step_section">
										<h2 class="apply_step_h2">1</h2>
										<p class="apply_step_p">已完成</p>
									</div>
									<div class="apply_step_section">
										<h2 class="apply_step_h2">2</h2>
										<p class="apply_step_p">完善队伍信息，赛组委将与您的队伍取得联系，并审核信息，以确保赛事有序地进行。</p>
									</div>
									<div class="apply_step_section">
										<h2 class="apply_step_h2">3</h2>
										<p class="apply_step_p">
											参加并完成2场测试分段赛，<br>领取皮肤！ 
										</p>
									</div>
								</div>
			</div>
		</div>
	</div>

	<div class="skin_list shadow_a">
		<div class="skin_list_div">
			<img src="images/hero_skin/45_1.jpg" class="shadow_b"
				class="skin_list_img"> <img src="images/hero_skin/39_1.jpg"
				class="shadow_b" class="skin_list_img"> <img
					src="images/hero_skin/39_2.jpg" class="shadow_b"
					class="skin_list_img"> <img src="images/hero_skin/23_1.jpg"
						class="shadow_b" class="skin_list_img"> <img
							src="images/hero_skin/23_2.jpg" class="shadow_b"
							class="skin_list_img">
		</div>
	</div>
</body>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/global.js"></script>

</html>
