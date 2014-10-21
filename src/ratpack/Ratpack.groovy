import handlers.CookieClearingHandler
import handlers.CookieSettingHandler
import handlers.LoggingHandler
import services.UserService

import static ratpack.groovy.Groovy.ratpack

ratpack {

  bindings {
    bind UserService
  }

  handlers {

    handler registry.get(LoggingHandler)

    get "login", registry.get(CookieSettingHandler)
    get "logout", registry.get(CookieClearingHandler)

    handler { UserService service ->
      def email = response.cookies.find { it.name == 'email' } ?: request.cookies.find { it.name == 'email'}
      def user = service.findByEmail(email?.value)
      response.send "Hello ${user?.email ?: 'guest'}"
    }

  }
}
