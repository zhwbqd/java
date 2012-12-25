package com.hp.sbs.log.chart;

import java.io.File;
import java.io.FilenameFilter;

public class Filtername implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {
		if (name.endsWith(".crc")) {
			return false;
		} else if (name.endsWith(".csv")) {
			return true;
		} else
			return false;
	}

}
