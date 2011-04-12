package sample.pages

import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.{Form, TextArea}
import sample.model.Post
import org.apache.wicket.markup.html.list.{ListItem, ListView}
import org.apache.wicket.model.{AbstractReadOnlyModel, PropertyModel}
import org.apache.wicket.markup.html.basic.Label
import java.util.{List => JList}
import scala.collection.JavaConversions._
import org.apache.wicket.markup.html.{WebMarkupContainer, WebPage}

class TopPage extends WebPage {

  val timelineContainer = new WebMarkupContainer("timeline") {
    add(new ListView("timelineList", new AbstractReadOnlyModel[JList[Post]] {
      def getObject = Post.data.toList
    }) {
      override def populateItem(item: ListItem[Post]) {
        val post = item.getModelObject
        item.add(new Label("body", post.body))
      }
    })
    this.setOutputMarkupId(true)
  }
  add(timelineContainer)

  add(new Form("postForm") {
    var post = ""
    val postTextArea = new TextArea("postTextArea", new PropertyModel(this, "post"))
    add(postTextArea)
    add(new AjaxButton("postButton") {
      def onSubmit(target: AjaxRequestTarget, form: Form[_]) {
        if (post.length <= 3) {
          Post(post).save
          post = ""
        } else {
          target.appendJavascript("alert('too long!')")
        }
        target.addComponent(this.getParent)
        target.addComponent(timelineContainer)
      }
    })
  })
}
