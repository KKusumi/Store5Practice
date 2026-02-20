package com.example.store5practice.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store5practice.data.sampleArticles
import com.example.store5practice.core.model.Article

/**
 * 記事一覧画面。
 *
 * 「記事を取得」ボタンを押すとモック記事のリストを表示する。
 */
@Composable
fun ArticleListScreen(modifier: Modifier = Modifier) {
    var articles by remember { mutableStateOf<List<Article>>(emptyList()) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
    ) {
        Button(
            onClick = { articles = sampleArticles },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("記事を取得")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(articles) { article ->
                ArticleItem(article)
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ArticleItem(article: Article) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Text(
            text = article.title,
            fontSize = 16.sp,
        )
        Text(
            text = article.url,
            fontSize = 14.sp,
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.url)))
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleListScreenPreview() {
    ArticleListScreen()
}
