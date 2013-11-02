package httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author jack.zhang
 */
public class BasicResponseHandler extends AbstractResponseHandler<String> {
    @Override
    String handle(HttpEntity entity) throws IOException {
        return entity == null ? null : EntityUtils.toString(entity);
    }
}
