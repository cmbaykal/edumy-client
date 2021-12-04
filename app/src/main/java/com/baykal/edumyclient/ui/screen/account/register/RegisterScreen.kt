package com.baykal.edumyclient.ui.screen.account.register

import android.app.DatePickerDialog
import android.util.Log
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.baykal.edumyclient.ui.component.*
import com.baykal.edumyclient.ui.navigation.Screen
import java.time.LocalDate
import java.util.*

@Composable
fun RegisterScreen(navController: NavController? = null) {

    var name by remember { mutableStateOf(InputState()) }
    var surname by remember { mutableStateOf(InputState()) }
    var mail by remember { mutableStateOf(InputState()) }
    var pass by remember { mutableStateOf(InputState()) }
    var birth by remember { mutableStateOf(InputState()) }
    var passConfirm by remember { mutableStateOf(InputState()) }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ETextField(label = "Name", onChange = { name = it })
        ETextField(label = "Surname", onChange = { surname = it })
        ETextField(label = "E-Mail", onChange = { mail = it })
        EDateField(label = "Birth", onChange = { birth = it })
        ETextField(label = "Password", onChange = { pass = it }, passwordToggle = true)
        ETextField(label = "Confirm Password", onChange = { passConfirm = it }, passwordToggle = true)
        EButton(text = "Register"){
            navController?.navigateUp()
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}