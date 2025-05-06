package com.assesment.jobfinder.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            strokeWidth = 2.dp
        )
    }
}

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = "Empty",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }(
        imageVector = Icons.Default.Error,
        contentDescription = "Error",
        modifier = Modifier.size(64.dp),
        tint = MaterialTheme.colors.error
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = message,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier.padding(horizontal = 32.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    androidx.compose.material.Button(
        onClick = onRetry
    ) {
        Text("Retry")
    }
}
}

@Composable
fun EmptyView(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon