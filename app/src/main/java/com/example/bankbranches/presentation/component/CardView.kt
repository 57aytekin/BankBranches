package com.example.bankbranches.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankbranches.R
import com.example.bankbranches.domain.model.BankBranch


@Composable
fun HomeCardItem(
    bankBranch: BankBranch,
    onClick:(BankBranch) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(8.dp)
            .clickable { onClick.invoke(bankBranch) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.card_background),
                contentDescription = null,
                alignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Row(
                Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = bankBranch.bankaSube ?: "",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Şube Kodu: ${bankBranch.bankKodu}",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Adress: ${bankBranch.adres}",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis

                    )
                }
                Image(
                    painterResource(id = R.drawable.ic_location),
                    contentDescription = ""
                )
            }
        }
    }
}