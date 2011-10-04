package example1

import com.twitter.logging.Logger
import com.twitter.ostrich.admin.{RuntimeEnvironment,Service}
import com.twitter.ostrich.admin.config.ServerConfig

object Main {

  val log = Logger.get(getClass.getName)

  def main(args: Array[String]) {
    val runtime = RuntimeEnvironment(this, args)
    val server = runtime.loadRuntimeConfig[MyServer]()
    log.info("Starting my server!")
    try {
      server.start()
    } catch {
      case e: Exception =>
        e.printStackTrace()
        log.error(e, "Unexpected exception: %s", e.getMessage)
        System.exit(0)
    }
  }
}


class MyServerConfig extends ServerConfig[MyServer] {
  var serverPort: Int = 9999

  def apply(runtime: RuntimeEnvironment) = {
    new MyServer(serverPort)
  }
}

class MyServer(port: Int) extends Service {
  def start() {
    println("start server")
  }

  def shutdown() {
    println("shutdown server")
  }

  override def quiesce() {
    println("quiesce server")
  }

  override def reload() {
    println("reload server")
  }
}


// vim: set ts=2 sw=2 et:
