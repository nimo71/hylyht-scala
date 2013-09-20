package hylyht.adapter.ws.resource

import javax.ws.rs.core.{Response, UriInfo}
import org.scalatest.FlatSpec
import hylyht.adapter.json.UserAdapterComponent
import hylyht.domain.{Password, Username, User, UserRepositoryComponent}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.hamcrest.Matchers._
import org.hamcrest.MatcherAssert._

class UserResourceTest extends FlatSpec with MockitoSugar {

    private val userResource = new UserResource with UserAdapterComponent with UserRepositoryComponent  {
        override val userAdapter = mock[UserAdapter]
        override val userRepository = mock[UserRepository]
    }

    private val data = """{
                username : testuser,
                password : testpass
            }"""

    private val username = Username("testuser")
    private val password = Password("testpass")
    private val user = User(username, password)

    when(userResource.userAdapter.unmarshall(data)) thenReturn user
    when(userResource.userAdapter.marshall(user)) thenReturn data

    "UserResource" should "unmarshall json into a user" in {
        val ui = mock[UriInfo]
        val response = userResource.createUser(data, ui)

        verify(userResource.userRepository).createUser(username, password)
        assertThat(response, any(classOf[Response]))
    }
}
