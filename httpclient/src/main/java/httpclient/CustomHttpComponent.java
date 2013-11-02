package httpclient;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class CustomHttpComponent {

    private static final int MAX_TOTAL = 200;

    private static HttpClient httpClient;

    public CustomHttpComponent() {
        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(MAX_TOTAL);
        connectionManager.setMaxTotal(MAX_TOTAL);
        HttpParams httpParams = new BasicHttpParams();
        httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 200);
        httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 200);
        httpParams.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false);
        httpClient = new DefaultHttpClient(connectionManager);
        new IdleConnectionMonitorThread(connectionManager).start();
    }

    public <T> T execute(HttpHost httpHost, HttpUriRequest httpUriRequest, AbstractResponseHandler<T> handler) throws IOException {
        return httpClient.execute(httpHost, httpUriRequest, handler, new BasicHttpContext());
    }

    public class IdleConnectionMonitorThread extends Thread {
        private final ClientConnectionManager connMgr;
        private volatile boolean shutdown;
        public IdleConnectionMonitorThread(ClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }
        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections
                        // that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                //LOG
            }
        }
        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }
}


