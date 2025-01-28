package com.shubhans.feature.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubhans.core.presentation.designsystem.components.PokemonProgressbar
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun PokemonStatusItem(
    modifier: Modifier = Modifier, pokemonStatus: PokemonStatus
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = pokemonStatus.type,
            modifier = Modifier
                .padding(start = 32.dp)
                .widthIn(min = 20.dp),
            color = PokedexTheme.colors.white70,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        PokemonProgressbar(
            label = pokemonStatus.label,
            color = pokemonStatus.color,
            progress = pokemonStatus.progress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}