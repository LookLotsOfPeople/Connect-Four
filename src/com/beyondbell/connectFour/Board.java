package com.beyondbell.connectFour;

import org.jetbrains.annotations.Contract;

final class Board {
	private final Piece[][] board = {
			{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
			{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
			{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
			{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
			{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
			{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty}
	};

	@Contract(pure = true)
	final byte getRowCount() {
		return (byte) board.length;
	}

	@Contract(pure = true)
	final byte getColumnCount() {
		return (byte) board[0].length;
	}

	@Contract(pure = true)
	final Piece getPiece(final byte row, final byte column) {
		return board[row][column];
	}

	final void setPiece(final byte row, final byte column, final Piece piece) {
		board[row][column] = piece;
	}

	/**
	 * Checks if the board is full. Should be run after checking for a specific win since it returns either full or empty.
	 *
	 * @return Whether or Not the Board is Full
	 */
	@Contract(pure = true)
	final boolean isFull() {
		for (byte column = 0; column < board[0].length; column++) { // If Top is Full, Entire Board Must be Full
			if (board[0][column] == Piece.Empty) {
				return false;
			}
		}
		return true;
	}
}
