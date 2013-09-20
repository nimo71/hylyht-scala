package hylyht.domain

case class Password(val value: String) {

    override def hashCode(): Int = value.hashCode()

    override def equals(other: Any): Boolean = {
        other match {
            case o: Password => o.value == this.value
            case _ => false
        }
    }
}
