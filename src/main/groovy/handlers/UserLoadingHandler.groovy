package handlers

import domain.User
import ratpack.handling.Context
import ratpack.handling.Handler
import services.UserService

import javax.inject.Inject

class UserLoadingHandler implements Handler {

  final UserService service

  @Inject
  UserLoadingHandler(UserService service) {
    this.service = service
  }

  @Override
  void handle(Context context) throws Exception {
    context.with {
      def email = response.cookies.find { it.name == 'email' } ?: request.cookies.find { it.name == 'email' }

      def userPromise = service.findByEmail(email?.value)
      userPromise.then { User user ->
        request.add(User, user)
        next()
      }
    }
  }
}
