package com.example.composetask.presentation.feature.authentication.register

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composetask.R
import com.example.composetask.domain.model.user.Address
import com.example.composetask.domain.model.user.Geolocation
import com.example.composetask.domain.model.user.Name
import com.example.composetask.domain.model.user.User
import com.example.composetask.domain.model.user.UserResponse
import com.example.composetask.presentation.feature.home.Main.MainActivity
import com.example.composetask.presentation.utils.navigation.Screen


@Composable
fun RegisterView(
    navController: NavController,
    registerViewModel: UserViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val registrationState = registerViewModel.registrationState.value
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) } // Provide SnackbarHost
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            var showProgress by remember { mutableStateOf(false) }
            Spacer(modifier = Modifier.height(100.dp))
            ImageLogo()
            userNameTextField(username)
            Spacer(modifier = Modifier.height(20.dp))
            emailTextField(email)
            Spacer(modifier = Modifier.height(20.dp))
            phoneTextField(phone)
            Spacer(modifier = Modifier.height(20.dp))
            textFieldPassword(password)
            Spacer(modifier = Modifier.height(40.dp))
            registerBtn(
                username.value,
                password.value,
                email.value,
                phone.value,
                navController,
                showProgress,
                { showProgress = it },
                registerViewModel
            )
            textRegister(navController)
            val context = LocalContext.current
            LaunchedEffect(registrationState) {
                registrationState?.let { result: Result<UserResponse> -> // Specify the type explicitly
                    result.onSuccess { userResponse: UserResponse -> // Specify the type explicitly
                        snackbarHostState.showSnackbar("Registration successful!")
                      val intent  = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)

                    }.onFailure { error: Throwable -> // Specify the type explicitly
                        snackbarHostState.showSnackbar("Registration failed: ${error.message}")
                    }
                }
            }
        }
    }
}


@Composable
fun ImageLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo4),
        contentDescription = "logo",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(end = 200.dp),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun userNameTextField(username: MutableState<String>) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    TextField(
        value = username.value,
        onValueChange = { username.value = it },
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .border(
                width = 1.dp,
                brush = if (isFocused) {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.orange120),
                            colorResource(id = R.color.lightOrange)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.gray),
                            colorResource(id = R.color.gray)
                        )
                    )
                },
                shape = RoundedCornerShape(50.dp)
            ),
        label = { Text("Enter your name", color = Color.LightGray) },
        placeholder = { Text("e.g., John Doe") },
        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Person") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorResource(id = R.color.purple_700),
        ),
        shape = RoundedCornerShape(30.dp),
        interactionSource = interactionSource
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun emailTextField(email: MutableState<String>) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    TextField(
        value = email.value,
        onValueChange = { email.value = it },
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .border(
                width = 1.dp,
                brush = if (isFocused) {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.orange120),
                            colorResource(id = R.color.lightOrange)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.gray),
                            colorResource(id = R.color.gray)
                        )
                    )
                },
                shape = RoundedCornerShape(50.dp)
            ),
        label = { Text("Enter your email", color = Color.LightGray) },
        placeholder = { Text("e.g., mahmoud@gmail.com") },
        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorResource(id = R.color.purple_700),
        ),
        shape = RoundedCornerShape(30.dp),
        interactionSource = interactionSource
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun phoneTextField(Phone: MutableState<String>) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    TextField(
        value = Phone.value,
        onValueChange = { Phone.value = it },
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .border(
                width = 1.dp,
                brush = if (isFocused) {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.orange120),
                            colorResource(id = R.color.lightOrange)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.gray),
                            colorResource(id = R.color.gray)
                        )
                    )
                },
                shape = RoundedCornerShape(50.dp)
            ),
        label = { Text("Enter your Phone Number", color = Color.LightGray) },
        placeholder = { Text("e.g., John Doe") },
        leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = "Phone") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorResource(id = R.color.purple_700),
        ),
        shape = RoundedCornerShape(30.dp),
        interactionSource = interactionSource
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldPassword(password: MutableState<String>) {
    var passwordVisible by remember { mutableStateOf(false) }
    val iconVisible =
        painterResource(id = R.drawable.see_icon)
    val iconInvisible =
        painterResource(id = R.drawable.un_see_icon)
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()


    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text("Password", color = Color.LightGray) },
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .border(
                width = 1.dp,
                brush = if (isFocused) {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.orange120),
                            colorResource(id = R.color.lightOrange)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.gray),
                            colorResource(id = R.color.gray)
                        )
                    )
                },
                shape = RoundedCornerShape(50.dp)
            ),
        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Person") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorResource(id = R.color.purple_700),
        ),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = if (passwordVisible) iconVisible else iconInvisible,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    modifier = Modifier.size(25.dp) // Set icon size to 20.dp
                )
            }
        },
        shape = RoundedCornerShape(30.dp),
        interactionSource = interactionSource
    )

}

@Composable
fun registerBtn(
    username: String,
    password: String,
    email: String,
    phone: String,
    navController: NavController,
    showProgress: Boolean,
    updateShowProgress: (Boolean) -> Unit,
    registerViewModel: UserViewModel
) {
    Button(
        onClick = {
            updateShowProgress(true)

            val user = User(
                email = email,
                username = username,
                password = password,
                name = Name("John", "Doe"),
                address = Address(
                    city = "Unknown", // Add actual values based on user inputs
                    street = "Unknown",
                    number = 0,
                    zipcode = "00000",
                    geolocation = Geolocation("0.0", "0.0")
                ),
                phone = phone
            )
            registerViewModel.registerUser(user)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(start = 30.dp, end = 30.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.darkBlue),
                        colorResource(id = R.color.blue),
                    )
                ),
                shape = RoundedCornerShape(30.dp)
            ),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        if (showProgress) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(text = "REGISTER")
        }
    }
}

@Composable
fun textRegister(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Already have an account? ",
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 13.sp,
                fontStyle = FontStyle.Normal,
                background = Color.White,
            )
        )
        Text(
            text = "Login",
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable {
                    navController.navigate(Screen.Login.route)
                },
            style = TextStyle(
                color = colorResource(id = R.color.darkBlue),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                background = Color.White,
                textDecoration = TextDecoration.Underline
            )

        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp",
    name = "Register Screen"
)
@Composable
fun LoginScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}


