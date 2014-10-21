import ratpack.handling.Context
import ratpack.handling.Handler

class CookieSettingHandler implements Handler {
  @Override
  void handle(Context context) throws Exception {
    context.with {
      def email = request.queryParams.get("email")
      if (email) {
        response.cookie("email", email)
      }
      next()
    }
  }
}
