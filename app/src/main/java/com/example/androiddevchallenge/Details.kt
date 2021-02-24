package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable fun Details(dog: Dog) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column {
            Text(text = dog.name, style = MaterialTheme.typography.h3)
            Text(
                text = "${dog.rating}/10",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )

            Text(
                text = dog.description,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview
@Composable fun Details() = Details(Dog("Dog", "An eponymous dog", 10))
