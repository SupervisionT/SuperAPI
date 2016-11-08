import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Abdallah.AlShaikhAli on 07/11/2016.
 */


@Path("/Operations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Operations {
    private static final Logger LOG = LoggerFactory.getLogger(Operations.class);

    @POST
    @Path("/add")
    public Response add(@NotNull @Valid Input input) {

        LOG.info("Adding numbers");
        if (input.hasNoNumbers()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return input.getNumbers().stream()

                .reduce((x, y) -> x + y)
                .map(sum -> Response.ok(sum).build())
                .get();

    }

}
