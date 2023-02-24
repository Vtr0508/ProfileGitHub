package br.com.victor.profilegithub.model

class GitHubRepositoryWeb(
    val name: String = "",
    val description: String? = null
)


fun GitHubRepositoryWeb.toGitHubRepository() = GitHubRepository(
    name = name,
    description = description ?: ""


)





