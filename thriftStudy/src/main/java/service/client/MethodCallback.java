package service.client;

import org.apache.thrift.async.AsyncMethodCallback;

import service.demo.Hello.AsyncClient.helloString_call;

public class MethodCallback implements AsyncMethodCallback<helloString_call> {

    private helloString_call response;

    public helloString_call getResult() {
        return this.response;
    }

    @Override
    public void onError(Exception exception) {
        throw new RuntimeException(exception);
    }

    @Override
    public void onComplete(helloString_call response) {
        this.response = response;

    }
}