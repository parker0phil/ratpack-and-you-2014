package handlers

import ratpack.handling.Context
import ratpack.handling.Handler

class LoggingHandler implements Handler {
  @Override
  void handle(Context context) throws Exception {
    context.with {
      println "${new Date().format("yyyy-MM-dd HH:mm:ss")} - ${request.method} - ${request.uri}"
      next()
    }
  }
}
