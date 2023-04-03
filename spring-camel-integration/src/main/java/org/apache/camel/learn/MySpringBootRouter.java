package org.apache.camel.learn;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints
 * to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {
    JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Client.class);

    @Override
    public void configure() {
        from("direct:listarclient")
                .to("rest:get:/customers?host=localhost:5000")
                .enrich("rest:get:/customers?host=localhost:8081", new JsonIntegration())
                .log("${body}")
                .to("stream:out");

        restConfiguration()
                .enableCORS(false);
        from("direct:crearCliente")
                .routeId("RouteCliente")
                .process(new ClientProcesor())
                .choice()
                .when(header("channel").contains("1"))
                .marshal(jsonDataFormat)
                .to("rest:post:/customer?host=localhost:5000")
                .otherwise()
                .marshal(jsonDataFormat)
                .to("rest:post:/customer?host=localhost:8081")
                .end();
    }

}
