package com.hp.sbs.krtkova.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

/**
 * @author zhawenbi
 * 
 */
public class CreateCharts {

	/**
	 * Create pie chart
	 * 
	 * @param title
	 * @param is3D
	 * @param serviceName
	 * @return
	 * @throws IOException
	 */
	public static JFreeChart createPieChart(String title, boolean is3D, String serviceName) throws IOException {
		JFreeChart chart = null;
		if (is3D) {
			chart = ChartFactory.createPieChart3D(title, // 图表标题
					pieDataset(serviceName), // 数据集
					true, // 是否显示图例
					true, // 是否显示工具提示
					true // 是否生成URL
					);
		} else {
			chart = ChartFactory.createPieChart(title, // 图表标题
					pieDataset(serviceName), // 数据集
					true, // 是否显示图例
					true, // 是否显示工具提示
					true // 是否生成URL
					);
		}

		// 设置标题字体,为了防止中文乱码：必须设置字体
		chart.setTitle(new TextTitle(title, new Font("Arial", Font.ITALIC, 22)));
		// 设置图例的字体,为了防止中文乱码：必须设置字体
		chart.getLegend().setItemFont(new Font("Arial", Font.BOLD, 12));

		// 获取饼图的Plot对象(实际图表)
		PiePlot plot = (PiePlot) chart.getPlot();
		// 图形边框颜色
		plot.setBaseSectionOutlinePaint(Color.orange);
		// 图形边框粗细
		plot.setBaseSectionOutlineStroke(new BasicStroke(0.0f));
		// 设置饼状图的绘制方向，可以按顺时针方向绘制，也可以按逆时针方向绘制
		plot.setDirection(Rotation.ANTICLOCKWISE);
		// 设置绘制角度(图形旋转角度)
		plot.setStartAngle(70);
		// 设置突出显示的数据块
		// plot.setExplodePercent("One", 0.1D);
		// 设置背景色透明度
		plot.setBackgroundAlpha(0.7F);
		// 设置前景色透明度
		plot.setForegroundAlpha(0.65F);
		// 设置区块标签的字体==为了防止中文乱码：必须设置字体
		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		// 扇区分离显示,对3D图不起效
		if (is3D)
			plot.setExplodePercent(pieDataset(serviceName).getKey(3), 0.1D);
		// 图例显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}\r\n({2}) ", NumberFormat.getNumberInstance(),
				new DecimalFormat(" 0.00% ")));
		// 指定显示的饼图为：圆形(true) 还是椭圆形(false)
		plot.setCircular(true);
		// 没有数据的时候显示的内容
		plot.setNoDataMessage(" 找不到可用数据 ");

		// 设置鼠标悬停提示
		plot.setToolTipGenerator(new StandardPieToolTipGenerator());
		// 设置热点链接
		// plot.setURLGenerator(new StandardPieURLGenerator("#"));

		return chart;

	}

	/**
	 * Create data set of pieChart
	 * 
	 * @param serviceName
	 * 
	 * @return
	 * @throws IOException
	 */
	private static DefaultPieDataset pieDataset(String serviceName) throws IOException {
		LoadFile ld = new LoadFile();
		DefaultPieDataset pieDataSet = ld.generatePieDataSet(serviceName);
		return pieDataSet;
	}

	/**
	 * Create bar chart
	 * 
	 * @param title
	 * @param is3D
	 * @param serviceName
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@SuppressWarnings("deprecation")
	public static JFreeChart createBarChart(String title, boolean is3D, String serviceName)
			throws NumberFormatException, IOException {
		JFreeChart chart = null;
		BarRenderer renderer = null;

		if (is3D) {
			chart = ChartFactory.createBarChart3D(title, // 图表标题
					"MethodName", // 目录轴的显示标签
					"Duration", // 数值轴的显示标签
					barChartDataset(serviceName), // 数据集
					PlotOrientation.HORIZONTAL, // 图表方向：水平、垂直
					true, // 是否显示图例(对于简单的柱状图必须是 false)
					false, // 是否生成工具
					false // 是否生成 URL 链接
					);

			// 3D柱图的呈现器
			renderer = new BarRenderer3D();
			renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setItemLabelFont(new Font("Arial", Font.PLAIN, 12));
			renderer.setItemLabelsVisible(true);

			// 3D柱子上不能正常显示数字
			// 注意：如果数值太大切前面的柱子低于后面的柱子，那么前面的那个数值将被挡住，所以将下面方法中的0该为-值
			ItemLabelPosition itemLabelPositionFallback = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
					TextAnchor.BASELINE_LEFT, TextAnchor.HALF_ASCENT_LEFT, -1.3D);
			// 设置不能正常显示的柱子label的position
			renderer.setPositiveItemLabelPositionFallback(itemLabelPositionFallback);
			renderer.setNegativeItemLabelPositionFallback(itemLabelPositionFallback);

		} else {
			chart = ChartFactory.createBarChart(title, // 图表标题
					"MethodName", // 目录轴的显示标签
					"Duration", // 数值轴的显示标签
					barChartDataset(serviceName), // 数据集
					PlotOrientation.HORIZONTAL, // 图表方向：水平、垂直
					true, // 是否显示图例(对于简单的柱状图必须是 false)
					false, // 是否生成工具
					false // 是否生成 URL 链接
					);
			// 柱图的呈现器
			renderer = new BarRenderer();
			renderer.setIncludeBaseInRange(true); // 显示每个柱的数值，并修改该数值的字体属性
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

		}

		// 设置图片背景
		// chart.setBackgroundPaint(Color.PINK);
		// 为了防止中文乱码：必须设置字体
		chart.setTitle(new TextTitle(title, new Font("Arial", Font.PLAIN, 22)));
		LegendTitle legend = chart.getLegend(); // 获取图例
		legend.setItemFont(new Font("Arial", Font.BOLD, 12)); // 设置图例的字体，防止中文乱码
		CategoryPlot plot = (CategoryPlot) chart.getPlot(); // 获取柱图的Plot对象(实际图表)
		// 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
		plot.setBackgroundPaint(new Color(255, 255, 204));
		plot.setForegroundAlpha(0.65F); // 设置前景色透明度
		// 设置横虚线可见
		plot.setRangeGridlinesVisible(true);
		// 虚线色彩
		plot.setRangeGridlinePaint(Color.gray);

		ValueAxis rangeAxis = plot.getRangeAxis();
		// 设置最高的一个Item与图片顶端的距离
		rangeAxis.setUpperMargin(0.2);
		// 设置最低的一个Item与图片底端的距离
		rangeAxis.setLowerMargin(1);

		CategoryAxis domainAxis = plot.getDomainAxis(); // 获取x轴
		domainAxis.setMaximumCategoryLabelWidthRatio(1.0f); // 横轴上的 Lable 是否完整显示
		domainAxis.setLabelFont(new Font("Arial", Font.TRUETYPE_FONT, 14)); // 设置字体，防止中文乱码
		domainAxis.setTickLabelFont(new Font("Arial ", Font.BOLD, 12)); // 轴数值
		// h.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 45度倾斜
		plot.getRangeAxis().setLabelFont(new Font("Arial", Font.TRUETYPE_FONT, 14)); // Y轴设置字体，防止中文乱码

		renderer.setBaseOutlinePaint(Color.BLACK); // 设置柱子边框颜色
		renderer.setDrawBarOutline(true); // 设置柱子边框可见
		renderer.setSeriesPaint(0, Color.YELLOW); // 设置每个柱的颜色
		renderer.setSeriesPaint(1, Color.green);
		renderer.setSeriesPaint(2, Color.RED);
		renderer.setSeriesPaint(3, Color.CYAN);
		renderer.setSeriesPaint(5, Color.ORANGE);
		renderer.setSeriesPaint(4, Color.MAGENTA);
		renderer.setSeriesPaint(6, Color.DARK_GRAY);
		renderer.setSeriesPaint(7, Color.PINK);
		renderer.setSeriesPaint(8, Color.black);
		renderer.setItemMargin(0.1); // 设置每个地区所包含的平行柱的之间距离
		plot.setRenderer(renderer); // 给柱图添加呈现器
		plot.setForegroundAlpha(0.7f); // 设置柱的透明度
		// renderer.setMaximumBarWidth(0.2); // 设置柱子宽度
		// renderer.setMinimumBarLength(0.6); // 设置柱子高度
		// 设置横坐标显示位置(默认是下方)；
		// plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		// 设置纵坐标显示位置(默认是左方)
		// plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		// 没有数据的时候显示的内容
		plot.setNoDataMessage(" 找不到可用数据 ");
		return chart;

	}

	/**
	 * 获取一个演示用的组合数据集对象
	 * 
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	private static CategoryDataset barChartDataset(String serviceName) throws NumberFormatException, IOException {
		LoadFile ld = new LoadFile();
		DefaultCategoryDataset pieDataSet = ld.generateDefaultCategoryDataset(serviceName);
		return pieDataSet;
	}

	/**
	 * Create time series chart
	 * 
	 * @param title
	 * @param subtitleStr
	 * @param range
	 * @param isAreaText
	 * @param areaText
	 * @param lowpress
	 * @param uperpress
	 * @param serviceName
	 * @return
	 */
	public static JFreeChart createSeriesChart(String title, String subtitleStr, String range, boolean isAreaText,
			String areaText, double lowpress, double uperpress, String serviceName) {

		// 时间曲线元素
		// JFreeChart chart =
		// ChartFactory.createTimeSeriesChart("标题","x轴标志","y轴标志","设置数据",是否显示图形,是否进行提示,是否配置报表存放地址);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, null, range, createTimeTimeSeriesDataset(), true,
				true, false);

		if (subtitleStr != null) {
			TextTitle subtitle = new TextTitle(subtitleStr, new Font("黑体", Font.BOLD, 12));
			chart.addSubtitle(subtitle);
		}

		// 设置日期显示格式
		XYPlot plot = chart.getXYPlot();
		// XY軸對象
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
		// 设置标题的颜色
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC, 22)));
		LegendTitle legend = chart.getLegend(); // 获取图例
		legend.setItemFont(new Font(" 宋体 ", Font.BOLD, 12)); // 设置图例的字体，防止中文乱码
		// 在這裡設置的是漸變色
		chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.blue));

		// 设置Y轴字体
		plot.getRangeAxis().setLabelFont(new Font(" 宋体 ", Font.TRUETYPE_FONT, 14)); // Y轴设置字体，防止中文乱码
		plot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细
		ValueAxis vaxis = plot.getDomainAxis();
		vaxis.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
		vaxis.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
		vaxis.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
		vaxis.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色
		vaxis.setLowerMargin(0.06d);// 分类轴下（左）边距
		vaxis.setUpperMargin(0.14d);// 分类轴下（右）边距,防止最后边的一个数据靠近了坐标轴。
		plot.setNoDataMessage("找不到可用数据");// 没有数据时显示的文字说明。

		// 渲染
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) plot.getRenderer();
		// 第一条折线的颜色
		xylineandshaperenderer.setBaseItemLabelsVisible(true);
		xylineandshaperenderer.setSeriesFillPaint(0, new Color(127, 128, 0));
		xylineandshaperenderer.setSeriesPaint(0, new Color(127, 128, 0));
		xylineandshaperenderer.setSeriesShapesVisible(0, true);
		xylineandshaperenderer.setSeriesShapesVisible(1, true);
		xylineandshaperenderer.setSeriesShapesVisible(2, true);
		xylineandshaperenderer.setSeriesShapesVisible(3, true);
		xylineandshaperenderer.setSeriesShapesVisible(4, true);
		// 折线的粗细调
		StandardXYToolTipGenerator xytool = new StandardXYToolTipGenerator();
		xylineandshaperenderer.setToolTipGenerator(xytool);
		xylineandshaperenderer.setStroke(new BasicStroke(1.5f));
		// 显示节点的值
		xylineandshaperenderer.setBaseItemLabelsVisible(true);
		xylineandshaperenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
				TextAnchor.BASELINE_CENTER));
		xylineandshaperenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		xylineandshaperenderer.setBaseItemLabelPaint(new Color(102, 102, 102));// 显示折点数值字体的颜色

		ValueAxis rangeAxis = plot.getRangeAxis();
		// 设置最高的一个Item与图片顶端的距离
		rangeAxis.setUpperMargin(0.2);
		// 设置最低的一个Item与图片底端的距离
		rangeAxis.setLowerMargin(0.3);
		// // 在图表中加区域加区域
		// if (isAreaText) {
		// lowpress = 62;
		// uperpress = 400;
		// IntervalMarker intermarker = new IntervalMarker(lowpress, uperpress);
		// intermarker.setPaint(Color.decode("#66FFCC"));// 域顏色
		// intermarker.setLabelFont(new Font("SansSerif", 41, 14));
		// intermarker.setLabelPaint(Color.RED);
		// intermarker.setLabel(areaText);
		// if (createTimeTimeSeriesDataset() != null) {
		// plot.addRangeMarker(intermarker, Layer.BACKGROUND);
		// }
		// }
		return chart;
	}

	/**
	 * The data set of time Series needed
	 * 
	 * @return
	 */
	private static TimeSeriesCollection createTimeTimeSeriesDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		// 第一条时序线
		TimeSeries pop1 = new TimeSeries("水果零售销售趋势", Day.class);
		pop1.add(new Day(10, 1, 2004), 100);
		pop1.add(new Day(10, 2, 2004), 150);
		pop1.add(new Day(10, 3, 2004), 250);
		pop1.add(new Day(10, 4, 2004), 275);
		pop1.add(new Day(10, 5, 2004), 325);
		pop1.add(new Day(10, 6, 2004), 425);
		// 创建第二条时序线
		TimeSeries pop2 = new TimeSeries("蔬菜零售销售趋势", Day.class);
		pop2.add(new Day(20, 1, 2004), 200);
		pop2.add(new Day(20, 2, 2004), 250);
		pop2.add(new Day(20, 3, 2004), 450);
		pop2.add(new Day(20, 4, 2004), 475);
		pop2.add(new Day(20, 5, 2004), 125);
		pop2.add(new Day(20, 6, 2004), 150);

		// 创建第三天条时序线
		TimeSeries pop3 = new TimeSeries("主食零售销售趋势", Day.class);
		pop3.add(new Day(20, 1, 2004), 200);
		pop3.add(new Day(20, 2, 2004), 250);
		pop3.add(new Day(20, 3, 2004), 450);
		pop3.add(new Day(20, 4, 2004), 200);
		pop3.add(new Day(20, 5, 2004), 125);
		pop3.add(new Day(20, 6, 2004), 150);

		dataset.addSeries(pop1);
		dataset.addSeries(pop2);
		dataset.addSeries(pop3);
		return dataset;

	}

	@SuppressWarnings("deprecation")
	public static JFreeChart createLineChart(String titleStr, boolean is3D, String serviceName) {
		JFreeChart chart = null;
		// JFreeChart对象 参数：标题，目录轴显示标签，数值轴显示标签，数据集，是否显示图例，是否生成工具，是否生成URL连接

		if (!is3D) {
			// 平面
			chart = ChartFactory.createLineChart(titleStr, "时间/月份", "销售数量", getLineChartDataSet(),
					PlotOrientation.VERTICAL, true, true, false);
		} else {
			// 3D
			chart = ChartFactory.createLineChart3D(titleStr, "时间/月份", "销售数量", getLineChartDataSet(),
					PlotOrientation.VERTICAL, true, true, false);
		}

		// 设置整张图表的背景也就是图片的背景
		chart.setBackgroundPaint(Color.yellow.white);
		chart.setBorderVisible(true);// 边框可见

		TextTitle title = new TextTitle(titleStr, new Font("宋体", Font.BOLD, 20));
		// 解决曲线图片标题中文乱码问题
		chart.setTitle(title);
		// 解决图表底部中文乱码问题
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		// 设置折线图的背景还有刻度线
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setBackgroundPaint(Color.pink);
		categoryplot.setRangeGridlinesVisible(true);
		categoryplot.setRangeGridlinePaint(Color.green);

		// 设置Y轴
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		setNumberAxis(numberaxis);

		// 设置x轴
		CategoryAxis domainAxis = (CategoryAxis) categoryplot.getDomainAxis();
		setDomainAxis(domainAxis);

		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();

		// 3D柱子上不能正常显示数字
		// 注意：如果数值太大切前面的柱子低于后面的柱子，那么前面的那个数值将被挡住，所以将下面方法中的0该为-值
		ItemLabelPosition itemLabelPositionFallback = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1,
				TextAnchor.BASELINE_CENTER, TextAnchor.BASELINE_CENTER, -1.0D);
		// 设置不能正常显示的柱子label的position
		lineandshaperenderer.setPositiveItemLabelPosition(itemLabelPositionFallback);
		lineandshaperenderer.setNegativeItemLabelPosition(itemLabelPositionFallback);
		// 数据点
		// series 点（即数据点）可见
		lineandshaperenderer.setBaseShapesVisible(true);
		// 显示数据点的数据
		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 显示折线图点上的数据
		lineandshaperenderer.setBaseItemLabelsVisible(true);
		return chart;
	}

	/**
	 * 设置X轴
	 * 
	 * @param domainAxis
	 */
	private static void setDomainAxis(CategoryAxis domainAxis) {
		// 解决x轴坐标上中文乱码
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		// 解决x轴标题中文乱码
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 14));
		// 用于显示X轴刻度
		domainAxis.setTickMarksVisible(true);
		// 设置X轴45度
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	}

	/**
	 * 设置Y轴
	 * 
	 * @param numberAxis
	 */
	private static void setNumberAxis(NumberAxis numberaxis) {
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// 是否显示零点
		numberaxis.setAutoRangeIncludesZero(true);
		numberaxis.setAutoTickUnitSelection(false);
		// 解决Y轴标题中文乱码
		numberaxis.setLabelFont(new Font("sans-serif", Font.PLAIN, 14));
		// 自动设置Y轴的数值
		numberaxis.setAutoRange(true);
		numberaxis.setAutoTickUnitSelection(true);
		// numberaxis.setTickUnit(new NumberTickUnit(10000));//Y轴数据间隔
	}

	/**
	 * 产生数据源
	 * 
	 * @return
	 */
	private static DefaultCategoryDataset getLineChartDataSet() {
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		int i = 0;
		Random rand = new Random();
		while (i < 6) {
			for (int j = 1; j <= 12; j++) {
				defaultcategorydataset.addValue((rand.nextDouble() * rand.nextInt(10000)), "产品" + i, "第" + j + "月份");
			}
			i++;
		}
		return defaultcategorydataset;
	}

}
