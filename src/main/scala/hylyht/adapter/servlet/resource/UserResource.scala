package hylyht.adapter.servlet.resource

import javax.ws.rs._
import javax.ws.rs.core._
import org.slf4j.LoggerFactory

@Path("/user")
class UserResource { // todo: use cake pattern here to mixin UserRegister

    private def logger = LoggerFactory.getLogger(classOf[UserResource])

    @PUT
    @Consumes(Array(MediaType.APPLICATION_JSON))
    @Produces(Array(MediaType.APPLICATION_JSON))
    def createUser(
        data: String,
        @Context ui: UriInfo): Response = {

        val responseJson = """{
            "id" : %d,
            "username" : "%s"
        }""".format(123, "usercreated")

        logger.debug("createUser: responseJson="+ responseJson)

        Response.created(ui.getRequestUri).entity(responseJson).build()
    }

}
