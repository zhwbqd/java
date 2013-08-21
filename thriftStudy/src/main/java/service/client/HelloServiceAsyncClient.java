package service.client;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import service.demo.Hello;

public class HelloServiceAsyncClient {
	
	public static void main(String[] args) throws Exception {
	    TAsyncClientManager clientManager = new TAsyncClientManager();
        //异步必须使用Noblocking
        TNonblockingTransport transport = new TNonblockingSocket(
                "localhost", 10005);
        TProtocolFactory protocol = new TBinaryProtocol.Factory();
        Hello.AsyncClient asyncClient = new Hello.AsyncClient(protocol,
                clientManager, transport);
        
        new Thread(new AsyncClient(asyncClient)).start();
	}
	
	public static class AsyncClient implements Runnable{

        private final Hello.AsyncClient client;
	    public AsyncClient(service.demo.Hello.AsyncClient asyncClient) {
	        client=asyncClient;
        }

        @Override
        public void run() {
            try {
                System.out.println("Client calls .....");
                MethodCallback callBack = new MethodCallback();
                client.helloString("Hello World", callBack);
                while(true){
                    if(callBack.isFinished()){
                        System.out.println(callBack.getResult());
                        break;
                    }
                }
//                client.helloBoolean(true, new AsyncMethodCallback<Hello.AsyncClient.helloBoolean_call>() {
//                    @Override
//                    public void onError(Exception exception) {
//                    }
//                    @Override
//                    public void onComplete(helloBoolean_call response) {
//                        try {
//                            System.out.println(response.getResult());
//                        } catch (TException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                client.helloVoid(new AsyncMethodCallback<Hello.AsyncClient.helloVoid_call>() {
//                    @Override
//                    public void onError(Exception exception) {
//                        
//                    }
//                    @Override
//                    public void onComplete(helloVoid_call response) {
//                        try {
//                            response.getResult();
//                        } catch (TException e) {
//                            e.printStackTrace();
//                        }                        
//                    }
//                });
            } catch (TException e) {
                e.printStackTrace();
            } 
        }
	    
	}
	
}