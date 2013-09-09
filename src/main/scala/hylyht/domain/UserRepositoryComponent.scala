package hylyht.domain

trait UserRepositoryComponent {

    def userRepository: UserRepository

    trait UserRepository {
        def createUser(username: Username, password: Password): User
    }
}