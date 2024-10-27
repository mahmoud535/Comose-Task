package com.example.composetask.presentation.feature.authentication.login

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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.composetask.domain.model.LoginRequest
import com.example.composetask.presentation.feature.home.Main.MainActivity
import com.example.composetask.presentation.utils.navigation.Screen
import kotlinx.coroutines.launch


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    val mockNavController = rememberNavController()
    LoginView(navController = mockNavController)
}

@Composable
fun LoginView(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        PutUIInColumn(
            viewModel = viewModel,
            navController = navController,
            snackbarHostState = snackBarHostState
        )
    }
}

@Composable
fun PutUIInColumn(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        ShowUI(
            viewModel = viewModel,
            navController = navController,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun ShowUI(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val username = remember { mutableStateOf("johnd") }
    val password = remember { mutableStateOf("m38rmF$") }
    var showProgress by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(150.dp))
    ImageLogo()
//    userNameTextField(username)
//    Spacer(modifier = Modifier.height(20.dp))
//    textFieldPassword(password)
//    Spacer(modifier = Modifier.height(40.dp))
    CustomTextField(
        text = username,
        label = "Enter your name",
        placeholder = "e.g., John Doe",
        keyboardType = KeyboardType.Text
    )
    Spacer(modifier = Modifier.height(20.dp))

    CustomTextField(
        text = password,
        label = "Password",
        placeholder = "Enter your password",
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(modifier = Modifier.height(40.dp))

    loginBtn(
        username.value,
        password.value,
        viewModel,
        showProgress,
        { showProgress = it },
        snackbarHostState
    )
    TextRegister(navController)
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
fun CustomTextField(
    text: MutableState<String>,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    var passwordVisible by remember { mutableStateOf(false) } // State for password visibility
    val iconVisible = painterResource(id = R.drawable.see_icon)
    val iconInvisible = painterResource(id = R.drawable.un_see_icon)

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(label, color = Color.LightGray) },
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
        placeholder = { Text(placeholder) },
        leadingIcon = {
            if (keyboardType == KeyboardType.Text) { // Show Person icon for email
                Icon(Icons.Filled.Person, contentDescription = "Person")
            } else if (keyboardType == KeyboardType.Password) { // Show Lock icon for password
                Icon(Icons.Filled.Lock, contentDescription = "Lock")
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorResource(id = R.color.purple_700),
        ),
        trailingIcon = if (keyboardType == KeyboardType.Password) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = if (passwordVisible) iconVisible else iconInvisible,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        } else null,
        shape = RoundedCornerShape(30.dp),
        interactionSource = interactionSource
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
        label = { androidx.compose.material3.Text("Enter your name", color = Color.LightGray) },
        placeholder = { androidx.compose.material3.Text("e.g., John Doe") },
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
fun textFieldPassword(password: MutableState<String>) {
    var passwordVisible by remember { mutableStateOf(false) }
    val iconVisible = painterResource(id = R.drawable.see_icon)
    val iconInvisible = painterResource(id = R.drawable.un_see_icon)
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
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        shape = RoundedCornerShape(30.dp),
        interactionSource = interactionSource
    )
}

@Composable
fun loginBtn(
    username: String,
    password: String,
    viewModel: LoginViewModel,
    showProgress: Boolean,
    updateShowProgress: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val loginResult by viewModel.loginResult.observeAsState()
    val context = LocalContext.current
    Button(
        onClick = {
            updateShowProgress(true)
            val loginRequest = LoginRequest(username, password)
            viewModel.login(loginRequest)
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
            androidx.compose.material3.Text(text = "LOGIN")
        }
    }

    loginResult?.let { result ->
        updateShowProgress(false)
        result.onSuccess {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }.onFailure { exception ->
            val scope = rememberCoroutineScope()
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = exception.message ?: "An error occurred",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}

@Composable
fun TextRegister(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.Text(
            text = "Dont have an Account? ",
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 13.sp,
                fontStyle = FontStyle.Normal,
                background = Color.White,
            )
        )
        androidx.compose.material3.Text(
            text = "Register",
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable {
                    navController.navigate(Screen.Register.route)
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
