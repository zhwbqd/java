/***********************************************************************************************
 * File Info: $Id: SwingDemo.java,v 1.1 2003/03/03 03:27:52 nathaniel_auvil Exp $
 * Copyright (C) 2002
 * Author: Nathaniel G. Auvil
 * Contributor(s):
 *
 * Copyright 2002 (C) Nathaniel G. Auvil. All Rights Reserved.
 *
 * Redistribution and use of this software and associated documentation ("Software"), with or
 * without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright statements and notices.
 * 	Redistributions must also contain a copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * 	conditions and the following disclaimer in the documentation and/or other materials
 * 	provided with the distribution.
 *
 * 3. The name "jCharts" or "Nathaniel G. Auvil" must not be used to endorse or promote
 * 	products derived from this Software without prior written permission of Nathaniel G.
 * 	Auvil.  For written permission, please contact nathaniel_auvil@users.sourceforge.net
 *
 * 4. Products derived from this Software may not be called "jCharts" nor may "jCharts" appear
 * 	in their names without prior written permission of Nathaniel G. Auvil. jCharts is a
 * 	registered trademark of Nathaniel G. Auvil.
 *
 * 5. Due credit should be given to the jCharts Project (http://jcharts.sourceforge.net/).
 *
 * THIS SOFTWARE IS PROVIDED BY Nathaniel G. Auvil AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * jCharts OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 ************************************************************************************************/

package com.hp.sbs.log.chart;

import java.awt.Color;
import java.awt.Paint;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.AxisChartDataSet;
import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.DataSeries;
import org.jCharts.chartData.interfaces.IAxisDataSeries;
import org.jCharts.properties.AxisProperties;
import org.jCharts.properties.BarChartProperties;
import org.jCharts.properties.ChartProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.PropertyException;
import org.jCharts.types.ChartType;

public class BarChart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public BarChart(double[][] data, String[] xAxisLabels, String title, String path) throws ChartDataException,
			PropertyException {
		initComponents(data, xAxisLabels, title, path);
	}

	private void initComponents(double[][] data, String[] xAxisLabels, String title, String path)
			throws ChartDataException, PropertyException {
		this.setSize(800, 500);
		this.panel = new JPanel(true);
		this.panel.setSize(500, 360);
		this.getContentPane().add(this.panel);
		this.setVisible(true);

		String xAxisTitle = "Methods";
		String yAxisTitle = "Invoke Duration";
		IAxisDataSeries dataSeries = new DataSeries(xAxisLabels, xAxisTitle, yAxisTitle, title);

		String[] legendLabels = { "Unit:ms" };
		Paint[] paints = new Paint[] { Color.yellow };
		dataSeries.addIAxisPlotDataSet(new AxisChartDataSet(data, legendLabels, paints, ChartType.BAR,
				new BarChartProperties()));

		AxisChart axisChart = new AxisChart(dataSeries, new ChartProperties(), new AxisProperties(),
				new LegendProperties(), 500, 360);

		BufferedImage bufferedImage = new BufferedImage(500, 360, BufferedImage.TYPE_INT_RGB);
		axisChart.setGraphics2D(bufferedImage.createGraphics());

		axisChart.render();

		// this.panel.getGraphics().drawImage(bufferedImage, 0, 0, this);
		try {
			ImageIO.write(bufferedImage, "jpg", new File(path + File.separator + title + "_BarChart" + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				exitForm(windowEvent);
			}
		});
	}

	private void exitForm(WindowEvent windowEvent) {
		System.exit(0);
	}

}
