package com.satdev.jetpackcompose.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satdev.jetpackcompose.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()) {
    val uiState = viewModel.uiState
    val context = LocalContext.current
    Column(modifier = modifier.padding(16.dp)) {
        if (uiState.loadingState) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        if (uiState.resultMessage.isNotEmpty()) {
            Toast.makeText(context, uiState.resultMessage, Toast.LENGTH_SHORT).show()

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { viewModel.onEvent(HomeScreenEvents.OnGetSchemeClick) }) {
                Text(text = stringResource(R.string.obtener_esquema))
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(onClick = { viewModel.onEvent(HomeScreenEvents.OnListTablesClick) }) {
                Text(text = stringResource(R.string.leer_tablas))
            }
        }
        if (uiState.tableNameList.isEmpty()) {
            Text(
                text = "No hay Tablas por mostrar",
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            HomeList(
                list = uiState.tableNameList,
                uiState.filterState,
                filterQuery = uiState.searchQuery,
                onQueryChange = { viewModel.onEvent(HomeScreenEvents.OnQueryChange(it)) })
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeList(
    list: List<String?>,
    listFilter: (String?) -> Boolean,
    filterQuery: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterList = remember(list, listFilter) {
        list.filter(listFilter)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = filterQuery, onValueChange = onQueryChange, placeholder = {
            Text(text = stringResource(R.string.buscar))
        })
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(filterList) {
                it?.let {
                    HomeItem(string = it)
                }
            }
        }
    }
}

@Composable
fun HomeItem(string: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = string, modifier = Modifier
            .padding(10.dp)
            .align(Alignment.CenterHorizontally))
    }
}