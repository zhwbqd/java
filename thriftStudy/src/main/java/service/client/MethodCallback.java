package service.client;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import service.demo.Hello.AsyncClient.helloString_call;

public class MethodCallback implements AsyncMethodCallback<helloString_call> {

    private helloString_call res;
    
    @Override
    public void onError(Exception exception) {
        throw new RuntimeException(exception);
    }

    @Override
    public void onComplete(helloString_call response) {
        res=response;
    }

    public synchronized boolean isFinished() {
        if(res==null)return false;
        return true;
    }

    public synchronized String getResult() throws TException {
        if(res==null){
            return null;
        }
        return res.getResult();
    }
    
}