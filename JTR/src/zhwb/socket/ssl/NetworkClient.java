package zhwb.socket.ssl;

// Source File Name:   NetworkClient.java

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkClient
{
    protected Socket serverSocket;
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

    protected Socket doConnect(String s, int i)
        throws IOException, UnknownHostException
    {
        return new Socket(s, i);
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

    public NetworkClient(String s, int i)
        throws IOException
    {
        serverSocket = null;
        openServer(s, i);
    }

    public NetworkClient()
    {
        serverSocket = null;
    }
    
    public static void main(String args[]) throws IOException {
    	NetworkClient client = new NetworkClient("localhost", 6667);
    	client.readFromServer();
    }
}