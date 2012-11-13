package zhwb.socket.ssl;

/**
 * SSLNetworkServer.java
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.util.Date;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLNetworkServer  implements Runnable, Cloneable
{
    public SSLSocket client;
    public PrintStream out;
    public InputStream in;
    private Thread serverInstance;
    private SSLServerSocket serverSocket;
    private int port;

    public SSLNetworkServer()
    {
        client = null;
    }

    public boolean clientIsOpen()
    {
        return client != null;
    }

    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException _ex)
        {
            throw new InternalError();
        }
    }

    public void close()
        throws IOException
    {
        client.close();
        client = null;
        in = null;
        out = null;
    }

    public static void main(String args[])
    {
        System.setProperty("javax.net.ssl.trustStore","MyCacertsFile");
        try
        {
            (new SSLNetworkServer()).startServer(6667);
            System.out.println("Server started at port 6667");
        }
        catch(IOException ioexception)
        {
            System.out.print("Server failed: " + ioexception + "\n");
        }
    }

    public final void run()
    {
        if(serverSocket != null)
        {
            // Thread.currentThread().setPriority(10);
            try
            {
                do
                {
                    SSLSocket socket = (SSLSocket)serverSocket.accept();
                    SSLNetworkServer networkserver = (SSLNetworkServer)clone();
                    networkserver.serverSocket = null;
                    networkserver.client = socket;
                    (new Thread(networkserver)).start();
                } while(true);
            }
            catch(Exception exception)
            {
                System.out.print("Server failure\n");
                exception.printStackTrace();
            }
            try
            {
                serverSocket.close();
            }
            catch(IOException _ex) { }
            System.out.print("cs=" + serverSocket + "\n");
        } else
        {
            try
            {
                out = new PrintStream(new BufferedOutputStream(client.getOutputStream()), false);
                in = new BufferedInputStream(client.getInputStream());
                serviceRequest();
            }
            catch(Exception _ex) { }
            try
            {
                close();
            }
            catch(IOException _ex) { }
        }
    }

    public void serviceRequest() throws IOException
    {
        out.println("HTTP/1.0 200 OK\n");
		out.println("Hello, this is a SSL Time server " + getClass().getName() + "Port: " + this.port);
        out.println("Current time:" + new Date().toLocaleString());
        out.flush();
    }

    public final void startServer(int port) throws IOException
    {
        this.port = port;
		SSLServerSocketFactory ssf = null;
		// Set up key manager to do server authentication
		char[] passphrase = "secret".toCharArray();
		try {
			// Get a context for the protocol. We can use SSL of TLS as needed.
			SSLContext ctx = SSLContext.getInstance("TLS");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");

			// Open the keystore with the password
			// and initialize the SSL context with this keystore.
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(".\\keystore"), passphrase);
			kmf.init(ks, passphrase);
			ctx.init(kmf.getKeyManagers(), null, null);
			ssf = ctx.getServerSocketFactory();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		serverSocket = (SSLServerSocket)ssf.createServerSocket(port);

        serverInstance = new Thread(this);
        serverInstance.start();
    }

}