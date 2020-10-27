package com.compucompare.compucompare.configuration;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This configuration tells the server to
 * listen to and redirect plain HTTP traffic
 * to HTTPS.
 */
@Profile("production")
@Configuration
public class HTTPConfiguration
{
    @Value("${server.http.port}")
    private int httpPort;

    @Value("${server.port}")
    private int httpsPort;

    @Bean
    /**
     * Create a new instance of Tomcat that uses
     * an HTTP redirect connector.
     * 
     * @return A new Tomcat servlet that responds to
     *         and redirects HTTP requests.
     */
    public ServletWebServerFactory servletContainer()
    {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(httpRedirectConnector());
        return tomcat;
    }

    /**
     * Programatically define an HTTP connector
     * for Tomcat that redirects traffic to HTTPS.
     * 
     * @return A new configured Tomcat connector.
     */
    private Connector httpRedirectConnector()
    {
        Connector httpConnector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        httpConnector.setPort(httpPort);
        httpConnector.setSecure(false);
        httpConnector.setRedirectPort(httpsPort);
        return httpConnector;
    }
}