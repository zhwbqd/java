<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<html>
<head>
<%@ include file="meta.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("div.panel div.title").click(function() {
			$(this).parent().find("div.content").slideToggle();
		});
	});
</script>
<title>Quality-one Portal</title>
</head>
<body>
	<%
		List<String> artifactList = new ArrayList<String>();
		List<String> aggregateList = new ArrayList<String>();
		File workspace = new File(System.getProperty("user.home") + File.separator + ".quality-one"
				+ File.separator + "workspace");
		if (workspace.isDirectory()) {
			for (File project : workspace.listFiles()) {
				if (!project.isDirectory()) {
					continue;
				}
				int firstIndex = project.getName().indexOf('$');
				if (firstIndex == -1) {
					continue;
				}

				if ("aggregate".equalsIgnoreCase(project.getName().substring(0, firstIndex))) {
					aggregateList.add(project.getName().substring(project.getName().indexOf('$')+1));
					continue;
				}

				artifactList.add(project.getName().replace('$', ':'));
			}
		}
	%>
	<div id="wrapper">
		<%@ include file="header.jsp"%>
		<div id="box">
			<table>
				<tr>
					<td style="vertical-align: top;">
						<div class="panel">
							<div class="title">Violation change report</div>
							<div class="content">
								<ul>

									<%
										for (String title : artifactList) {
									%>
									<li style="margin: 5px 0 5px 0"><a
										href="query.jsp?KEE=<%=title%>"><%=title%></a></li>
									<%
										}
									%>
								</ul>
							</div>
						</div>
					</td>
					<td style="vertical-align: top;">
						<div class="panel">
							<div class="title">Aggregate violation change report</div>
							<div class="content">
								<ul>
									<%
										for (String title : aggregateList) {
									%>
									<li style="margin: 8px 0 8px 0"><a href="aggregate.jsp?Aggregate=<%=title%>"><%=title%></a></li>
									<%
										}
									%>

								</ul>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>