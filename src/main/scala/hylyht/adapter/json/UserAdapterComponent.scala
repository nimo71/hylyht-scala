package hylyht.adapter.json

import hylyht.domain.User

trait UserAdapterComponent {

    def userAdapter: UserAdapter

    trait UserAdapter {
        def unmarshall(from: String): User
        def marshall(from: User): String
    }

}
