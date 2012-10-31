<%@ page import="java.io.File"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="com.hp.it.server.configuration.*" %>
<!DOCTYPE HTML>
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
<title>Query</title>
</head>
<body>
	<div id="wrapper">
		<%@ include file="header.jsp"%>
		<div id="box">
			<table>
				<tr>
					<td style="vertical-align: top;">
						<div class="panel">
							<div class="title">Report configuration</div>
							<div class="content">
								<form action="SonarReportConfiguration/U" method="post">
									<table style="margin: 0 0 0 10px">
										<%
											File workspace = new File(System.getProperty("user.home") + File.separator + ".quality-one"
													+ File.separator + "workspace");
											if (workspace.isDirectory()) {
												for (File project : workspace.listFiles()) {
													if (!project.isDirectory()) {
														continue;
													}
													String[] aggregate = project.getName().split("\\$");
													if (!aggregate[0].equalsIgnoreCase("aggregate") || !aggregate[1].equalsIgnoreCase(request.getParameter("Aggregate"))) {
														continue;
													}

													for (File file : project.listFiles()) {
														if (file.isFile() && file.getName().equalsIgnoreCase("config.properties")) {
															try {
																Properties properties = new Properties();
																properties.load(new FileInputStream(file));
										%>
										<tr>
												<td>Aggregate</td>
												<td><input type="text" name="AGGREGATE"
													value="<%=properties.getProperty(AggregateReportConstant.AGGREGATE)%>" size="48"
													readonly="readonly" /></td>
										</tr>
										<tr>
											<td>Default violation priority</td>
											<td><select name="DEFAULT_PRIORITY" style="width: 200px">
													<%
														if ("BLOCKER".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY))) {
													%>
													<option value="BLOCKER" selected="selected">Blocker</option>
													<%
														} else {
													%>
													<option value="BLOCKER">Blocker</option>
													<%
														}
																			if ("CRITICAL".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY))) {
													%>
													<option value="CRITICAL" selected="selected">Critical</option>
													<%
														} else {
													%>
													<option value="CRITICAL">Critical</option>
													<%
														}
																			if ("MAJOR".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY))) {
													%>
													<option value="MAJOR" selected="selected">Major</option>
													<%
														} else {
													%>
													<option value="MAJOR">Major</option>
													<%
														}
																			if ("MINOR".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY))) {
													%>
													<option value="MINOR" selected="selected">Minor</option>
													<%
														} else {
													%>
													<option value="MINOR">Minor</option>
													<%
														}
																			if ("INFO".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY))) {
													%>
													<option value="INFO" selected="selected">Info</option>
													<%
														} else {
													%>
													<option value="INFO">Info</option>
													<%
														}
													%>
											</select></td>
										</tr>
										<tr>
											<td>Default period</td>
											<td><select name="DEFAULT_PERIOD" style="width: 200px">
													<%
														if ("1".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PERIOD))) {
													%>
													<option value="1" selected="selected">Since
														previous</option>
													<%
														} else {
													%>
													<option value="1">Since previous</option>
													<%
														}
																			if ("2".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PERIOD))) {
													%>
													<option value="2" selected="selected">5 days</option>

													<%
														} else {
													%>
													<option value="2">5 days</option>
													<%
														}
																			if ("3".equalsIgnoreCase(properties.getProperty(AggregateReportConstant.DEFAULT_PERIOD))) {
													%>
													<option value="3" selected="selected">30 days</option>
													<%
														} else {
													%>
													<option value="3">30 days</option>
													<%
														}
													%>
											</select></td>
										</tr>
										<tr>
											<td>Default email recipients</td>
											<td><input type="text" name="DEFAULT_EMAIL(S)"
												value="<%=properties.getProperty(AggregateReportConstant.DEFAULT_EMAIL_LIST)%>"
												size="48" /></td>
										</tr>
										<tr>
											<td>Aggregate projects</td>
											<td><input type="text" name="<%=AggregateReportConstant.AGGREGATE_PROJECTS %>"
												value="<%=properties.getProperty(AggregateReportConstant.AGGREGATE_PROJECTS)%>"
												size="48" /></td>
										</tr>
										<tr>
											<td align="center" colspan="2"><input type="submit" value="Update" /></td>
										</tr>
										<%
											break;
															} catch (FileNotFoundException e) {
																e.printStackTrace();
															} catch (IOException e) {
																e.printStackTrace();
															}
														}
													}
												}
											}
										%>
									</table>
								</form>
							</div>
						</div>
					</td>
					<td style="vertical-align: top;">
						<div class="panel">
							<div class="title">State</div>
							<div class="content">
								<ul style="list-style-type: none;">
									<li><input type="url"
										value="http://<%=InetAddress.getLocalHost().getCanonicalHostName()%>:<%=request.getLocalPort()%><%=this.getServletContext().getContextPath()%>/AggregateViolationChangeReport?Aggregate=<%=request.getParameter("Aggregate")%>"
										size="54"></li>
									<li><input type="button" value="copy" /></li>
								</ul>
							</div>
						</div>
						<div class="panel">
							<div class="title">Manual Trigger</div>
							<div class="content">
								<table style="margin:0 0 0 30px;">
									<tr>
										<td><select name="period"  style="width: 200px">
												<option value="1" selected="selected">Since
													previous</option>
												<option value="2">Weekly</option>
												<option value="3">Monthly</option>
										</select></td>
										<td>Period</td>
									</tr>
									<tr>
										<td><select name="violationPriority"  style="width: 200px">
										<option value="Blocker">Blocker</option>
										<option value="Critical">Critical</option>
										<option value="Major" selected="selected">Major</option>
										<option value="Minor">Minor</option>
										<option value="Info">Info</option>
										</select></td>
										<td>Violation Priority</td>
									</tr>
									<tr>
										<td><input type="text" value=""  style="width: 300px"></select></td>
										<td>Additional recipients</td>
									</tr>
									<tr>
										<td colspan="2"><input type="submit" value="request" /></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="panel">
							<div class="title">Timer Trigger</div>
							<div class="content"  style="display:none;">
								<ul style="list-style-type: none;">
									<li>HISTORY1</li>
									<li>HISTORY1</li>
									<li>HISTORY1</li>
									<li>HISTORY1</li>
								</ul>
							</div>
						</div>
						<div class="panel">
							<div class="title">History</div>
							<div class="content"  style="display:none;">
								<ul style="list-style-type: none;">
									<li>HISTORY1</li>
									<li>HISTORY1</li>
									<li>HISTORY1</li>
									<li>HISTORY1</li>
								</ul>
							</div>
						</div>
						<div class="panel">
							<div class="title">Delete</div>
							<div class="content" style="display:none;">
								<button title="Delete" ></button>
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