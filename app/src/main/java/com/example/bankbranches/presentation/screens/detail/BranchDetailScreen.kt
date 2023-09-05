package com.example.bankbranches.presentation.screens.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bankbranches.R
import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.presentation.component.NavigationTopAppBar
import com.example.bankbranches.presentation.component.OutlinedButtonWithArrow
import com.example.bankbranches.presentation.screens.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetailScreen(
    navController: NavController,
    bankBranch: BankBranch?
) {
    val viewModel: HomeViewModel = hiltViewModel()
    LaunchedEffect(Unit){
        viewModel.sendScreenViewEventForAnalytics()
    }
    Scaffold(
        topBar = {
            NavigationTopAppBar(title = bankBranch?.adresAdi ?: ""){
                navController.popBackStack()
            }
        },
        content = { paddingValues ->
            val context = LocalContext.current
            Box(Modifier.padding(paddingValues)){
                Column {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f),
                        painter = painterResource(id = R.drawable.google_map), contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    DetailRowItem(
                        title = "${stringResource(id = R.string.txt_branch_name)}:",
                        desc = bankBranch?.bankaSube ?: ""
                    )
                    DetailRowItem(
                        title = "${stringResource(id = R.string.txt_branch_type)}:",
                        desc = bankBranch?.bankaTipi ?: ""
                    )
                    DetailRowItem(
                        title = "${stringResource(id = R.string.txt_branch_code)}:",
                        desc = bankBranch?.bankKodu ?: ""
                    )
                    DetailRowItem(
                        title = "${stringResource(id = R.string.txt_branch_address)}:",
                        desc = bankBranch?.adres ?: ""
                    )
                    OutlinedButtonWithArrow(text = stringResource(id = R.string.txt_get_address)){
                        val gmmIntentUri =
                            Uri.parse("geo:0,0?q=${bankBranch?.adres}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(context, mapIntent, null)
                    }
                }
            }
        }
    )
}

@Composable
fun DetailRowItem(
    modifier: Modifier = Modifier,
    title: String,
    desc: String
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 8.dp),
                style = MaterialTheme.typography.titleMedium,
                text = title
            )
            Box(
                modifier = Modifier.weight(1f)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    text = desc,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
        Divider(Modifier.background(Color.Gray))
    }
}

