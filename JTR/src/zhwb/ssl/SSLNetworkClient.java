// Source File Name:   SSLNetworkClient.java
import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SSLNetworkClient
{
    protected SSLSocket serverSocket;
    public PrintStream serverOutput;
    public InputStream serverInput;

    public void openServer(String s, int i)
        throws IOException, UnknownHostException
    {
        if(serverSocket != null)
            closeServer();
        serverSocket = doConnect(s, i);
        serverOutput = new PrintStream(new BufferedOutputStream(serverSocket.getOutputStream()), true);
        serverInput = new BufferedInputStream(serverSocket.getInputStream());
    }
    
    public void readFromServer() throws IOException {
    	if(!serverIsOpen()) return;
    	BufferedReader in = new BufferedReader(new InputStreamReader(serverInput));
        String line;
        while((line = in.readLine()) != null) {
        	System.out.println(line);
        }
        closeServer();
    }

    protected SSLSocket doConnect(String host, int port)
        throws IOException, UnknownHostException
    {
        
	    SSLSocketFactory sslFact =
      		(SSLSocketFactory)SSLSocketFactory.getDefault();
    	SSLSocket s =
      		(SSLSocket)sslFact.createSocket(host, port);

        return s;
    }

    public void closeServer()
        throws IOException
    {
        if(!serverIsOpen())
        {
            return;
        } else
        {
            serverSocket.close();
            serverSocket = null;
            serverInput = null;
            serverOutput = null;
            return;
        }
    }

    public boolean serverIsOpen()
    {
        return serverSocket != null;
    }

    public SSLNetworkClient(String s, int i)
        throws IOException
    {
        serverSocket = null;
        openServer(s, i);
    }

    public SSLNetworkClient()
    {
        serverSocket = null;
    }
    
    public static void main(String args[]) throws IOException {
    	SSLNetworkClient client = new SSLNetworkClient("localhost", 6667);
    	client.readFromServer();
    }
}