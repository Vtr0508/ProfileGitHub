package br.com.victor.profilegithub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import br.com.victor.profilegithub.model.ProfileUiState
import br.com.victor.profilegithub.model.toProfileUiState
import br.com.victor.profilegithub.ui.theme.ProfileGitHubTheme
import br.com.victor.profilegithub.webclient.GitHubWebClient
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    user: String,
    webClient: GitHubWebClient = GitHubWebClient()
) {
    val foundUser by webClient.findProfileBy(user)
        .collectAsState(initial = null)

    foundUser?.let { userProfile ->
        val state = userProfile.toProfileUiState()
        Profile(state = state)
    }
}

@Composable
fun Profile(state: ProfileUiState) {
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
                state.image,
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
                text = state.name,
                fontSize = 32.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = state.user,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )


        }

        Text(
            text = state.bio, Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

    }


}

@Preview(showSystemUi = true)
@Composable
fun ProfilePreview() {
    ProfileGitHubTheme {
        Surface {
            Profile(state = ProfileUiState(
                user = "vtr0508",
                image = "https://avatars.githubusercontent.com/u/122477531?v=4",
                name = "Victor Viana",
                bio = "Android developer"
            ))
        }
    }
}