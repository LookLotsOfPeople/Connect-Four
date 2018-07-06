package com.beyondbell.connectFour;

import javafx.geometry.NodeOrientation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class WinChecker {
	@Nullable
	static WinState getWinState(final Piece[][] board) {
		Piece gameWon = null;

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
		for (int i = 0; i < board.length; i++) {
			for (int h = 0; h < board[0].length - 3; h++) {
				if (board[i][h] != Piece.Empty
						&& board[i][h] == board[i][h + 1]
						&& board[i][h] == board[i][h + 2]
						&& board[i][h] == board[i][h + 3]) {
					return getWinnerFromPiece(board[i][h]);
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState verticalWin(@NotNull final Piece[][] board) {
		for (int i = 0; i < board.length - 3; i++) {
			for (int h = 0; h < board[0].length; h++) {
				if (board[i][h] != Piece.Empty
						&& board[i][h] == board[i + 1][h]
						&& board[i][h] == board[i + 2][h]
						&& board[i][h] == board[i + 3][h]) {
					return getWinnerFromPiece(board[i][h]);
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState leftRightDiagonalWin(@NotNull final Piece[][] board) {
		for (int i = 0; i < board.length - 3; i++) {
			for (int h = 0; h < board[0].length - 3; h++) {
				if (board[i][h] != Piece.Empty
						&& board[i][h] == board[i + 1][h + 1]
						&& board[i][h] == board[i + 2][h + 2]
						&& board[i][h] == board[i + 3][h + 3]) {
					return getWinnerFromPiece(board[i][h]);
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState rightLeftDiagonalWin(@NotNull final Piece[][] board) {
		for (int i = 0; i < board.length - 3; i++) {
			for (int h = board[0].length - 1; h > 3; h--) {
				if (board[i][h] != Piece.Empty
						&& board[i][h] == board[i + 1][h - 1]
						&& board[i][h] == board[i + 2][h - 2]
						&& board[i][h] == board[i + 3][h - 3]) {
					return getWinnerFromPiece(board[i][h]);
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
