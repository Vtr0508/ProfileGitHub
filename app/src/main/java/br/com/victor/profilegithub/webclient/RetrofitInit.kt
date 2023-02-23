package br.com.victor.profilegithub.webclient


import br.com.victor.profilegithub.service.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()



    val gitHubService get() = retrofit.create(GitHubService::class.java)

}