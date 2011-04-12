package sample.model

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.SynchronizedBuffer

case class Post(body: String) {
  def save {
    Post.add(this)
  }
}

object Post {
  val data = new ListBuffer[Post]

  def add(post: Post) {
    synchronized {
      post +=: data
    }
  }
}
