package services

import domain.User

class UserService {

  User findByEmail(String email) {
    email ? new User(email: email) : null
  }

}
