import domain.User
import handlers.CookieClearingHandler
import handlers.CookieSettingHandler
import handlers.LoggingHandler
import handlers.UserLoadingHandler
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

    handler registry.get(UserLoadingHandler)

    handler {
      User user = request.maybeGet(User)
      response.send "Hello ${user?.email ?: 'guest'}"
    }

  }
}
