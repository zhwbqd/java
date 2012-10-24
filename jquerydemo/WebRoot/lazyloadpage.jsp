<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Lazy Load, jQuery 插件延迟加载图片效果演示页-scorpinz.com</title>
<script type="text/javascript" src="jslib/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jslib/jquery.lazyload.js"></script>
</head>
<body>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dq9doyhvv7j.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dq9dp8ewmdj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww1.sinaimg.cn/large/920dbc6ejw1dq9dpm1wb7j.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww4.sinaimg.cn/large/920dbc6ejw1dq9dqd7jcpj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww3.sinaimg.cn/large/920dbc6ejw1dq9dqp3ua2j.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dq9dr1eof8j.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww1.sinaimg.cn/large/920dbc6ejw1dq9drddxrcj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dq9drmpi7aj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dq9drvo7kvj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dqeq30rm0sj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dqeq3o9af2j.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dqeq4ezen8j.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww1.sinaimg.cn/large/920dbc6ejw1dqeq51j5cgj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dqeq6ytqyxj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww2.sinaimg.cn/large/920dbc6ejw1dqeq6dlszmj.jpg" width="535"></p>
<p><img class="lazy" src="images/pixel.gif" style="background:url(images/loading.gif) no-repeat center;" data-original="http://ww4.sinaimg.cn/large/920dbc6ejw1dqeq5pyc3tj.jpg" width="535"></p>
<script type="text/javascript" charset="utf-8">
$("img.lazy").lazyload({ 
	threshold : 200,
    effect : "fadeIn"
});
</script>
</body>
</html>

