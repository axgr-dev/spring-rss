package dev.axgr

import com.rometools.rome.feed.atom.Content
import com.rometools.rome.feed.atom.Entry
import com.rometools.rome.feed.atom.Feed
import com.rometools.rome.feed.rss.Channel
import com.rometools.rome.feed.rss.Item
import com.rometools.rome.feed.synd.SyndPersonImpl
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView
import org.springframework.web.servlet.view.feed.AbstractRssFeedView
import java.util.*

@Component
class AtomView(private val service: PostService) : AbstractAtomFeedView() {

  override fun buildFeedMetadata(model: MutableMap<String, Any>, feed: Feed, request: HttpServletRequest) {
    feed.title = "My Amazing Feed"
    feed.language = "en-US"
    feed.updated = Date.from(service.posts().maxByOrNull { it.timestamp }!!.timestamp)
  }

  override fun buildFeedEntries(model: MutableMap<String, Any>, request: HttpServletRequest, response: HttpServletResponse): List<Entry> {
    return service.posts().map { post ->
      val entry = Entry()
      entry.title = post.title
      entry.updated = Date.from(post.timestamp)
      entry.authors = listOf(SyndPersonImpl().apply {
        name = post.author
      })
      entry.contents = listOf(Content().apply {
        type = "html"
        value = post.content
      })

      entry
    }
  }
}

@Component
class RssView(private val service: PostService) : AbstractRssFeedView() {

  override fun buildFeedMetadata(model: MutableMap<String, Any>, feed: Channel, request: HttpServletRequest) {
    feed.title = "My Amazing Feed"
    feed.description = "A feed about amazing stuff"
    feed.link = "https://axgr.dev"
    feed.lastBuildDate = Date.from(service.posts().maxByOrNull { it.timestamp }!!.timestamp)
    feed.language = "en-US"
  }

  override fun buildFeedItems(model: MutableMap<String, Any>, request: HttpServletRequest, response: HttpServletResponse): List<Item> {
    return service.posts().map { post ->
      Item().apply {
        title = post.title
        author = post.author
        content = com.rometools.rome.feed.rss.Content().apply {
          type = "html"
          value = post.content
        }
      }
    }

  }
}

