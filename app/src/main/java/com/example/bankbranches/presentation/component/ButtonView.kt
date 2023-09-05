package com.example.bankbranches.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.bankbranches.ui.theme.Blue60

@Composable
fun OutlinedButtonWithArrow(
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable { onClick.invoke() }
    ) {
        Row(
            Modifier.padding(16.dp)
        ) {
            Image(
                imageVector = Icons.Filled.LocationOn,
                colorFilter = ColorFilter.tint(color = Blue60),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                text = text
            )
            Image(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                colorFilter = ColorFilter.tint(color = Blue60),
                contentDescription = ""
            )
        }
        Divider(Modifier.background(Color.Gray))

    }
}