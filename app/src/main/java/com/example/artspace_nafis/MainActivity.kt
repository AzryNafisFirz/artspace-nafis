package com.example.artspace_nafis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace_nafis.ui.theme.ArtspacenafisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtspacenafisTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    )
                }
            }
        }
    }
}

data class Artwork(
    @param:DrawableRes val imageResourceId: Int,
    @param:StringRes val titleResourceId: Int,
    @param:StringRes val artistResourceId: Int,
    @param:StringRes val yearResourceId: Int,
    @param:StringRes val descriptionResourceId: Int,
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    var currentArtworkIndex by remember { mutableIntStateOf(0) }

    val artworkList = listOf(
        Artwork(
            imageResourceId = R.drawable.artwork_86_1,
            titleResourceId = R.string.artwork_1_title,
            artistResourceId = R.string.artwork_1_artist,
            yearResourceId = R.string.artwork_1_year,
            descriptionResourceId = R.string.artwork_1_desc,
        ),
        Artwork(
            imageResourceId = R.drawable.artwork_86_2,
            titleResourceId = R.string.artwork_2_title,
            artistResourceId = R.string.artwork_2_artist,
            yearResourceId = R.string.artwork_2_year,
            descriptionResourceId = R.string.artwork_2_desc,
        ),
        Artwork(
            imageResourceId = R.drawable.artwork_86_3,
            titleResourceId = R.string.artwork_3_title,
            artistResourceId = R.string.artwork_3_artist,
            yearResourceId = R.string.artwork_3_year,
            descriptionResourceId = R.string.artwork_3_desc,
        ),
        Artwork(
            imageResourceId = R.drawable.artwork_86_4,
            titleResourceId = R.string.artwork_4_title,
            artistResourceId = R.string.artwork_4_artist,
            yearResourceId = R.string.artwork_4_year,
            descriptionResourceId = R.string.artwork_4_desc,
        ),
        Artwork(
            imageResourceId = R.drawable.artwork_86_5,
            titleResourceId = R.string.artwork_5_title,
            artistResourceId = R.string.artwork_5_artist,
            yearResourceId = R.string.artwork_5_year,
            descriptionResourceId = R.string.artwork_5_desc,
        ),
    )

    val currentArtwork = artworkList[currentArtworkIndex]

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ArtworkWall(
            imageResourceId = currentArtwork.imageResourceId,
            contentDescriptionId = currentArtwork.titleResourceId,
        )

        Spacer(modifier = Modifier.height(24.dp))

        ArtworkDescriptor(
            titleResourceId = currentArtwork.titleResourceId,
            artistResourceId = currentArtwork.artistResourceId,
            yearResourceId = currentArtwork.yearResourceId,
            descriptionResourceId = currentArtwork.descriptionResourceId,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        DisplayController(
            onPreviousClick = {
                currentArtworkIndex = if (currentArtworkIndex > 0) {
                    currentArtworkIndex - 1
                } else {
                    artworkList.size - 1
                }
            },
            onNextClick = {
                currentArtworkIndex = (currentArtworkIndex + 1) % artworkList.size
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes imageResourceId: Int,
    @StringRes contentDescriptionId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp),
        border = BorderStroke(3.dp, Color.White),
        color = Color.White,
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(440.dp),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun ArtworkDescriptor(
    @StringRes titleResourceId: Int,
    @StringRes artistResourceId: Int,
    @StringRes yearResourceId: Int,
    @StringRes descriptionResourceId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = stringResource(id = titleResourceId),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(id = artistResourceId),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Text(
                text = "Tahun: ${stringResource(id = yearResourceId)}",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.75f),
                modifier = Modifier.padding(top = 2.dp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = descriptionResourceId),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
            )
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtspacenafisTheme {
        ArtSpaceScreen(modifier = Modifier.fillMaxSize())
    }
}