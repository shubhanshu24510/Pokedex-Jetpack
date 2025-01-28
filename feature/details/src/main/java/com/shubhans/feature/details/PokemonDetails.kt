package com.shubhans.feature.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.shubhans.core.navigation.boundsTransform
import com.shubhans.core.navigation.currentComposeNavigator
import com.shubhans.core.presentation.designsystem.components.PokemonCircularProgress
import com.shubhans.core.presentation.designsystem.components.PokemonText
import com.shubhans.core.presentation.designsystem.components.pokedexSharedElement
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme
import com.shubhans.core.presentation.designsystem.utils.getPokemonTypeColor
import com.shubhans.feature.details.components.PokemonInfoItem
import com.shubhans.feature.details.components.PokemonStatusItem
import com.shubhans.feature.details.components.paletteBackgroundBrush
import com.shubhans.feature.details.components.toPokemonStatusInfoList
import com.shubhans.model.Pokemon
import com.shubhans.model.PokemonInfo
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.palette.rememberPaletteState
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin

@Composable
fun SharedTransitionScope.PokemonDetails(
    animatedVisibilityScope: AnimatedVisibilityScope,
    detailsViewModel: DetailsViewModel = hiltViewModel(),

    ) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
    val pokemon by detailsViewModel.pokemon.collectAsStateWithLifecycle()
    val pokemonInfo by detailsViewModel.pokemonInfo.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        var palette by rememberPaletteState()
        val backgroundBrush by palette.paletteBackgroundBrush()

        DetailsHeader(
            animatedVisibilityScope = animatedVisibilityScope,
            pokemon = pokemon,
            pokemonInfo = pokemonInfo,
            onPaletteLoaded = { palette = it },
            backgroundBrush = backgroundBrush
        )

        if (uiState == DetailsUiState.Idle && pokemonInfo != null) {
            DetailsInfo(pokemonInfo = pokemonInfo!!)
            DetailsStatus(pokemonInfo = pokemonInfo!!)
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                PokemonCircularProgress()
            }
        }
    }
}

@Composable
fun SharedTransitionScope.DetailsHeader(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon?,
    pokemonInfo: PokemonInfo?,
    onPaletteLoaded: (Palette?) -> Unit,
    backgroundBrush: Brush
) {
    val composeNavigator = currentComposeNavigator
    val shape = RoundedCornerShape(
        topStart = 0.dp, topEnd = 0.dp, bottomEnd = 64.dp, bottomStart = 64.dp
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
            .shadow(elevation = 9.dp, shape = shape)
            .background(brush = backgroundBrush, shape = shape)
    ) {
        Row(
            Modifier
                .padding(12.dp)
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = com.shubhans.core.presentation.designsystem.R.drawable.ic_arrow),
                contentDescription = "Back",
                tint = PokedexTheme.colors.absoluteWhite,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        composeNavigator.navigateUp()
                    })
            Text(
                text = pokemon?.name.orEmpty(),
                modifier = Modifier.padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold,
                color = PokedexTheme.colors.absoluteWhite,
                fontSize = 18.sp
            )
        }
        PokemonText(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 12.dp)
                .statusBarsPadding(),
            text = pokemonInfo?.getIdString().orEmpty(),
            previewText = "#001",
            color = PokedexTheme.colors.absoluteWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        GlideImage(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .size(190.dp)
                .pokedexSharedElement(
                    isLocalInspectionMode = LocalInspectionMode.current,
                    animatedVisibilityScope = animatedVisibilityScope,
                    state = rememberSharedContentState(key = "image-${pokemon?.name}"),
                    boundsTransform = boundsTransform
                ),
            imageModel = { pokemon?.imageUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            component = rememberImageComponent {
                +CrossfadePlugin()
                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = pokemon?.imageUrl,
                        useCache = true,
                        paletteLoadedListener = { onPaletteLoaded.invoke(it) },
                    )
                }
            },
        )
    }

    PokemonText(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .pokedexSharedElement(
                isLocalInspectionMode = LocalInspectionMode.current,
                state = rememberSharedContentState(key = "name-${pokemon?.name}"),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = boundsTransform,
            ),
        text = pokemon?.name.orEmpty(),
        previewText = "subhumans2451",
        color = PokedexTheme.colors.black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 36.sp,
    )
}

@Composable
private fun DetailsInfo(
    pokemonInfo: PokemonInfo
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally)
    ) {
        pokemonInfo.types.forEach { typeInfo ->
            Text(
                modifier = Modifier
                    .background(
                        color = getPokemonTypeColor(typeInfo.type.name),
                        shape = RoundedCornerShape(64.dp)
                    )
                    .padding(horizontal = 40.dp, vertical = 4.dp),
                text = typeInfo.type.name,
                color = PokedexTheme.colors.absoluteWhite,
                fontSize = 16.sp,
                maxLines = 1,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PokemonInfoItem(
            title = pokemonInfo.getWeightString(), content = stringResource(R.string.weight)
        )
        PokemonInfoItem(
            title = pokemonInfo.getHeightString(), content = stringResource(R.string.height)
        )
    }
}

@Composable
private fun DetailsStatus(
    pokemonInfo: PokemonInfo
) {
    Text(
        text = stringResource(R.string.base_stats),
        color = PokedexTheme.colors.black,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 16.dp)
    )
    Column {
        pokemonInfo.toPokemonStatusInfoList().forEach { pokemonStatus ->
            PokemonStatusItem(
                pokemonStatus = pokemonStatus, modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}

