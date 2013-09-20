package hylyht.domain

case class User(val username: Username, val password: Password) {

    override def toString(): String =
        "[User: username=%s, password=%s]".format(username.value, password.value)

    override def hashCode(): Int =
        41 * (41 + username.hashCode()) + password.hashCode()

    override def equals(other: Any): Boolean = {
        other match {
            case o: User => o.username == this.username && o.password == this.password
            case _ => false
        }
    }
}
