package cl.ucn.disc.as;

import lombok.extern.slf4j.Slf4j;
import io.javalin.Javalin;


import cl.ucn.disc.as.ui.ApiRestServer;
import cl.ucn.disc.as.ui.WebController;
import cl.ucn.disc.as.grpc.PersonaGrpcServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
/**
 *The Main
 *@author Jhoan Mamani Carrillo
 */

/**
 *
 @@ -24,15 +19,24 @@ public final class Main {
  * Entrypoint
  * @param args to use.
  */
@Slf4j
public class Main {
    /**
    *The Main
     *
     * @param args to use.
     */
    public static void main(String[] args) throws IOException, InterruptedException  {

        log.debug("Starting Main ..");

        log.debug("Starting the REST API server ..");
        Javalin app = ApiRestServer.start(7070, new WebController());

        log.debug("Starting the gRPC server ..");
        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        server.awaitTermination();
        log.debug("Done.");
    }
}
