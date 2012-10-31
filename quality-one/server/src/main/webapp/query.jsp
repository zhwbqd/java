<%@ page import="java.io.File"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="com.hp.it.encrypt.*"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="meta.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("div.panel div.title").click(function() {
			$(this).parent().find("div.content").slideToggle();
		});
		$("#copyButton").click(function() {
			var urlText = $("#url").val();
			$.copy(urlText);
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
											String groupId = "";
											String artifactId = "";
											if (workspace.isDirectory())
											{
												for (File project : workspace.listFiles())
												{
													if (!project.isDirectory())
													{
														continue;
													}
													if (!project.getName().replace('$', ':').equalsIgnoreCase(request.getParameter("KEE")))
													{
														continue;
													}

													for (File file : project.listFiles())
													{
														if (file.isFile() && file.getName().equalsIgnoreCase("config.properties"))
														{
															try
															{
																Properties properties = new Properties();
																properties.load(new FileInputStream(file));
																String[] kee = properties.getProperty("KEE").split("\\:");
																groupId = kee[0];
																artifactId = kee[1];
										%>
										<div class="">
											<tr>
												<td>Identifier</td>
												<td><input type="text" name="KEE"
													value="<%=properties.getProperty("KEE")%>" size="48"
													readonly="readonly" /></td>
											</tr>
											<tr>
												<td>Sonar url</td>
												<td><input type="url" name="PORTAL_URL"
													value="<%=properties.getProperty("PORTAL_URL")%>" size="48" /></td>
											</tr>
										</div>
										<tr>
											<td>Datebase driver</td>
											<td><input type="text" name="DB_DRIVER"
												value="<%=properties.getProperty("DB_DRIVER")%>" size="48" /></td>
										</tr>
										<tr>
											<td>Database url</td>
											<td><input type="text" name="DB_URL"
												value="<%=properties.getProperty("DB_URL")%>" size="48" /></td>
										</tr>
										<tr>
											<td>Database user</td>
											<td><input type="text" name="DB_USER"
												value="<%=properties.getProperty("DB_USER")%>" size="48" /></td>
										</tr>
										<tr>
											<td>Database password</td>
											<td><input type="password" name="DB_PWD"
												value="<%=new EncryptUtil().decrypt(properties.getProperty("DB_PWD")) %>" size="48" /></td>
										</tr>
										<tr>
											<td>SVN url</td>
											<td><input type="text" name="VER_URL"
												value="<%=properties.getProperty("VER_URL")%>" size="48" /></td>
										</tr>
										<tr>
											<td>SVN user</td>
											<td><input type="text" name="VER_USER"
												value="<%=properties.getProperty("VER_USER")%>" size="48" /></td>
										</tr>
										<tr>
											<td>SVN password</td>
											<td><input type="password" name="VER_PWD"
												value="<%=new EncryptUtil().decrypt(properties.getProperty("VER_PWD")) %>" size="48" /></td>
										</tr>
										<tr>
											<td>VER_PRV</td>
											<td><input type="text" name="VER_PRV"
												value="<%=properties.getProperty("VER_PRV")%>" size="48" /></td>
										</tr>
										<tr>
											<td>VER_PPWD</td>
											<td><input type="text" name="VER_PPWD"
												value="<%=properties.getProperty("VER_PPWD")%>" size="48" /></td>
										</tr>
										<tr>
											<td>Default violation priority</td>
											<td><select name="DEFAULT_PRIORITY" style="width: 200px">
													<%
														if ("BLOCKER".equalsIgnoreCase(properties.getProperty("DEFAULT_PRIORITY")))
																			{
													%>
													<option value="BLOCKER" selected="selected">Blocker</option>
													<%
														} else
																			{
													%>
													<option value="BLOCKER">Blocker</option>
													<%
														}
																			if ("CRITICAL".equalsIgnoreCase(properties.getProperty("DEFAULT_PRIORITY")))
																			{
													%>
													<option value="CRITICAL" selected="selected">Critical</option>
													<%
														} else
																			{
													%>
													<option value="CRITICAL">Critical</option>
													<%
														}
																			if ("MAJOR".equalsIgnoreCase(properties.getProperty("DEFAULT_PRIORITY")))
																			{
													%>
													<option value="MAJOR" selected="selected">Major</option>
													<%
														} else
																			{
													%>
													<option value="MAJOR">Major</option>
													<%
														}
																			if ("MINOR".equalsIgnoreCase(properties.getProperty("DEFAULT_PRIORITY")))
																			{
													%>
													<option value="MINOR" selected="selected">Minor</option>
													<%
														} else
																			{
													%>
													<option value="MINOR">Minor</option>
													<%
														}
																			if ("INFO".equalsIgnoreCase(properties.getProperty("DEFAULT_PRIORITY")))
																			{
													%>
													<option value="INFO" selected="selected">Info</option>
													<%
														} else
																			{
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
														if ("1".equalsIgnoreCase(properties.getProperty("DEFAULT_PERIOD")))
																			{
													%>
													<option value="1" selected="selected">Since
														previous</option>
													<%
														} else
																			{
													%>
													<option value="1">Since previous</option>
													<%
														}
																			if ("2".equalsIgnoreCase(properties.getProperty("DEFAULT_PERIOD")))
																			{
													%>
													<option value="2" selected="selected">5 days</option>

													<%
														} else
																			{
													%>
													<option value="2">5 days</option>
													<%
														}
																			if ("3".equalsIgnoreCase(properties.getProperty("DEFAULT_PERIOD")))
																			{
													%>
													<option value="3" selected="selected">30 days</option>
													<%
														} else
																			{
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
												value="<%=properties.getProperty("DEFAULT_EMAIL(S)")%>"
												size="48" /></td>
										</tr>
										<tr>
											<td align="center" colspan="2"><input type="submit"
												value="Update" /></td>
										</tr>
										<%
											break;
															} catch (FileNotFoundException e)
															{
																e.printStackTrace();
															} catch (IOException e)
															{
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
									<li><input type="url" id="url"
										value="<%=InetAddress.getLocalHost().getCanonicalHostName()%>:<%=request.getLocalPort()%><%=this.getServletContext().getContextPath()%>/SonarViolationChangeReport?groupId=<%=groupId%>&artifactId=<%=artifactId%>"
										size="54"></li>
									<li><input type="button" value="copy the link" id="copyButton"/></li>
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
										<td colspan="2"><input id="request" type="submit" value="Request" /></td>
									</tr>
								</table>
							</div>
						</div>
						<script type="text/javascript">
							$(document).ready(function() {
									$("#request").click(function(){
										window.open("<%=InetAddress.getLocalHost().getCanonicalHostName()%>:<%=request.getLocalPort()%><%=this.getServletContext().getContextPath()%>/SonarViolationChangeReport?groupId=<%=groupId%>&artifactId=<%=artifactId%>");
							})
						});
						</script>
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
								<form action="SonarReportConfiguration/D" method="post">
									<input type="hidden" name="KEE" value="<%=request.getParameter("KEE") %>" /> 
									<input type="submit" value="Delete" />
								</form>
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