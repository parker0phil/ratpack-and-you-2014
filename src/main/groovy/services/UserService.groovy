package services

import domain.User
import groovy.transform.CompileStatic
import ratpack.exec.ExecControl
import ratpack.exec.Promise

import javax.inject.Inject

@CompileStatic
class UserService {

  final ExecControl execControl

  @Inject
  UserService(ExecControl execControl) {
    this.execControl = execControl
  }

  Promise<User> findByEmail(String email) {
    execControl.blocking {
      email ? new User(email: email) : null
    }
  }

}
