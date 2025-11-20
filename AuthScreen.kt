package com.example.feathertrace.ui

import androidx.compose.foundation.Image
import com.example.feathertrace.R
import androidx.compose.ui.res.painterResource
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthScreen(onAuthSuccess: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bird_login_bg),
            contentDescription = "Login background",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )

        Text("Feather Tracker Login", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener { onAuthSuccess() }
                        .addOnFailureListener {
                            Toast.makeText(context, "Login failed: ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Login")
            }

            Button(
                onClick = {
                    if (email.isBlank() || password.length < 6) {
                        Toast.makeText(context, "Enter valid email and password (6+ chars)", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    try {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener { onAuthSuccess() }
                            .addOnFailureListener {
                                Toast.makeText(context, "Signup failed: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                    } catch (e: Exception) {
                        Toast.makeText(context, "Unexpected error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Register")
            }

            Button(
                onClick = {
                    FirebaseAuth.getInstance().signInAnonymously()
                        .addOnSuccessListener { onAuthSuccess() }
                        .addOnFailureListener {
                            Toast.makeText(context, "Skip failed: ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Skip")
            }
        }
    }
}