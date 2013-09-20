package hylyht.adapter.ws.resource

import javax.ws.rs._
import javax.ws.rs.core._
import hylyht.adapter.json.{UserAdapterComponent, JsonUserAdapterComponent}
import hylyht.domain.UserRepositoryComponent
import hylyht.adapter.jdbi.JdbiUserRepositoryComponent

trait UserResource { this: UserAdapterComponent with UserRepositoryComponent =>

    def createUser(json: String, ui: UriInfo): Response =
    {
        val user = userAdapter.unmarshall(json)
    userRepository.createUser(user.username, user.password)

        val userJson = userAdapter.marshall(user)
        Response.created(ui.getRequestUri).entity(userJson).build()
    }
}

@Path("/user")
class UserResourceEndpoint {

    private val userResource = new UserResource with JsonUserAdapterComponent with JdbiUserRepositoryComponent {
        override val userAdapter = new JsonUserAdapter
        override val userRepository = new JdbiUserRepository
    }

    @PUT
    @Consumes(Array(MediaType.APPLICATION_JSON))
    @Produces(Array(MediaType.APPLICATION_JSON))
    def createUser(data: String, @Context ui: UriInfo): Response =
    {
        userResource.createUser(data, ui)
    }

}
