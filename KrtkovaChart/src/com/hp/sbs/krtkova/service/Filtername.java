package com.hp.sbs.krtkova.service;

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
