package services

class UserService {

  def findByEmail(String email) {
    email ? [email: email] : null
  }

}
