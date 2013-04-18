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

<title>IE Prompt</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style_ie.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/global.js"></script>

</style>
</head>

<body>
	<%
		int eCodes = 0;
		String eRemind = "很抱歉！这是一个未知的错误。";
		String eTodo = "您可通过本页面右侧所提供的方式告知我们，我们将第一时间回复您。";
		if (request.getParameter("e") != null) {
			eCodes = Integer.parseInt(request.getParameter("e"));
		}
	%>
	<div id="panel_div" class="bg_1"></div>
	<div class="apply_suc_panel_div">
	<div class="prompt_panel shadow_b">

		<button class="return_button" id="return_button"
			onclick="returnPage()"></button>
		<div class="prompt_bg">
			<div class="prompt_bg_header">
				<img src="images/apply/error_title.png" class="error_title"> <br>
				<div class="prompt_bg_article">
					<%
						switch (eCodes) {
						case 800:
							eRemind = "队伍验证失败!";
							break;
						case 801:
							eRemind = "队伍抽签失败!";
							break;
						case 802:
							eRemind = "已经抽签!";
							break;
						case 900:
							eRemind = "注册异常!";
							break;
						case 901:
							eRemind = "队伍名为空!";
							break;
						case 902:
							eRemind = "团队口令为空!";
							break;
						case 903:
							eRemind = "代表院系年级不能为空!";
							break;
						case 905:
							eRemind = "队伍名称已经存在!";
							break;
						case 906:
							eRemind = "学号已经存在!";
							break;
						case 907:
							eRemind = "验证码输入错误!";
							break;
						case 920:
							eRemind = "头像上传失败!";
							break;
						case 921:
							eRemind = "文件传输异常!";
							break;
						}
					%>
					<%=eRemind%></div>
				<img src="images/apply/than_todo.png" class="than_todo">
				<div class="eTodo_msg">
					<%
						switch (eCodes) {
						case 800:
							eTodo = "队伍验证失败!";
							break;
						case 801:
							eTodo = "队伍抽签失败!";
							break;
						case 802:
							eTodo = "已经抽签!";
							break;
						case 900:
							eTodo = "返回并重新注册，若依然不成功您可通过本页面右侧所提供的方式告知我们，我们将第一时间回复您。";
							break;
						case 901:
							eTodo = "队伍名为空!";
							break;
						case 902:
							eTodo = "团队口令为空!";
							break;
						case 903:
							eTodo = "代表院系年级不能为空!";
							break;
						case 905:
							eTodo = "队伍名称已经存在!";
							break;
						case 906:
							eTodo = "学号已经存在!";
							break;
						case 907:
							eTodo = "验证码输入错误!";
							break;
						case 920:
							eTodo = "头像上传失败!";
							break;
						case 921:
							eTodo = "文件传输异常!";
							break;
						}
					%>
					<div class="eTodo_msg_span"><%=eTodo%></div>
				</div>
			</div>


			<div class="prompt_con">
				<img src="images/apply/phone.png" class="prompt_con_img"></img> <img
					src="images/apply/weixin.png" class="prompt_con_img"></img> <img
					src="images/apply/weibo.png" class="prompt_con_img"></img>
			</div>


		</div>
	</div>
	</div>
</body>
</html>
