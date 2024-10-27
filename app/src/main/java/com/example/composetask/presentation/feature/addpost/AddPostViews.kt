package com.example.composetask.presentation.feature.addpost

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.composetask.R
import com.example.composetask.domain.model.Product
import java.io.File

@Composable
fun AddPostView(viewModel: AddPostViewModel = hiltViewModel(), navController: NavController) {
    val textTitle = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    var imageFile: File? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePicker { file ->
          imageFile = file
        }
        TextField(
            textTitle = textTitle,
            keyboardType = KeyboardType.Text,
            placeholderText = "enter title"
        )
        TextField(
            textTitle = price,
            keyboardType = KeyboardType.Number,
            placeholderText = "add price"
        )
        TextField(
            textTitle = description,
            keyboardType = KeyboardType.Text,
            placeholderText = "add description"
        )
        TextField(
            textTitle = category,
            keyboardType = KeyboardType.Text,
            placeholderText = "add category"
        )
        AddProductBtn(textTitle.value, price.value, category.value, description.value, viewModel, imageFile)
    }
    // Display result or error message
    viewModel.postProductResult?.let { result ->
        if (result.isSuccess) {
            Text("Product posted successfully!")
        } else {
            Text("Error: ${result.exceptionOrNull()?.message}")
        }
    }
}

@Composable
fun TextField(
    textTitle: MutableState<String>,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholderText: String
) {

    OutlinedTextField(
        value = textTitle.value,
        onValueChange = { textTitle.value = it },
        label = { Text(placeholderText) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = colorResource(id = R.color.blue),
            unfocusedIndicatorColor = colorResource(id = R.color.gray),
            focusedLabelColor = colorResource(id = R.color.darkBlue),
            unfocusedLabelColor = Color.Gray,
        ),
        placeholder = { Text(placeholderText, style = TextStyle(color = Color.Gray)) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Composable
fun ImagePicker(onImageFilePicked: (File?) -> Unit) {
    var imageUri: Uri? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            // Convert URI to File
            val imageFile = copyUriToFile(context, uri)
            onImageFilePicked(imageFile)
        } else {
            println("Error")
            onImageFilePicked(null)
        }
    }
    // to display image that user selected
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUri ?: R.drawable.ic_image)
            .crossfade(true)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.baseline_error_24)
            .build(),
        contentDescription = "Photo Picker",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable {
                photoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
    )
}
// Function to copy content from URI to a temporary file
fun copyUriToFile(context: Context, uri: Uri): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        inputStream?.use { input ->
            val tempFile = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
            tempFile
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun AddProductBtn(
    title: String,
    price: String,
    category: String,
    description: String,
    productViewModel: AddPostViewModel,
    imageFile: File?
) {
    Button(onClick = {
        if (imageFile != null) {
            val product = Product(
                id = 0,
                title = title,
                price = price,
                category = category,
                description = description,
                image = imageFile.toString()
            )
            productViewModel.postProduct(
                product.id, product.title, product.price, product.category, product.description,
                imageFile
            )
        }
    }) {
        Text(text = "ADD POST")
    }
}
