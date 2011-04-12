package sample.api

import org.scalatra.ScalatraFilter
import sample.model.Post
import net.liftweb.json.JsonDSL._
import net.liftweb.json._


class TimelineAPI extends ScalatraFilter {
  get("/api/timeline/latest") {
    status(200)
    pretty(render(Post.data map ("body" -> _.body) toList))
  }
}
