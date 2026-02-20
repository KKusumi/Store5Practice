package com.example.store5practice.core.model

/**
 * 記事を表すドメインモデル。
 *
 * @property title 記事のタイトル
 * @property url 記事のURL
 */
data class Article(
    val title: String,
    val url: String,
)
