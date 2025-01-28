package com.shubhans.feature.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.shubhans.core.data.repository.home.FakeHomeRepository
import com.shubhans.core.navigation.PokedexScreen
import com.shubhans.core.navigation.boundsTransform
import com.shubhans.core.navigation.currentComposeNavigator
import com.shubhans.core.presentation.designsystem.components.PokemonAppBar
import com.shubhans.core.presentation.designsystem.components.PokemonCircularProgress
import com.shubhans.core.presentation.designsystem.components.pokedexSharedElement
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme
import com.shubhans.core.preview.PokemonPreviewTheme
import com.shubhans.core.preview.PreviewUtils
import com.shubhans.model.Pokemon
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.palette.rememberPaletteState
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SharedTransitionScope.PokemonHome(
    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val pokemonList by homeViewModel.pokemonList.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PokemonAppBar()
        HomeContent(
            animatedVisibilityScope = animatedVisibilityScope,
            uiState = uiState,
            pokemonList = pokemonList.toImmutableList(),
            fetchNextPokemonList = homeViewModel::fetchPokemonList
        )
    }
}

@Composable
private fun SharedTransitionScope.HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: HomeUiState,
    pokemonList: ImmutableList<Pokemon>,
    fetchNextPokemonList: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(6.dp),
        ) {
            val threadHold = 8
            itemsIndexed(
                items = pokemonList, key = { _, pokemon -> pokemon.name }) { index, pokemon ->
                if (index + threadHold >= pokemonList.size && uiState != HomeUiState.Loading) {
                    fetchNextPokemonList()
                }
                var palette by rememberPaletteState()
                val backgroundColor by palette.paletteBackgroundColor()

                PokemonCard(
                    animatedVisibilityScope = animatedVisibilityScope,
                    onPaletteLoaded = { palette = it },
                    backgroundColor = backgroundColor,
                    pokemon = pokemon
                )
            }
        }
        if (uiState == HomeUiState.Loading) {
            PokemonCircularProgress()
        }
    }
}

@Composable
private fun SharedTransitionScope.PokemonCard(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onPaletteLoaded: (Palette) -> Unit,
    backgroundColor: Color,
    pokemon: Pokemon
) {
    val composeNavigator = currentComposeNavigator
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable {
                composeNavigator.navigate(route = PokedexScreen.Details(pokemon = pokemon))
            }, shape = RoundedCornerShape(14.dp), colors = CardColors(
            contentColor = backgroundColor,
            containerColor = backgroundColor,
            disabledContentColor = backgroundColor,
            disabledContainerColor = backgroundColor,
        ), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .size(120.dp)
                .pokedexSharedElement(
                    isLocalInspectionMode = LocalInspectionMode.current,
                    state = rememberSharedContentState(key = "image-${pokemon.name}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = boundsTransform,
                ),
            imageModel = { pokemon.imageUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            component = rememberImageComponent {
                +CrossfadePlugin()
                +ShimmerPlugin(
                    Shimmer.Resonate(
                        baseColor = Color.Transparent,
                        highlightColor = Color.LightGray,
                    )
                )
                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = pokemon.imageUrl,
                        useCache = true,
                        paletteLoadedListener = { onPaletteLoaded.invoke(it) })
                }
            })
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(12.dp),
            text = pokemon.name,
            color = PokedexTheme.colors.black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexHomePreview() {
    PokedexTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true, label = "") {
                PokemonHome(
                    animatedVisibilityScope = this,
                    homeViewModel = HomeViewModel(homeRepository = FakeHomeRepository()),
                )
            }
        }
    }
}

//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun HomeContentPreview() {
//    PokemonPreviewTheme { scope ->
//        HomeContent(
//            animatedVisibilityScope = scope,
//            uiState = HomeUiState.Idle,
//            pokemonList = PreviewUtils.mockPokemonList().toImmutableList(),
//            fetchNextPokemonList = { HomeViewModel(homeRepository = FakeHomeRepository()) },
//        )
//    }
//}