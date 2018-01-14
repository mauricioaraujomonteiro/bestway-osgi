/**
 *
 */
package br.com.bestway.restapiimpl;

import java.util.logging.Logger;

import javax.jms.*;
import javax.naming.NamingException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import br.com.bestway.queueservice.connector.QueueConnector;
import br.com.bestway.queueserviceimpl.queue.QueueConstraintName;
import br.com.bestway.restapiservice.RoutetService;

/**
 * @author mmont
 *
 */
public class RouteServiceImpl implements RoutetService, BundleActivator
{
    private final static Logger LOG = Logger.getGlobal();
    @Reference (bind = "bindQueueConnector", unbind = "unbindQueueConnector", cardinality = ReferenceCardinality.MANDATORY_UNARY, policy = ReferencePolicy.STATIC)
    private QueueConnector queueConnector;
    private MessageConsumer consumer;

    @Override
    public void postRoute()
    {
        // TODO Auto-generated method stub

    }

    /**
     * @throws JMSException
     * @throws NamingException
     *
     *
     */
    public void createConsumer() throws NamingException, JMSException
    {
        Destination queueDestination = queueConnector.connect(QueueConstraintName.ROUTE.name());
        MessageConsumer consumer = queueConnector.createConsumer(queueDestination);

        consumer.setMessageListener(new MessageListener()
        {

            @Override
            public void onMessage(Message message)
            {
                TextMessage textMessage = (TextMessage) message;
                try
                {
                    System.out.println(textMessage.getText());
                } catch (JMSException e)
                {
                    LOG.severe("An error occured while trying to read message from the queue: " +  e);
                }

            }
        });

    }


    @Override
    public void start(BundleContext pContext) throws Exception
    {
        Logger.getGlobal().info("Starting Rest route service implementation");
//        createConsumer();
    }

    @Override
    public void stop(BundleContext pContext) throws Exception
    {
        Logger.getGlobal().info("Stoping Rest route service implementation");
//        consumer.close();
    }

    /**
     * Set the queue connector service
     * @param pQueueConnector
     */
    public void bindQueueConnector(QueueConnector pQueueConnector)
    {
        LOG.info("Binding Queue connector.");
        this.queueConnector = pQueueConnector;
    }

    /**
     * Clear the queue connector service
     * @param pQueueConnector
     */
    public void unbindQueueConnector()
    {
        LOG.info("Unbinding Queue connector.");
        this.queueConnector = null;
    }
}
