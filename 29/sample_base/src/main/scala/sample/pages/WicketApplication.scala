package sample.pages

import org.apache.wicket.protocol.http.WebApplication

class WicketApplication extends WebApplication {
  override val getHomePage = classOf[TopPage]
}
