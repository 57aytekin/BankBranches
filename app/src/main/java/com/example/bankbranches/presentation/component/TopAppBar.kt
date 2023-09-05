package com.example.bankbranches.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeTopBar(
    modifier: Modifier,
    fullName: String,
    bgColor: Color,
) {
    val letter = fullName
        .split(' ')
        .mapNotNull { it.firstOrNull()?.toString() }
        .reduce { acc, s -> acc + s }

    Box(modifier = Modifier.background(bgColor)){
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleFirstLetterName(nameLetter = letter)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = fullName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.background
            )
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopAppBar(
    title: String,
    containerColor: Color = MaterialTheme.colorScheme.background,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onClick.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerColor
        )

    )
}