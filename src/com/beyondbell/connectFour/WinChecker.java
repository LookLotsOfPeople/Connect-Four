package com.beyondbell.connectFour;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class WinChecker {
	@Nullable
	static WinState getWinState(final Piece[][] board) {
		//Checks Horizontal Wins
		final WinState horizontal = horizontalWin(board);
		if (horizontal != WinState.NoOne) {
			return horizontal;
		}
		//Checks Vertical Wins
		final WinState vertical = verticalWin(board);
		if (vertical != WinState.NoOne) {
			return vertical;
		}
		//Checks Diagonal \ Wins
		final WinState leftRightDiagonal = leftRightDiagonalWin(board);
		if (leftRightDiagonal != WinState.NoOne) {
			return leftRightDiagonal;
		}
		//Checks Diagonal / Win
		final WinState rightLeftDiagonal = rightLeftDiagonalWin(board);
		if (rightLeftDiagonal != WinState.NoOne) {
			return rightLeftDiagonal;
		}

		// Checks for Full Board
		if (isFull(board)) {
			return null;
		} else {
			return WinState.NoOne;
		}
	}

	@Contract(pure = true)
	private static WinState horizontalWin(@NotNull final Piece[][] board) {
		for (final Piece[] row : board) {
			for (int column = 0; column < board[0].length - 3; column++) {
				if (row[column] != Piece.Empty
						&& row[column] == row[column + 1]
						&& row[column] == row[column + 2]
						&& row[column] == row[column + 3]) {
					return getWinnerFromPiece(row[column]);
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState verticalWin(@NotNull final Piece[][] board) {
		for (int row = 0; row < board.length - 3; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] != Piece.Empty
						&& board[row][column] == board[row + 1][column]
						&& board[row][column] == board[row + 2][column]
						&& board[row][column] == board[row + 3][column]) {
					return getWinnerFromPiece(board[row][column]);
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState leftRightDiagonalWin(@NotNull final Piece[][] board) {
		for (int row = 0; row < board.length - 3; row++) {
			for (int column = 0; column < board[0].length - 3; column++) {
				if (board[row][column] != Piece.Empty
						&& board[row][column] == board[row + 1][column + 1]
						&& board[row][column] == board[row + 2][column + 2]
						&& board[row][column] == board[row + 3][column + 3]) {
					return getWinnerFromPiece(board[row][column]);
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState rightLeftDiagonalWin(@NotNull final Piece[][] board) {
		for (int row = 0; row < board.length - 3; row++) {
			for (int column = board[0].length - 1; column > 3; column--) {
				if (board[row][column] != Piece.Empty
						&& board[row][column] == board[row + 1][column - 1]
						&& board[row][column] == board[row + 2][column - 2]
						&& board[row][column] == board[row + 3][column - 3]) {
					return getWinnerFromPiece(board[row][column]);
				}
			}
		}
		return WinState.NoOne;
	}

	private static WinState getWinnerFromPiece(final Piece piece) {
		switch (piece) {
			case Yellow:
				return WinState.Yellow;
			case Red:
				return WinState.Red;
			default:
				return WinState.NoOne;
		}
	}

	/**
	 * Checks if the board is full. Should be run after checking for a specific win since it returns either full or empty.
	 *
	 * @param board Main Game Board
	 *
	 * @return Whether or Not the Board is Full
	 */
	@Contract(pure = true)
	private static boolean isFull(@NotNull final Piece[][] board) { // Just Checks Top
		for (byte column = 0; column < board[0].length; column++) { // If Top is Full, Entire Board Must be Full
			if (board[0][column] == Piece.Empty) {
				return false;
			}
		}
		return true;
	}
}
