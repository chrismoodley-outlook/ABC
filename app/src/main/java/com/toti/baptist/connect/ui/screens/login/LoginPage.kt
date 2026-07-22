package com.toti.baptist.connect.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.toti.baptist.connect.ui.theme.Primary

@Composable
fun LoginPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Cross Icon
        Text(
            text = "✝️",
            fontSize = 48.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // App Name
        Text(
            text = "Toti Baptist Connect",
            style = MaterialTheme.typography.displaySmall,
            color = Primary,
            fontSize = 28.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        // Tagline
        Text(
            text = "Connect • Grow • Serve",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // Email Input
        Text(
            text = "Email",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter your email") },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            singleLine = true,
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Email,
                imeAction = androidx.compose.ui.text.input.ImeAction.Next
            ),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Password Input
        Text(
            text = "Password",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Enter your password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            singleLine = true,
            visualTransformation = if (showPassword) {
                androidx.compose.ui.text.input.VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Password,
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password Link
        TextButton(
            onClick = {
                // TODO: Navigate to Forgot Password screen
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.labelMedium,
                color = Primary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Button
        Button(
            onClick = {
                isLoading = true
                // TODO: Implement Firebase Authentication
                // For now, navigate to home
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = !isLoading && email.isNotEmpty() && password.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "SIGN IN",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )
            Text(
                text = "OR",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google Sign-In Button
        OutlinedButton(
            onClick = {
                // TODO: Implement Google Sign-In
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Primary)
        ) {
            Icon(
                painter = rememberAsyncImagePainter("https://www.gstatic.com/devrel-devsite/prod/v2210deb611920cd3130a47b5fab38e3853afc04b39a9d9ac4196e1cd7fbe04ef/android/images/favicons/android-chrome-192x192.png"),
                contentDescription = "Google",
                modifier = Modifier.size(20.dp),
                tint = Primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Sign in with Google",
                style = MaterialTheme.typography.labelLarge,
                color = Primary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign Up Link
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account? ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            TextButton(
                onClick = {
                    // TODO: Navigate to Sign Up screen
                },
                modifier = Modifier.padding(0.dp)
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Primary,
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun rememberAsyncImagePainter(url: String): androidx.compose.material.icons.painter.Painter {
    // Placeholder - in real implementation use Coil
    return androidx.compose.material.icons.rememberVectorPainter(
        image = Icons.Default.Email
    )
}
