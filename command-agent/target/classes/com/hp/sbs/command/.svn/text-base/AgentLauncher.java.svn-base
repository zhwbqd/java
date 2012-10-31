package com.hp.sbs.command;

import org.eclipse.jetty.server.Server;

public class AgentLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Server server = new Server(9090);

		try {

			server.setHandler(new RequestHandler());

			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
