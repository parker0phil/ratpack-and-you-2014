import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    handler {
      response.send "${new Date()}"
    }

  }
}
