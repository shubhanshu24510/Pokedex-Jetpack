package com.shubhans.feature.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubhans.core.presentation.designsystem.components.PokemonText
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun PokemonInfoItem(
    title: String?,
    content: String?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonText(
            text = title.orEmpty(),
            previewText = "Weight",
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            color = PokedexTheme.colors.black,
            fontSize = 21.sp,
        )
        PokemonText(
            text = content.orEmpty(),
            previewText = "height",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = PokedexTheme.colors.white56,
        )
    }
}