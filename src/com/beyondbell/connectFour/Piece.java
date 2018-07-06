package com.beyondbell.connectFour;

import java.awt.*;

public enum Piece {
	Empty(Color.WHITE), Yellow(Color.YELLOW), Red(Color.RED);

	private final Color color;

	Piece(final Color color) {
		this.color = color;
	}

	public final Color getColor() {
		return color;
	}
}
