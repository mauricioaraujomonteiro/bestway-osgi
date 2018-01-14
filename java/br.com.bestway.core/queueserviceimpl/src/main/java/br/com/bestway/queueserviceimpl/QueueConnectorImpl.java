/**
 *
 */
package br.com.bestway.queueserviceimpl;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import br.com.bestway.queueservice.connector.QueueConnector;

/**
 * @author mmont
 *
 */
@Service(value = QueueConnectorImpl.class)
public class QueueConnectorImpl implements QueueConnector, BundleActivator
{
    private Logger LOG = Logger.getGlobal();
    private Connection connection = null;
    private Session serverSession = null;

    private InitialContext context = null;

    @Override
    public Destination connect(String pQueueName) throws NamingException
    {
        if (null == context)
        {
            context = new InitialContext();
        }
        getServerConnection(context);
        Destination queue = (Destination) context.lookup(pQueueName);

        return queue;
    }

    /**
     * @return
     *
     */
    private Connection getServerConnection(InitialContext pContext)
    {
        ConnectionFactory factory = null;
        if (null != connection)
        {
            return connection;
        }
        try
        {
            pContext = new InitialContext();
            factory = (ConnectionFactory) pContext.lookup("ConnectionFactory");
            connection = (Connection) factory.createConnection();
            ((javax.jms.Connection) connection).start();
        } catch (JMSException | NamingException e)
        {
            LOG.severe("Is was not possible to connect to the queue server. " + e);
        }
        return connection;
    }

    @Override
    public void start(BundleContext pContext) throws Exception
    {
        LOG.info("Startig Queue connector bundle");
        if (null == context)
        {
            context = new InitialContext();
        }
        Connection connection = getServerConnection(context);
        this.serverSession  = ((javax.jms.Connection) connection).createSession(false, Session.AUTO_ACKNOWLEDGE);
    }


    @Override
    public void stop(BundleContext pContext) throws Exception
    {
        LOG.info("Stopping Queue connector bundle");
    }

    @Override
    public MessageConsumer createConsumer(Destination pQueueName) throws JMSException
    {
        return  serverSession.createConsumer(pQueueName);

    }

    @Override
    public MessageProducer createProducer(Destination pQueueName) throws NamingException, JMSException
    {
        return serverSession.createProducer(pQueueName);
    }

}
