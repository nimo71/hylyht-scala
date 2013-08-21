package hylyht.adapter.servlet.resource

import org.scalatest.FlatSpec
import hylyht.domain._

class UserResourceTest extends FlatSpec{

    val userRegister: UserRegister = new UserRegister() {
        override def createUser(username: Username, password: Password) {

        }
    }

    "UserResource" should "create a user with a unique username and password" in {
        fail()
    }
}
