package project.spaceshop.mqactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQProducer {

    private JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMQProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String message){
        jmsTemplate.convertAndSend("advertising", message);
    }
}
