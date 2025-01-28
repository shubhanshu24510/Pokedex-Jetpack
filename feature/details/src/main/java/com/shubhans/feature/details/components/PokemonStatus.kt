package com.shubhans.feature.details.components

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme
import com.shubhans.feature.details.R
import com.shubhans.model.PokemonInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class PokemonStatus(
    val type: String,
    val label: String,
    val color: Color,
    @FloatRange(0.0, 1.0) val progress: Float
)

@Composable
internal fun PokemonInfo.toPokemonStatusInfoList(): ImmutableList<PokemonStatus> {
    return persistentListOf(
        PokemonStatus(
            type = stringResource(id = R.string.hp),
            label = getHpString(),
            color = PokedexTheme.colors.primary,
            progress = hp / PokemonInfo.MAX_HP.toFloat()
        ),
        PokemonStatus(
            type = stringResource(id = R.string.attack),
            label = getAttackString(),
            color = PokedexTheme.colors.orange,
            progress = attack / PokemonInfo.MAX_ATTACK.toFloat()
        ),
        PokemonStatus(
            type = stringResource(id = R.string.defense),
            label = getDefenseString(),
            color = PokedexTheme.colors.blue,
            progress = defense / PokemonInfo.MAX_DEFENSE.toFloat()
        ),
        PokemonStatus(
            type = stringResource(id = R.string.speed),
            label = getSpeedString(),
            color = PokedexTheme.colors.flying,
            progress = speed / PokemonInfo.MAX_SPEED.toFloat()
        ),
        PokemonStatus(
            type = stringResource(id = R.string.exp),
            label = getExpString(),
            color = PokedexTheme.colors.green,
            progress = exp / PokemonInfo.MAX_EXP.toFloat()
        ),
    )
}