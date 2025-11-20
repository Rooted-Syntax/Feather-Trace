package com.example.feathertrace.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun BirdLogEntryScreen(onSave: (String, String) -> Unit) {
    var birdName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Log a Bird Sighting",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = birdName,
            onValueChange = { birdName = it },
            label = { Text("Bird Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (birdName.isNotBlank() && location.isNotBlank()) {
                    onSave(birdName, location)
                    Toast.makeText(context, "Bird logged!", Toast.LENGTH_SHORT).show()
                    birdName = ""
                    location = ""
                    Log.d("BirdLogEntryScreen", "Save clicked with: $birdName at $location")
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}
