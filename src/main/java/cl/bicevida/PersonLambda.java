package cl.bicevida;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
public class PersonLambda {
    @Inject
    PersonService personService;

    @ConfigProperty(name = "greeting.prefix")
    String prefixGreeting;

    @POST
    @Path("/serverless")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleRequest(Person input) {
        Wrapper response = new Wrapper();
        try {
            response.setData(prefixGreeting + " " + personService.getName(input));
            response.setDescripcion("RESULTADO OK");
            response.setHttpCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(response).build();

        } catch (Exception ex) {
            response.setDescripcion(ex.getMessage());
            response.setHttpCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }
}
