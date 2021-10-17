package id.itborneo.githubuser.data.networks

import id.itborneo.githubuser.data.model.UserDetailModel
import id.itborneo.githubuser.data.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val GITHUB_KEY = "49c91b8a6df00de10f74e55a9f6ab944e2b46291"

interface ApiService {

    @GET("users")
    @Headers("Authorization: token $GITHUB_KEY")
    suspend fun users(): List<UserModel>

    @GET("users/{username}")pre
    suspend fun detailUser(
        @Path("username") username: String
    ): UserDetailModel

}

