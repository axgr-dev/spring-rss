package dev.axgr

import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

data class Post(val title: String, val timestamp: Instant, val author: String, val content: String)

@Service
class PostService {

  fun posts(): List<Post> {
    val now = Instant.now()
    return (1..10).map { i ->
      Post("Title $i", now.minus(i.toLong(), ChronoUnit.DAYS), "Author $i",
        """
        <h1>Content</h1>
        <p>This is post no. $i</p>
        """
      )}
  }

}
