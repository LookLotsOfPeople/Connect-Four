package com.beyondbell.connectFourAPI

import java.awt.Color

enum class Piece(val color: Color) {
	Empty(Color.WHITE), Yellow(Color.YELLOW), Red(Color.RED);

	fun getWinner(): WinState {
		return when (this) {
			Yellow -> WinState.Yellow
			Red -> WinState.Red
			else -> WinState.NoOne
		}
	}
}