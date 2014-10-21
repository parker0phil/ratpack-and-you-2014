package handlers

import ratpack.handling.Context
import ratpack.handling.Handler

class CookieClearingHandler implements Handler {

  @Override
  void handle(Context context) throws Exception {
    context.with {
      response.expireCookie('email')
      next()
    }
  }
}
