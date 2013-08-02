package service.client;

 import org.apache.thrift.async.AsyncMethodCallback;

 public class MethodCallback implements AsyncMethodCallback { 
    Object response = null; 

    public Object getResult() { 
        // 返回结果值
        return this.response; 
    } 

    // 处理服务返回的结果值
    @Override 
    public void onComplete(Object response) { 
        this.response = response; 
    } 

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.thrift.async.AsyncMethodCallback#onError(java.lang.Exception)
     */
    @Override
    public void onError(Exception exception) {
        // TODO Auto-generated method stub

    } 
 } 