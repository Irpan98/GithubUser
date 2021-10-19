package id.itborneo.githubuser.data.networks

import id.itborneo.githubuser.data.model.UserDetailModel
import id.itborneo.githubuser.data.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun users(): List<UserModel>

    @GET("users/{username}")
    suspend fun detailUser(@Path("username") username: String): UserDetailModel
}

