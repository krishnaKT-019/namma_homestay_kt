package com.example.nammahomestay.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nammahomestay.presentation.MenuViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyMenuScreen(
    onNavigateBack: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val currentMenu by viewModel.currentMenu.collectAsState()
    val isUpdating by viewModel.isUpdating.collectAsState()
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    
    var menuText by remember(currentMenu) { mutableStateOf(currentMenu?.items ?: "") }

    Scaffold(
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Update Daily Menu") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // ... rest of the column content
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = { 
                    viewModel.updateMenu(menuText, null)
                    scope.launch {
                        snackbarHostState.showSnackbar("Menu updated successfully!")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isUpdating && menuText.isNotBlank()
            ) {
                if (isUpdating) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Update Menu Now")
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            if (currentMenu != null) {
                Text(
                    text = "Current Active Menu:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(text = currentMenu?.items ?: "")
            }
        }
    }
}
