package br.com.victor.profilegithub.webclient

import android.util.Log
import br.com.victor.profilegithub.service.GitHubService
import kotlinx.coroutines.flow.flow

class GitHubWebClient (private val service: GitHubService = RetrofitInit().gitHubService){

    fun findProfileBy(user: String) = flow {
        try {
            emit(service.findProfileBy(user))
        } catch (e: Exception){
            Log.e("GitHubService", "findProfileBy: falha ao buscar usuario",e )
        }

    }


}