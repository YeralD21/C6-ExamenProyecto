package com.example.crud.ui.theme.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.Model.Student
import com.example.crud.ui.theme.EstudianteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScreen(viewModel: EstudianteViewModel = viewModel()) {
    val estudiantes by viewModel.estudiantes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Estado para los campos de texto
    var nombre by remember { mutableStateOf("") }
    var grado by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }
    var editingStudentId by remember { mutableStateOf(0) }

    // Estado para mostrar mensajes de error
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchStudents()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Estudiantes") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when {
                isLoading -> {
                    // Indicador de carga
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                errorMessage != null -> {
                    // Mostrar mensaje de error
                    Text(
                        text = "Error: $errorMessage",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Lista de estudiantes
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            items(estudiantes) { student ->
                                StudentItem(
                                    student = student,
                                    onDelete = {
                                        viewModel.deleteStudent(it)
                                    },
                                    onEdit = {
                                        // Cargar datos del estudiante en el formulario
                                        nombre = student.nombre
                                        grado = student.grado
                                        editingStudentId = student.id
                                        isEditing = true
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Formulario para agregar o editar estudiantes
                        Text(
                            text = if (isEditing) "Editar Estudiante" else "Agregar Nuevo Estudiante",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = grado,
                            onValueChange = { grado = it },
                            label = { Text("Grado") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )

                        Button(
                            onClick = {
                                if (nombre.isNotBlank() && grado.isNotBlank()) {
                                    if (isEditing) {
                                        viewModel.updateStudent(
                                            Student(editingStudentId, nombre, grado)
                                        )
                                        isEditing = false
                                        editingStudentId = 0
                                    } else {
                                        viewModel.saveStudent(Student(0, nombre, grado))
                                    }
                                    nombre = ""
                                    grado = ""
                                } else {
                                    showError = true
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (isEditing) "Actualizar Estudiante" else "Guardar Estudiante")
                        }

                        if (showError) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Por favor, completa todos los campos.",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, onDelete: (Int) -> Unit, onEdit: (Student) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = student.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "Grado: ${student.grado}", style = MaterialTheme.typography.bodyMedium)
            }
            Row {
                IconButton(onClick = { onEdit(student) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar Estudiante",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { onDelete(student.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar Estudiante",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

