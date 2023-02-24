package br.com.victor.profilegithub.service

import br.com.victor.profilegithub.model.GitHubProfileWeb
import br.com.victor.profilegithub.model.GitHubRepositoryWeb
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}")
    suspend fun findProfileBy(@Path("user") user: String ): GitHubProfileWeb

    @GET("/users/{user}/repos")
    suspend fun findRepositoriesBy(@Path("user") user: String): List<GitHubRepositoryWeb>
}