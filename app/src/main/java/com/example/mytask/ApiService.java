package com.example.mytask;

import com.example.mytask.LoginRequest;
import com.example.mytask.LoginResponse;
import com.example.mytask.Message;
import com.example.mytask.MessageRequest;

import java.util.List;

import kotlin.Unit;
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("api/login")
    suspend fun login(@Body loginRequest:LoginRequest): Response<LoginResponse>

    @GET("api/messages")
    suspend fun getMessages(@Header("X-Branch-Auth-Token") authToken:String): Response<List<Message>>

    @POST("api/messages")
    suspend fun sendMessage(
            @Header("X-Branch-Auth-Token") authToken: String,
            @Body messageRequest:MessageRequest
    ): Response<Message>

    @POST("api/reset")
    suspend fun reset(@Header("X-Branch-Auth-Token") authToken: String): Response<Unit>
}
