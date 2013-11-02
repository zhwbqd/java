package httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author jack.zhang
 */
public abstract class AbstractResponseHandler<T> implements ResponseHandler<T> {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final int HTTP_UNSUCCESS_CODE = 300;

    @Override
    public T handleResponse(HttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= HTTP_UNSUCCESS_CODE) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        return handle(entity);
    }

    abstract T handle(HttpEntity entity) throws IOException;
}
