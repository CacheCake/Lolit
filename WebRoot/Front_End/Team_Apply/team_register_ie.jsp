<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>IE Registration Form</title>
    
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

	<div class="register_div">

		<form id="teamRegister" action="frontend/teamRegist/DoRegist"
			method="post">



			<div class="register_team shadow_b">
				<div>
					<hgroup>
						<h2>Registration Form</h2>
						<h3>报名表</h3>
					</hgroup>
				</div>

				<ul class="ul_form_a">
					<li><h4>队伍信息 Team Form</h4></li>
					<li class="li_form"><label for="tName" class="label_reg">队伍名称:</label><input
						type="text" id="tName" name="tName" placeholder="如：大神联盟">
					</li>
					<li class="li_form"><label for="tPwd1" class="label_reg">队伍口令:</label><input
						type="password" id="tPwd1" name="tPwd1"
						placeholder="6-18位英文、数字或下划线">
					</li>
					<li class="li_form"><label for="tPwd2" class="label_reg">确认口令:</label><input
						type="password" id="tPwd2" name="tPwd2" placeholder="重复">
					</li>

					<br>
					<li class="li_form"><label for="tGrade" class="label_reg">代表院系年级:</label><select
						id="tGrade" name="tGrade">
							<option>09</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
					</select><label>级</label><select id="tMajor" name="tMajor">
							<option value="艺术服装">艺术服装</option>
							<option value="计算机软件">计算机软件</option>
							<option value="纺织">纺织</option>
							<option value="材料科学">材料科学</option>
							<option value="环境化学">环境化学</option>
							<option value="机械工程">机械工程</option>
							<option value="电气自动化">电气自动化</option>
							<option value="电子信息">电子信息</option>
							<option value="理">理</option>
							<option value="管理">管理</option>
							<option value="经济">经济</option>
							<option value="人文与法">人文与法</option>
							<option value="外国语">外国语</option>
							<option value="应用技术">应用技术</option>
							<option value="国际教育">国际教育</option>
					</select><label>学院</label></li>
					<li class="li_form"><label for="pWin1" class="label_reg">队员平均胜场:</label><input
						type="text" name="pWin1" id="pWin1"><label style="margin: 0 0 0 88px;">场</label>
					</li>
					<li class="li_form"><label for="pServer1" class="label_reg">队员大号所在大区:</label><select
						id="pServer1" name="pServer1">
							<option value="无30级号">均无30级号</option>
							<option value="比尔吉沃特">比尔吉沃特</option>
							<option value="艾欧尼亚">艾欧尼亚</option>
							<option value="教育网专区">教育网专区</option>
							<option value="征服之海">征服之海</option>
							<option value="守望之海">守望之海</option>
							<option value="影流">影流</option>
							<option value="均衡教派">均衡教派</option>
							<option value="水晶之痕">水晶之痕</option>
							<option value="钢铁烈阳">钢铁烈阳</option>
							<option value="暗影岛">暗影岛</option>
							<option value="黑色玫瑰">黑色玫瑰</option>
							<option value="裁决之地">裁决之地</option>
							<option value="雷瑟守备">雷瑟守备</option>
							<option value="巨神峰">巨神峰</option>
							<option value="扭曲丛林">扭曲丛林</option>
							<option value="战争学院">战争学院</option>
							<option value="恕瑞玛">恕瑞玛</option>
							<option value="皮尔特沃夫">皮尔特沃夫</option>
							<option value="无畏先锋">无畏先锋</option>
							<option value="班德尔城">班德尔城</option>
							<option value="弗雷尔卓德">弗雷尔卓德</option>
							<option value="诺克萨斯">诺克萨斯</option>
							<option value="德玛西亚">德玛西亚</option>
							<option value="祖安">祖安</option>
					</select>
					</li>
					<li class="li_form"><label for="pRank1" class="label_reg">队伍rank值:</label><input
						type="text" id="pRank1" name="pRank1" placeholder="可填队长rank来代表本队">
					</li>
				</ul>

				<div class="div_ps">
					<p>说明：</p>
					<ul>
						<li class="ul_ps">为了赛事的公平公正，本赛事采取实名制报名，请各队伍如实填写相关信息，我们稍后会进一步审核。</li>
					</ul>
				</div>

			</div>


			<div class="register_player shadow_b">
				<ul class="ul_form_a">
					<li class="li_form"><h4>队长 Captain</h4></li>
					<br>
					<li class="li_form"><label for="pId1" class="label_reg">学号:</label><input
						type="text" id="pId1" name="pId1" placeholder="xxxxxx-xxxx">
					</li>
					<li class="li_form"><label for="pName1" class="label_reg">姓名:</label><input
						type="text" id="pName1" name="pName1" placeholder="请填写真实姓名">
					</li>
					<li class="li_form"><label for="pGender1" class="label_reg">性别:</label><input
						type="radio" id="pGender1" value="1" 
						name="pGender1" class="bool_range" checked="checked"><label id="pGender1_label">男</label>
						<input type="radio"  id="pGender1" value="2" 
						name="pGender1" class="bool_range"><label id="pGender1_label">女</label>
					</li>
					<li class="li_form"><label for="tPhone" class="label_reg">联系电话:</label><input
						type="tel" id="tPhone" name="tPhone" placeholder="xxx-xxxx-xxxx">
					</li>
					<br><br>
					<li class="li_form checkNum"><label for="tCheckNum" class="label_reg">验证码:</label><input
						type="text" id="tCheckNum" name="tCheckNum" maxlength=4 ><img
						id="codeImg" border=0
						src="/Lolit/Front_End/Team_Apply/identifyingcode_image.jsp" style="margin: 0 0 0 150px;"><a
						href="javascript:void(0)" onclick="reloadCheckCode()" >换一张</a>
					</li>
				</ul>
				<div class="div_ps">
					<p>说明：</p>
					<ul>
						<li class="ul_ps">第一届的赛事将先收集同学们的情况，由赛组委统计分析后再制定赛程、赛制。</li>
						<li class="ul_ps">宿舍地址用来防止比赛中有非团队成员参与作弊，我们会有工作人员随机抽查；我也保证此类信息的隐私保护。</li>
					</ul>
				</div>

			</div>

			<div class="register_submit opacityDeep shadow_b">
				<button type="submit" class="submit_reg"
					onclick="return teamRegCheck();">
					<span class="button_span_a">确认</span><span class="button_span_b">撸翻对面宿舍！</span>
				</button>
			</div>
		</form>
	</div>
	<div class="footer"> </div>


</body>

<!--GR刷新验证码  -->
<script language='JavaScript'>
	function reloadCheckCode() {
		document.getElementById("codeImg").src = "/Lolit/Front_End/Team_Apply/identifyingcode_image.jsp?"
				+ Math.random();
	}
</script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/global.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/check.js"></script>

</html>