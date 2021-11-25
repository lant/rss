# RSS

This is a script that parses a local html page with some specifc `<div class>` structure and generates
its corresponding rss feed. 

I made it just to generate the rss feed for my [blog](https://www.basarda.cat/marc/blog.html), I write 
all the posts in a single plain html, and it was hard to find any tool that could generate the rss. 

The approach is very simple: 

* Every post is wrapped in a `<div class="post">` tag. 
* The tile of the post is in the `<h2>` header. 
* The date of the post is wrapped in a `<div class="date">` tag.
* And the text of the post in html is wrapped in a `<div class="post_content">` tag.

Some of the values of the feed (blog name, url & description) are hardcoded, as this is basically a
personal project for my own blog. 

This is published in Github just in case it serves as example or "base project" for someone.

It is developed in Kotlin and it's using: 
* [Jsoup](https://jsoup.org/): To parse the html file. 
* [Rome](https://rometools.github.io/rome/): To generate the RSS xml feed file.
