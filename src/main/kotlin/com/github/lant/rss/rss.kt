package com.github.lant.rss

import com.sun.syndication.feed.synd.*
import com.sun.syndication.io.SyndFeedOutput
import org.jsoup.Jsoup
import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.text.SimpleDateFormat
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    if (args.size != 1) {
        println("Program expects one argument. The html file to extract the rss from.")
        exitProcess(22)
    }

    val dateParser = SimpleDateFormat("dd/MM/yyyy");
    val document = Jsoup.parse(File(args[0]).readText())
    val posts = document.getElementsByClass("post")

    // setting up the rss
    val entries = mutableListOf<SyndEntry>()
    val feed: SyndFeed = SyndFeedImpl()
    feed.feedType = "rss_2.0"
    feed.title = "Marc de Palol's Blog"
    feed.link = "https://www.basarda.cat/marc/blog.html"
    feed.description = "Marc de Palol's Blog rss feed"

    // parsing and filling the feed
    posts.forEach { post ->
        val description = SyndContentImpl().apply {
            type = "texts/html"
            value = post.getElementsByClass("post_content").html()
        }
        val entry = SyndEntryImpl().apply {
            title = post.getElementsByTag("h2").text()
            link = "#" + post.getElementsByTag("a").attr("id")
            publishedDate = dateParser.parse(post.getElementsByClass("date").text())
        }
        entry.description = description
        entries.add(entry)
    }

    feed.entries = entries;
    val writer: Writer = FileWriter("feed.xml")
    val output = SyndFeedOutput()
    output.output(feed, writer)
    writer.close()
}