package dev.axgr

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FeedController(private val atom: AtomView, private val rss: RssView) {

  @GetMapping("/feed.rss")
  fun rss() = rss

  @GetMapping("/feed.atom")
  fun atom() = atom

}
