package com.example.zadanie2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zadanie2.ui.theme.Zadanie2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Zadanie2Theme {
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Pierwsza liczba") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Druga liczba") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.weight(1f).padding(4.dp),
                shape = RectangleShape,

                onClick = {
                    val res = number1.toDoubleOrNull()?.plus(number2.toDoubleOrNull() ?: 0.0)
                    result = res?.toString() ?: "Błąd"
                }


            ) {
                Text("+")
            }
            Button(
                modifier = Modifier.weight(1f).padding(4.dp),
                shape = RectangleShape,
                onClick = {
                    val res = number1.toDoubleOrNull()?.minus(number2.toDoubleOrNull() ?: 0.0)
                    result = res?.toString() ?: "Błąd"
                }
            ) {
                Text("-")
            }
            Button(
                modifier = Modifier.weight(1f).padding(4.dp),
                shape = RectangleShape,
                onClick = {
                    val num2 = number2.toDoubleOrNull()
                    val res = if (num2 != null && num2 != 0.0)
                        number1.toDoubleOrNull()?.div(num2)
                    else null
                    result = res?.toString() ?: "Błąd"
                }
            ) {
                Text("/")
            }
            Button(
                modifier = Modifier.weight(1f).padding(4.dp),
                shape = RectangleShape,
                onClick = {
                    val res = number1.toDoubleOrNull()?.times(number2.toDoubleOrNull() ?: 0.0)
                    result = res?.toString() ?: "Błąd"
                }
            ) {
                Text("X")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(

            text = "Wynik: $result",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

