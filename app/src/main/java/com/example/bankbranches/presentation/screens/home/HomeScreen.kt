package com.example.bankbranches.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bankbranches.R
import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.presentation.component.HomeCardItem
import com.example.bankbranches.presentation.component.HomeTopBar
import com.example.bankbranches.presentation.component.ProgressIndicator
import com.example.bankbranches.presentation.component.SearchTextField
import com.example.bankbranches.presentation.navigation.Destination
import com.example.bankbranches.ui.theme.Blue60
import com.example.bankbranches.ui.theme.Blue70
import com.example.bankbranches.util.NetworkUtil
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val lazyState = rememberLazyListState()
    val viewStateHome by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val userFullName = "Aytekin Çömez"
    LaunchedEffect(Unit) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is HomeViewEvent.SnackBarError -> {
                        snackBarHostState.showSnackbar(it.message ?: "")
                    }
                    else -> {}
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            HomeTopBar(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                fullName = userFullName,
                bgColor = Blue60
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Blue60)
            ){
                if (viewStateHome.searchedBankList?.isEmpty() == true && !viewStateHome.isLoading){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.msg_no_available_data),
                    )
                }
                val coroutineScope = rememberCoroutineScope()
                Column {
                    var input by remember { mutableStateOf(TextFieldValue(viewStateHome.searchText)) }
                    TopItem(
                        userFullName = userFullName,
                        input = input,
                        onSearch = {
                            input = it
                            viewModel.setCitySearch(it.text)
                        },
                        onClear = {
                            input = TextFieldValue("")
                            coroutineScope.launch {
                                lazyState.scrollToItem(0)
                            }
                            viewModel.clearSearch()
                        }
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ) {
                        if (viewStateHome.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                ProgressIndicator(modifier = Modifier.size(75.dp))
                            }
                        }
                        NetworkControlAndReload{
                            viewModel.onTriggerEvent(HomeViewEvent.GetBankBranches)
                        }
                        LazyColumn(
                            state = lazyState,
                            modifier = Modifier.padding(top = 16.dp),
                            content = {
                                items(viewStateHome.searchedBankList ?: listOf()){ item ->
                                    HomeCardItem(
                                        bankBranch = item,
                                        onClick = {
                                            val jsonAdapter: JsonAdapter<BankBranch> = viewModel.moshi.adapter(BankBranch::class.java)
                                            navigateDetail(jsonAdapter, navController, coroutineScope, item)
                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }
        },
    )
}

@Composable
fun NetworkControlAndReload(
    onClickReload: () -> Unit
) {
    if (!NetworkUtil.isNetworkAvailable(LocalContext.current)){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.txt_check_your_connection),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onClickReload) {
                    Text(text = stringResource(id = R.string.txt_try_again))
                }
            }
        }
    }
}

fun navigateDetail(
    jsonAdapter: JsonAdapter<BankBranch>,
    navController: NavController,
    coroutineScope: CoroutineScope,
    item: BankBranch
) {
    coroutineScope.launch {
        var json = ""
        withContext(Dispatchers.IO){
            json = jsonAdapter.toJson(
                item.copy(
                    adres = URLEncoder.encode(item.adres, StandardCharsets.UTF_8.toString()),
                    enYakinAtm = URLEncoder.encode(item.enYakinAtm, StandardCharsets.UTF_8.toString()),
                    bankaSube = URLEncoder.encode(item.bankaSube, StandardCharsets.UTF_8.toString()),
                    adresAdi = URLEncoder.encode(item.adresAdi, StandardCharsets.UTF_8.toString()),
                )
            )
        }

        navController.navigate(
            route = Destination.BranchDetail.invoke(json),
        )
    }
}

@Composable
fun TopItem(
    userFullName: String,
    input: TextFieldValue,
    onSearch: (TextFieldValue) -> Unit,
    onClear: () -> Unit
) {
    Column(
        Modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = "${stringResource(id = R.string.txt_welcome)}, ${userFullName.split(" ").first()}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.background
        )
        SearchTextField(
            modifier = Modifier.padding(top = 8.dp),
            searchText = input,
            onSearchFocusChange = {},
            onSearch = onSearch,
            onClearQuery = onClear,
            hintText = stringResource(id = R.string.txt_search_branch),
            bgColor = Blue70
        )
    }
}
