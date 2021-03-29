package com.example.androiddevchallenge.component

import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Location(modifier: Modifier = Modifier, location: String) {
    Text(
        text = location,
        style = MaterialTheme.typography.subtitle1,
        modifier = modifier.paddingFromBaseline(56.dp)
    )
}