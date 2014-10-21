package handlers

import ratpack.handling.Context
import ratpack.handling.Handler

class SimpleHandler implements Handler {
  @Override
  void handle(Context context) throws Exception {
    context.response.send "${new Date()}"
  }
}
