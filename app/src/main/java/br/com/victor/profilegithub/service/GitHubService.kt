package br.com.victor.profilegithub.service

import br.com.victor.profilegithub.model.GitHubProfileWeb
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}")
    suspend fun findProfileBy(@Path("user") user: String ): GitHubProfileWeb
}