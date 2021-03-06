package pl.urman.sandbox.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.urman.sandbox.auth.annotation.RolesAllowed;
import pl.urman.sandbox.db.model.Role;
import pl.urman.sandbox.model.user.User;
import pl.urman.sandbox.model.user.UserDeleter;
import pl.urman.sandbox.model.user.UserFinder;
import pl.urman.sandbox.model.user.UserPersister;

@Path("/user")
@RolesAllowed(roles = {Role.USER, Role.ADMIN})
public class UserEndpoint {

    @Inject
    private UserFinder userFinder;

    @Inject
    private UserPersister userPersister;

    @Inject
    private UserDeleter userDeleter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = {Role.USER, Role.ADMIN})
    public List<User> fetchAll() {
        return userFinder.fetchAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        userPersister.addUser(user);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteAll() {
        userDeleter.deleteAll();
        return Response.ok().build();
    }
}
