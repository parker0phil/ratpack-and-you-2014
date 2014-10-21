import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    handler {
      println "1"
      next()
    }

    handler {
      println "2"
      next()
    }

    handler {
      println "Sending Response"
      response.send "${new Date()}"
    }

  }
}
