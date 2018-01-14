/**
 *
 */
package br.com.bestway.queueservice.connector;

import javax.jms.*;
import javax.naming.NamingException;

/**
 * This interface should be implemented for queue connector
 * @author mmont
 *
 */
public interface QueueConnector
{
    /**
     * Connect to a give queue
     * @param pQueueName the queue name
     * @return a queue connection
     * @throws NamingException
     * @throws JMSException
     */
    public Destination connect(String pQueueName) throws NamingException;

    /**
     * @param pQueueName
     * @return
     * @throws NamingException
     * @throws JMSException
     */
    public MessageConsumer createConsumer(Destination pQueueName) throws NamingException, JMSException;

    /**
     * @param pQueueName
     * @return
     * @throws NamingException
     * @throws JMSException
     */
    public MessageProducer createProducer(Destination pQueueName) throws NamingException, JMSException;
}
