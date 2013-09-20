package hylyht.adapter.json

import hylyht.domain.{User, Password, Username}

trait JsonUserAdapterComponent extends UserAdapterComponent {

    override def userAdapter = new JsonUserAdapter

    class JsonUserAdapter extends UserAdapter {

        def unmarshall(from: String): User = {
            val tree = JSON.parseJSON(from)
            new User(
                Username(tree("username").toString),
                Password(tree("password").toString))
        }

        def marshall(user: User): String =
            JSON.makeJSON(Map(
                "username" -> user.username.value,
                "password" -> user.password.value ))

    }

}
