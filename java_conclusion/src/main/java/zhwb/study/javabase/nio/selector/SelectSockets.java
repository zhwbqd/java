package zhwb.study.javabase.nio.selector;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Simple echo-back server which listens for incoming stream connections and
 * echoes back whatever it reads. A single Selector object is used to listen to
 * the server socket (to accept new connections) and all the active socket
 * channels. * * @author Ron Hitchens (ron@ronsoft.com)
 */
public class SelectSockets {
	public static int PORT_NUMBER = 1234;

	public static void main(String[] argv) throws Exception {
		new SelectSockets().go(argv);
	}

	public void go(String[] argv) throws Exception {
		int port = PORT_NUMBER;
		if (argv.length > 0) { // Override default listen port
			port = Integer.parseInt(argv[0]);
		}
		System.out.println("Listening on port " + port);
		// Allocate an unbound server socket channel
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// Get the associated ServerSocket to bind it with
		ServerSocket serverSocket = serverChannel.socket();
		// Create a new Selector for use below
		Selector selector = Selector.open();
		// Set the port the server channel will listen to
		serverSocket.bind(new InetSocketAddress(port));
		// Set nonblocking mode for the listening socket
		serverChannel.configureBlocking(false);
		// Register the ServerSocketChannel with the Selector
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {

			// This may block for a long time. Upon returning, the
			// selected set contains keys of the ready channels.
			int n = selector.select();
			if (n == 0) {
				continue;
				// nothing to do
			}// Get an iterator over the set of selected
				// keys
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			// Look at each key in the selected set
			while (it.hasNext()) {
				SelectionKey key = it.next();
				// Is a new connection coming in?
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key
							.channel();
					SocketChannel channel = server.accept();
					registerChannel(selector, channel, SelectionKey.OP_READ);
					sayHello(channel);
				}// Is there data to read on this channel?
				if (key.isReadable()) {
					readDataFromSocket(key);
				}// Remove key from selected set; it's been handled it.remove();
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Register the given channel with the given selector for the given
	 * operations of interest
	 */
	protected void registerChannel(Selector selector,
			SelectableChannel channel, int ops) throws Exception {
		if (channel == null) {
			return;
			// could happen
		}// Set the new channel nonblocking
		channel.configureBlocking(false);
		// Register it with the selector
		channel.register(selector, ops);
	}// ----------------------------------------------------------
		// Use the same byte buffer for all channels. A single thread is
		// servicing all the channels, so no danger of concurrent acccess.

	private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

	/**
	 * Sample data handler method for a channel with data ready to read. * @param
	 * key A SelectionKey object associated with a channel determined by the
	 * selector to be ready for reading. If the channel returns
	 * 
	 * an EOF condition, it is closed here, which automatically invalidates the
	 * associated key. The selector will then de-register the channel on the
	 * next select call.
	 */
	protected void readDataFromSocket(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;
		buffer.clear();
		// Empty buffer // Loop while data is available; channel is nonblocking
		while ((count = socketChannel.read(buffer)) > 0) {
			buffer.flip();
			// Make buffer readable // Send the data; don't assume it goes all
			// at once
			while (buffer.hasRemaining()) {
				socketChannel.write(buffer);
			}// WARNING: the above loop is evil. Because
				// it's writing back to the same nonblocking
				// channel it read the data from, this code can
				// potentially spin in a busy loop. In real life
				// you'd do something more useful than this.
			buffer.clear(); // Empty buffer
		}
		if (count < 0) { // Close channel on EOF, invalidates the key
			socketChannel.close();
		}
	}

	// ----------------------------------------------------------
	/**
	 * * Spew a greeting to the incoming client connection. * @param channel *
	 * The newly connected SocketChannel to say hello to.
	 */
	private void sayHello(SocketChannel channel) throws Exception {
		buffer.clear();
		buffer.put("Hi there!\r\n".getBytes());
		buffer.flip();
		channel.write(buffer);
	}
}