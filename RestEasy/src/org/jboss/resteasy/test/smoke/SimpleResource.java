/**
 *  程序功能: RestEasy测试程序
 */
package org.jboss.resteasy.test.smoke;

import javax.ws.rs.ConsumeMime;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;
import javax.ws.rs.QueryParam;

public class SimpleResource {
	// 访问地址为:http://localhost:8080/RestEasy/rest/hello
	@GET
	@Path("/hello")
	@ProduceMime({ "text/plain" })
	public String getWild() {
		return "hello resteasy";
	}

	@GET
	@Path("basic")
	@ProduceMime({ "text/plain" })
	public String getBasic() {
		System.out.println("getBasic()");
		return "basic";
	}

	@PUT
	@Path("basic")
	@ConsumeMime({ "text/plain" })
	public void putBasic(String body) {
		System.out.println(body);
	}

	@GET
	@Path("queryParam")
	@ProduceMime({ "text/plain" })
	public String getQueryParam(@QueryParam("param") String param) {
		return param;
	}

	@GET
	@Path("matrixParam")
	@ProduceMime({ "text/plain" })
	public String getMatrixParam(@MatrixParam("param") String param) {
		return param;
	}

	@GET
	@Path("uriParam/{param}")
	@ProduceMime({ "text/plain" })
	public int getUriParam(@PathParam("param") int param) {
		return param;
	}
}