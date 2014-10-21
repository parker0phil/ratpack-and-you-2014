package handlers

import domain.User
import groovy.transform.CompileStatic
import ratpack.exec.ExecControl
import ratpack.handling.Context
import ratpack.handling.Handler
import services.UserService

import javax.inject.Inject

class UserLoadingHandler implements Handler {

  final ExecControl execControl
  final UserService service

  @Inject
  UserLoadingHandler(UserService service, ExecControl execControl) {
    this.execControl = execControl
    this.service = service
  }

  @Override
  void handle(Context context) throws Exception {
    context.with {
      def email = response.cookies.find { it.name == 'email' } ?: request.cookies.find { it.name == 'email' }
      execControl.blocking {
        service.findByEmail(email?.value)
      } then { User user ->
        request.add(User, user)
        next()
      }
    }
  }
}
