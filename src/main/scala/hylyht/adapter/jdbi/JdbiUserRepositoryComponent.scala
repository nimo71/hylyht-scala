package hylyht.adapter.jdbi

import hylyht.domain.{User, Password, Username, UserRepositoryComponent}
import org.slf4j.LoggerFactory

trait JdbiUserRepositoryComponent extends UserRepositoryComponent {

    private def logger = LoggerFactory.getLogger(classOf[JdbiUserRepositoryComponent])

    def userRepository = new JdbiUserRepository

    class JdbiUserRepository extends UserRepository {
        def createUser(username: Username, password: Password): User = {
            val user = User(username, password)
            logger.debug("created user="+ user)
            user
        }
    }
}
