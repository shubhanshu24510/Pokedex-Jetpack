package com.shubhans.core.presentation.designsystem.components

import androidx.annotation.FloatRange
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubhans.core.presentation.designsystem.theme.PokedexTheme
import com.shubhans.core.presentation.designsystem.utils.pxToDp

@Composable
fun PokemonProgressbar(
    label: String,
    color: Color,
    @FloatRange(0.0, 1.0) progress: Float,
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp.value
    val isLocalInspectionMode = LocalInspectionMode.current
    var progressWidth by remember {
        mutableFloatStateOf(
            if (isLocalInspectionMode) {
                screenWidth
            } else {
                0f
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .onSizeChanged {
                progressWidth = it.width * progress
            }
            .background(
                color = PokedexTheme.colors.absoluteWhite,
                shape = RoundedCornerShape(64.dp)
            )
            .clip(RoundedCornerShape(64.dp))
    ) {
        var textWidth by remember { mutableIntStateOf(0) }
        val threshold = 16
        val isInner by remember(
            progressWidth,
            textWidth,
        ) { mutableStateOf(progressWidth > (textWidth + threshold * 2)) }

        val animation: Float by animateFloatAsState(
            targetValue = if (progressWidth == 0f) 0f else 1f,
            // Configure the animation duration and easing.
            animationSpec = tween(durationMillis = 950, easing = LinearOutSlowInEasing),
            label = "",
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(
                    progressWidth.toInt()
                        .pxToDp() * animation
                )
                .height(18.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(64.dp)
                )
        ) {
            if (isInner) {
                Text(
                    text = label,
                    modifier = Modifier
                        .onSizeChanged {
                            textWidth = it.width
                        }
                        .align(Alignment.Center)
                        .padding(end = (threshold * 2).pxToDp()),
                    color = PokedexTheme.colors.absoluteWhite,
                    fontSize = 12.sp,
                )
            }
            if (!isInner) {
                Text(
                    text = label,
                    modifier = Modifier
                        .onSizeChanged {
                            textWidth = it.width
                        }
                        .align(Alignment.Center)
                        .padding(
                            start = progressWidth
                                .toInt()
                                .pxToDp() + threshold.pxToDp(),
                        ),
                    color = PokedexTheme.colors.absoluteBlack,
                    fontSize = 12.sp,
                )
            }
        }
    }
}