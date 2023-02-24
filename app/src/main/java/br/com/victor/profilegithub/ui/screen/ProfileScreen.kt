package br.com.victor.profilegithub.ui.screen

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.profilegithub.model.GitHubRepository
import br.com.victor.profilegithub.model.ProfileUiState
import br.com.victor.profilegithub.ui.theme.ProfileGitHubTheme
import br.com.victor.profilegithub.webclient.GitHubWebClient
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    user: String,
    webClient: GitHubWebClient = GitHubWebClient()
) {
    val uiState = webClient.uiState
    LaunchedEffect(null) {
        webClient.findProfileBy(user)
    }
    Profile(uiState = uiState)

}

@Composable
fun Profile(uiState: ProfileUiState) {
    LazyColumn {
        item {
            ProfileHeader(uiState = uiState)
        }
        item {
            if (uiState.repository.isNotEmpty()) {
                Text(
                    text = "Repositórios",
                    Modifier.padding(8.dp),
                    fontSize = 24.sp
                )
            }
        }
        items(uiState.repository){repo ->
            RepositoryItem(repo = repo)

        }
    }

}

@Composable
fun ProfileHeader(uiState: ProfileUiState) {
    Column {
        val boxHeight = remember {
            150.dp
        }
        val imageHeight = remember {
            boxHeight
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.DarkGray, shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .height(boxHeight)
        ) {
            AsyncImage(
                uiState.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(y = boxHeight / 2)
                    .size(imageHeight)
                    .align(Alignment.Center)
                    .clip(CircleShape)

            )

        }
        Spacer(modifier = Modifier.height(imageHeight / 2))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = uiState.name,
                fontSize = 32.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.user,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )


        }

        Text(
            text = uiState.bio, Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

    }


}

@Composable
fun RepositoryItem(repo: GitHubRepository) {
    Card(
        Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = repo.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(8.dp),
                fontSize = 24.sp,
                color = Color.White
            )

            if (repo.description.isNotBlank()) {
                Text(
                    text = repo.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun ProfilePreview() {
    ProfileGitHubTheme {
        Surface {
            Profile(
                uiState = ProfileUiState(
                    user = "vtr0508",
                    image = "https://avatars.githubusercontent.com/u/122477531?v=4",
                    name = "Victor Viana",
                    bio = "Android developer"
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    ProfileGitHubTheme {
        Surface {
            RepositoryItem(
                repo = GitHubRepository(
                    name = "Victor Viana",
                    description = "Minha imformações pessoais"
                )
            )
        }
    }
}