package zhwb.study.javabase.nio.channel;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ConnectAsync {
	public static void main(String[] argv) throws Exception {
		String host = "184.154.128.246";
		int port = 80;
		if (argv.length == 2) {
			host = argv[0];
			port = Integer.parseInt(argv[1]);
		}
		InetSocketAddress addr = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		System.out.println("initiating connection");
		sc.connect(addr);
		while (!sc.finishConnect()) {
			doSomethingUseful();
		}
		System.out.println("connection established");
		// Do something with the connected socket
		// The SocketChannel is still nonblocking
		sc.close();
	}

	private static void doSomethingUseful() {
		System.out.println("doing something useless");
	}
}