package hylyht.domain

case class User(val username: Username, val password: Password) {

    override def toString(): String =
        "[User: username=%s, password=%s]".format(username.value, password.value)
}
