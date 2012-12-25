package com.hp.sbs.log.mapre;

import org.apache.hadoop.util.ProgramDriver;

public class MainClass {

	public static void main(String argv[]) {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("log", LogProcessor.class, "A map/reduce program that analysis SBS logs.");
			pgd.driver(argv);
			exitCode = 0;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.exit(exitCode);
	}
}
