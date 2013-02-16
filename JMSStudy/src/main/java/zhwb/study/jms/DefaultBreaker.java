
package zhwb.study.jms;

import org.apache.activemq.broker.BrokerService;

public class DefaultBreaker
{
    public static void main(final String[] args)
        throws Exception
    {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.start();
    }

}
