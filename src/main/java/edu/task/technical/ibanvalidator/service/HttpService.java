package edu.task.technical.ibanvalidator.service;

import edu.task.technical.ibanvalidator.servlet.IBANValidationServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class HttpService {

    private Server httpJettyServer;

    void start(int WebPort) throws Exception {

        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;

        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        httpJettyServer = new Server(threadPool);
        ServerConnector connector = new ServerConnector(httpJettyServer);
        connector.setPort(WebPort);
        httpJettyServer.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        httpJettyServer.setHandler(servletHandler);

        servletHandler.addServletWithMapping(IBANValidationServlet.class, "/api/ibanvalidator");

        httpJettyServer.start();
    }

    void stop() throws Exception {
        httpJettyServer.stop();
    }

}
