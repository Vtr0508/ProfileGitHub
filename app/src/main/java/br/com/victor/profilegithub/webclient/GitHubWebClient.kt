package br.com.victor.profilegithub.webclient

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.victor.profilegithub.model.ProfileUiState
import br.com.victor.profilegithub.model.toGitHubRepository
import br.com.victor.profilegithub.model.toProfileUiState
import br.com.victor.profilegithub.service.GitHubService

class GitHubWebClient(private val service: GitHubService = RetrofitInit().gitHubService) {


    var uiState by mutableStateOf(ProfileUiState())
        private set


    suspend fun findProfileBy(user:String){
        try {
            val profile = service.findProfileBy(user).toProfileUiState()
            val repositories = service.findRepositoriesBy(user).map { it.toGitHubRepository()}
            uiState = profile.copy(repository = repositories)
        }catch (e:Exception){
            Log.e("GitHubWebCLient", "findProfileBy: falha ao buscar usuario", e)

        }
    }


}