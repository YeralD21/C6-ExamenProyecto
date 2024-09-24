package com.example.crud.Network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.DELETE
import com.example.crud.Model.Student

interface ApiService {
    @GET("/estudiante")
    suspend fun getStudents(): List<Student>

    @POST("/estudiante")
    suspend fun saveStudent(@Body student: Student): Student

    @GET("/estudiante/{id}")
    suspend fun getStudentById(@Path("id") id: Int): Student

    @PUT("/estudiante/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: Student): Student

    @DELETE("/estudiante/{id}")
    suspend fun deleteStudent(@Path("id") id: Int)
}
