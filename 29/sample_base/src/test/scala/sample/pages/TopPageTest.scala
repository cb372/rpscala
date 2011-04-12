package sample.pages

import org.apache.wicket.util.tester.WicketTester
import org.junit.Test
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.ajax.markup.html.form.AjaxButton

class TopPageTest extends WicketTester {
  var tester = new WicketTester(new WicketApplication)

  @Test
  def testLogin {
    tester.startPage(new TopPage())
    tester.assertRenderedPage(classOf[TopPage])
    tester.assertComponent("postForm:postTextArea", classOf[TextArea[String]])
    tester.assertComponent("postForm:postButton", classOf[AjaxButton])
    tester.assertNoErrorMessage()
  }

}
