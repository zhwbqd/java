package com.hp.sbs.krtkova.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.hp.sbs.krtkova.service.CreateCharts;

/**
 * Servlet implementation class PieChartServlet
 */
public class ChartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChartsServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("image/jpeg");

		JFreeChart chart = null;
		String type = request.getParameter("type").trim();
		String name = request.getParameter("title").trim();
		if ("3dPieChart".equals(type)) {
			chart = CreateCharts.createPieChart("3D Pie Chart", true, name);
		} else if ("pieChart".equals(type)) {
			chart = CreateCharts.createPieChart("Pie Chart", false, name);
		} else if ("3dBarChart".equals(type)) {
			chart = CreateCharts.createBarChart("3D Bar Chart", true, name);
		} else if ("barChart".equals(type)) {
			chart = CreateCharts.createBarChart("Bar Chart", false, name);
		} else if ("timeSeriesChart".equals(type)) {
			chart = CreateCharts.createSeriesChart("TimeLine", "Sub Title", "数量（单位：万元）", true, "区域文本", 0, 0, name);
		} else if ("3dLineChart".equals(type)) {
			chart = CreateCharts.createLineChart("3D TimeLine", true, name);
		} else if ("lineChart".equals(type)) {
			chart = CreateCharts.createLineChart("TimeLine", false, name);
		}

		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f, chart, 1000, 694, null);

	}

}
