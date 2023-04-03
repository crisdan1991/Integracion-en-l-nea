package org.apache.camel.learn;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientProcesor implements Processor {
    private Logger log = LoggerFactory.getLogger(ClientProcesor.class);

    @Override

    public void process(Exchange exchange) throws Exception {
        Client p = (Client) exchange.getIn().getBody();
        log.info("Recibio {}", p);
        log.info("Canal de registro " + p.getChannel());
        if (p.getChannel() == 1) {
            exchange.getIn().setHeader("channel", "1");
        } else {
            exchange.getIn().setHeader("channel", "2");
        }
        exchange.getIn().setBody(p);
    }
}
