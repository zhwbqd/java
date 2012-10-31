<!DOCTYPE HTML>
<html>
<head>
<%@ include file="meta.jsp"%>
<title>Creation</title>
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
								<form method="post" action="SonarReportConfiguration/C"
									autocomplete="on">
									<table class="contentBox">
										<tr>
											<td>Identifier</td>
											<td><input type="text" name="KEE" value="" size="48"
												placeholder="groupId:artifactId" /></td>
										</tr>
										<tr>
											<td>Sonar url</td>
											<td><input type="url" name="PORTAL_URL" value=""
												size="48" placeholder="http://localhost:9000" /></td>
										</tr>
										<tr>
											<td>Datebase driver</td>
											<td><input type="text" name="DB_DRIVER" value=""
												size="48" placeholder="com.mysql.jdbc.Driver" /></td>
										</tr>
										<tr>
											<td>Database url</td>
											<td><input type="text" name="DB_URL" value="" size="48"
												placeholder="jdbc:mysql://localhost:3306/sonar" /></td>
										</tr>
										<tr>
											<td>Database user</td>
											<td><input type="text" name="DB_USER" value="" size="48"
												placeholder="database username" /></td>
										</tr>
										<tr>
											<td>Database password</td>
											<td><input type="password" name="DB_PWD" value=""
												size="48" placeholder="database password" /></td>
										</tr>
										<tr>
											<td>SVN url</td>
											<td><input type="text" name="VER_URL" value="" size="48"
												placeholder="subversion repository url" /></td>
										</tr>
										<tr>
											<td>SVN user</td>
											<td><input type="text" name="VER_USER" value=""
												size="48" placeholder="subversion user" /></td>
										</tr>
										<tr>
											<td>SVN password</td>
											<td><input type="password" name="VER_PWD" value=""
												size="48" placeholder="subversion password" /></td>
										</tr>
										<tr>
											<td>VER_PRV</td>
											<td><input type="text" name="VER_PRV" value="" size="48"
												placeholder="true|false (If using private key)" /></td>
										</tr>
										<tr>
											<td>VER_PPWD</td>
											<td><input type="text" name="VER_PPWD" value=""
												size="48" placeholder="private key password" /></td>
										</tr>
										<tr>
											<td>Default violation priority</td>
											<td><select name="DEFAULT_PRIORITY" style="width: 200px">>
													<option value="BLOCKER">Blocker</option>
													<option value="CRITICAL">Critical</option>
													<option value="MAJOR" selected="selected">Major</option>
													<option value="MINOR">Minor</option>
													<option value="INFO">Info</option>
											</select></td>
										</tr>
										<tr>
											<td>Default period</td>
											<td><select name="DEFAULT_PERIOD" style="width: 200px">>
													<option value="1" selected="selected">Since
														previous</option>
													<option value="2">5 days</option>
													<option value="3">30 days</option>
											</select></td>
										</tr>
										<tr>
											<td>Default email recipients</td>
											<td><input type="text" name="DEFAULT_EMAIL(S)" value=""
												size="48" placeholder="Each address separated by comma" /></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><input type="submit"
												value="create" /></td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</td>
					<td style="vertical-align: top;">
						<div class="panel">
							<div class="title">Aggregate Report configuration</div>
							<div class="content">
								<form method="post" action="SonarReportConfiguration/C"
									autocomplete="on">
									<table class="contentBox">
										<tr>
											<td>Identifier</td>
											<td><input type="text" name="AGGREGATE" value=""
												size="48" placeholder="Identifier" /></td>
										</tr>
										<tr>
											<td>Default violation priority</td>
											<td><select name="DEFAULT_PRIORITY" style="width: 200px">>
													<option value="BLOCKER">Blocker</option>
													<option value="CRITICAL">Critical</option>
													<option value="MAJOR" selected="selected">Major</option>
													<option value="MINOR">Minor</option>
													<option value="INFO">Info</option>
											</select></td>
										</tr>
										<tr>
											<td>Default period</td>
											<td><select name="DEFAULT_PERIOD" style="width: 200px">>
													<option value="1" selected="selected">Since
														previous</option>
													<option value="2">5 days</option>
													<option value="3">30 days</option>
											</select></td>
										</tr>
										<tr>
											<td>Default email recipients</td>
											<td><input type="text" name="DEFAULT_EMAIL(S)" value=""
												size="48" placeholder="Each address separated by comma" /></td>
										</tr>
										<tr>
											<td>Aggregate projects</td>
											<td><input type="text" name=AGGREGATE_PROJECTS
												" value="" size="48"
												placeholder="Each project separated by asterisk (group1:artifact1*group2:artifact2)" /></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><input type="submit"
												value="create" /></td>
										</tr>
									</table>
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