package com.example.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.crud.ui.theme.CrudTheme
import com.example.crud.ui.theme.Screen.StudentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrudTheme { // Aplica el tema de tu aplicación
                Surface(color = MaterialTheme.colorScheme.background) {
                    StudentScreen() // Llama a la pantalla de estudiantes
                }
            }
        }
    }
}
