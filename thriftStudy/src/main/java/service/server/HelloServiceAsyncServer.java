package service.server; 
 import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

import service.demo.Hello;
import service.demo.HelloServiceImpl;

 public class HelloServiceAsyncServer { 
    /** 
     * 启动 Thrift 异步服务器
     * @param args 
     */ 
    public static void main(String[] args) { 
        TNonblockingServerTransport serverTransport; 
        try { 
            serverTransport = new TNonblockingServerSocket(10005); 
            Hello.Processor processor = new Hello.Processor( 
                    new HelloServiceImpl()); 

            Args arg = new TNonblockingServer.Args(serverTransport);
            arg.processor(processor);
            TServer server = new TNonblockingServer(arg);
            System.out.println("Start server on port 10005 ..."); 
            server.serve(); 
        } catch (TTransportException e) { 
            e.printStackTrace(); 
        } 
    } 
 } 