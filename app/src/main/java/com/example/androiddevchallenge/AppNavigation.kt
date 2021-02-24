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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate

internal enum class Destination {
    ListScreen, DetailsScreen
}

internal fun NavHostController.showDetails(dogIndex: Int) = navigate(
    "${Destination.DetailsScreen}/$dogIndex"
) {
    launchSingleTop = true
}

@Composable
internal fun AppNavHost(
    navController: NavHostController,
) = NavHost(
    navController = navController,
    startDestination = Destination.ListScreen
) {
    composable(Destination.ListScreen) {
        Column {
            Surface(
                color = MaterialTheme.colors.surface,
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp,
            ) {
                Text(
                    text = "Pick a dog!",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h6,
                )
            }
            DogList(dogs = SampleData.dogs, onItemClicked = { navController.showDetails(it) })
        }
    }
    val dogIndexName = "dogIndex"
    composable(
        route = "${Destination.DetailsScreen}/{$dogIndexName}",
        arguments = listOf(navArgument(name = dogIndexName) { type = NavType.IntType })
    ) {
        val index = it.arguments!!.getInt("dogIndex")
        Details(SampleData.dogs[index])
    }
}

@Composable
private fun NavHost(
    navController: NavHostController,
    startDestination: Enum<*>,
    route: Enum<*>? = null,
    builder: NavGraphBuilder.() -> Unit
): Unit =
    androidx.navigation.compose.NavHost(navController, startDestination.name, route?.name, builder)

private fun NavGraphBuilder.composable(
    route: Enum<*>,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(route.toString(), arguments, deepLinks, content)
