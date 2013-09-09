package hylyht.adapter.servlet.resource

import javax.ws.rs._
import javax.ws.rs.core._
import org.slf4j.LoggerFactory
import hylyht.adapter.json.JSON
import hylyht.domain.{UserResourceComponent, Password, Username}
import hylyht.adapter.jdbi.JdbiUserRepositoryComponent

@Path("/user")
class UserResourceEndpoint extends UserResourceComponent with JdbiUserRepositoryComponent {

    //private def logger = LoggerFactory.getLogger(classOf[UserResourceEndpoint])

    override val userResource: UserResource = super.userResource

    @PUT
    @Consumes(Array(MediaType.APPLICATION_JSON))
    @Produces(Array(MediaType.APPLICATION_JSON))
    def createUser(data: String, @Context ui: UriInfo): Response =
    {
        val tree = JSON.parseJSON(data)
        val username = Username(tree("username").toString)
        val password = Password(tree("password").toString)
        val user = userResource.createUser(username, password)

        val userJson = JSON.makeJSON(Map(
            "username" -> user.username.value,
            "password" -> user.password.value ))

        Response.created(ui.getRequestUri).entity(userJson).build()
    }

}
