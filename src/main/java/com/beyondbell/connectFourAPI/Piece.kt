package com.beyondbell.connectFourAPI

enum class Piece {
	Empty, Yellow, Red;

	fun getWinner(): WinState {
		return when (this) {
			Yellow -> WinState.Yellow
			Red -> WinState.Red
			else -> WinState.NoOne
		}
	}
}