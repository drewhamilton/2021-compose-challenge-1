/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DogList(
    dogs: List<Dog>,
    onItemClicked: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) = LazyColumn(
    modifier = modifier.fillMaxSize()
) {
    items(dogs.size) { index ->
        DogItem(
            dog = dogs[index],
            modifier = Modifier.clickable { onItemClicked(index) }
        )
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
private fun DogList() = DogList(SampleData.dogs, onItemClicked = {})

@Composable
private fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) = Text(
    text = dog.name,
    modifier = modifier
        .padding(16.dp)
        .fillMaxWidth(),
    style = MaterialTheme.typography.body1,
)

@Preview
@Composable
private fun DogItem() = DogItem(SampleData.dogs[0])
