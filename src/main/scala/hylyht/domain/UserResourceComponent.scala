package hylyht.domain


trait UserResourceComponent {
    this: UserRepositoryComponent =>

    def userResource: UserResource = new UserResource

    class UserResource {
        def createUser(username: Username, password: Password): User =
        {
            userRepository.createUser(username, password)
        }
    }
}
