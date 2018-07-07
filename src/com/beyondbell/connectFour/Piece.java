package com.beyondbell.connectFour;

import org.jetbrains.annotations.Contract;

import java.awt.Color;


public enum Piece {
	Empty(Color.WHITE), Yellow(Color.YELLOW), Red(Color.RED);

	private final Color color;

	Piece(final Color color) {
		this.color = color;
	}

	@Contract(pure = true)
	final Color getColor() {
		return color;
	}

	@Contract(pure = true)
	final WinState getWinner() {
		switch (this) {
			case Yellow:
				return WinState.Yellow;
			case Red:
				return WinState.Red;
			default:
				return WinState.NoOne;
		}
	}
}
