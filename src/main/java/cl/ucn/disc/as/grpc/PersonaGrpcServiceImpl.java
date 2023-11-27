package cl.ucn.disc.as.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * The Persona gRPC Service class.
 * @author Jhoan Mamani Carrillo
 */
@Slf4j
public final class PersonaGrpcServiceImpl extends PersonaGrpcServiceGrpc.PersonaGrpcServiceImplBase {
    /**
     * Retrieves a Persona from the system.
     * @param request
     * @param responseObserver
     */
    @Override
    public void retrieve(PersonaGrpcRequest request, StreamObserver<PersonaGrpcResponse> responseObserver) {
        log.debug("Retrieving Persona with RUT: {}", request.getRut());
        PersonaGrpc personaGrpc = PersonaGrpc.newBuilder()
                .setRut(request.getRut())
                .setNombre("Jhoan")
                .setApellidos("Mamani Carrillo")
                .setEmail("jhoan@ucn.cl")
                .setTelefono("+95453234")
                .build();

        responseObserver.onNext(PersonaGrpcResponse.newBuilder()
                .setPersona(personaGrpc)
                .build());
        responseObserver.onCompleted();
    }
}