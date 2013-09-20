package hylyht.domain

case class Username(val value: String) {

    override def hashCode(): Int = value.hashCode()

    override def equals(other: Any): Boolean = {
        other match {
            case o: Username => o.value == this.value
            case _ => false
        }
    }
}
