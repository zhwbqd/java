package zhwb.study.socket.ssl;

/**
 * NetworkServer.java
 * A simple network server
 * This class is modified from sun.net.NetworkServer, that class
 * is provided in Java 2 and later.
 * To implement your own server service, just extends it and
 * override the method public void serviceRequest() throws IOException; .
 * 3 variables that is public and useful is:
 * Socket client; PrintStream out; InputStream in.
 * Other methods:
 * public void startServer(int port) throws IOException;
 * public void close();
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class NetworkServer  implements Runnable, Cloneable
{
    public Socket client;
    public PrintStream out;
    public InputStream in;
    private Thread serverInstance;
    private ServerSocket serverSocket;
    private int port;

    public NetworkServer()
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
        try
        {
            (new NetworkServer()).startServer(6667);
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
                    Socket socket = serverSocket.accept();
                    NetworkServer networkserver = (NetworkServer)clone();
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
        out.println("Time server " + getClass().getName() + "Port: " + this.port);
        out.println("Current time:" + new Date());
        out.flush();
    }

    public final void startServer(int port) throws IOException
    {
        this.port = port;
        serverSocket = new ServerSocket(port, 50);
        serverInstance = new Thread(this);
        serverInstance.start();
    }

}
