package com.example.bankbranches.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankbranches.ui.theme.Blue60

@Composable
fun CircleFirstLetterName(
    nameLetter: String,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .size(45.dp)
            .background(
                MaterialTheme.colorScheme.background,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ){
        Row {
            Text(
                text = nameLetter,
                style = MaterialTheme.typography.titleMedium,
                color = Blue60
            )
        }
    }
}