package sample.api

import org.scalatra.ScalatraFilter

class SampleAPI extends ScalatraFilter {
  get("/api/sample/hello") {
    status(200)
    contentType = "text/plane"
    "Hello " + params.getOrElse('name, "world")
  }
}
