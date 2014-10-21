import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    handler registry.get(LoggingHandler)
    handler registry.get(CookieSettingHandler)

    handler {
      println "Sending Response"
      response.send "${new Date()}"
    }

  }
}
