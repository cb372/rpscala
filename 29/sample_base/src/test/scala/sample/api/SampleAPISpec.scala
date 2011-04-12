package sample.api

import org.scalatra.test.specs.ScalatraSpecification
import org.specs.SpecificationWithJUnit

class SampleAPISpec extends SpecificationWithJUnit with ScalatraSpecification {
  addFilter(classOf[SampleAPI], "/api/sample/*")

  "SampleAPI" should {
    "get method for hello should return 200 status" in {
      get("/api/sample/hello") {
        status mustEqual (200)
        body mustEqual "Hello world"
      }
      get("/api/sample/hello?name=specs") {
        status mustEqual (200)
        body mustEqual "Hello specs"
      }
    }
    "post method should return 404 status" in {
      post("/api/sample/hello") {
        status mustEqual (404)
      }
    }
  }
}
