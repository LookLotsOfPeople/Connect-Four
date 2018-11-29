package com.beyondbell.connectFourAPI

import java.awt.Color

enum class Piece(val color: Color) {
	Empty(Color.WHITE), Yellow(Color.WHITE), Red(Color.WHITE);

	fun getWinner(): WinState {
		return when (this) {
			Yellow -> WinState.Yellow
			Red -> WinState.Red
			else -> WinState.NoOne
		}
	}
}