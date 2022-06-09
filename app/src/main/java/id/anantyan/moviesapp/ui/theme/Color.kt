package id.anantyan.moviesapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val HeartRed = Color(0xFFFF4444)

fun Colors.topAppBarContentColor(): Color {
    return if (isLight) Color.White else Color.LightGray
}

fun Colors.topAppBarBackgroundColor(): Color {
    return if (isLight) Purple500 else Color.Black
}