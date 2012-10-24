package com.hp.sbs.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandExecutor implements IExecutor {

	private String command = "";

	public CommandExecutor(String command) {
		this.command = command;
	}

	public String exec() {

		StringBuffer result = new StringBuffer();

		try {
			Process process = Runtime.getRuntime().exec(command);
			InputStream is = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"GBK"));

			String temp = new String();
			while ((temp = br.readLine()) != null) {
				System.out.println(temp);
				result.append(temp + "\n");
			}

			br.close();
			is.close();
			process.waitFor();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();

	}

}
