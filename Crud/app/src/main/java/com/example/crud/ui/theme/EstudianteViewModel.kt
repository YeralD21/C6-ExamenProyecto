package com.example.crud.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crud.Model.Student
import com.example.crud.Network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EstudianteViewModel : ViewModel() {

    private val _estudiantes = MutableStateFlow<List<Student>>(emptyList())
    val estudiantes: StateFlow<List<Student>> = _estudiantes

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedStudents = RetrofitClient.apiService.getStudents()
                _estudiantes.value = fetchedStudents
                _errorMessage.value = null
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.localizedMessage ?: "Error desconocido"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val savedStudent = RetrofitClient.apiService.saveStudent(student)
                _estudiantes.value = _estudiantes.value + savedStudent
                _errorMessage.value = null
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.localizedMessage ?: "Error al guardar el estudiante"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val updatedStudent = RetrofitClient.apiService.updateStudent(student.id, student)
                _estudiantes.value = _estudiantes.value.map {
                    if (it.id == student.id) updatedStudent else it
                }
                _errorMessage.value = null
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.localizedMessage ?: "Error al actualizar el estudiante"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteStudent(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                RetrofitClient.apiService.deleteStudent(id)
                _estudiantes.value = _estudiantes.value.filter { it.id != id }
                _errorMessage.value = null
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.localizedMessage ?: "Error al eliminar el estudiante"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

