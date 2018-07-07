package com.beyondbell.connectFour;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class WinChecker {
	@Nullable
	static WinState getWinState(final Board board) {
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
		if (board.isFull()) {
			return null;
		} else {
			return WinState.NoOne;
		}
	}

	@Contract(pure = true)
	private static WinState horizontalWin(@NotNull final Board board) {
		for (byte row = 0; row < board.getRowCount(); row++) {
			for (byte column = 0; column < board.getColumnCount(); column++) {
				if (board.getPiece(row, column) != Piece.Empty
						&& board.getPiece(row, column) == board.getPiece(row, (byte) (column + 3))
						&& board.getPiece(row, column) == board.getPiece(row, (byte) (column + 3))
						&& board.getPiece(row, column) == board.getPiece(row, (byte) (column + 3))) {
					return board.getPiece(row, column).getWinner();
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState verticalWin(@NotNull final Board board) {
		for (byte row = 0; row < board.getRowCount() - 3; row++) {
			for (byte column = 0; column < board.getColumnCount(); column++) {
				if (board.getPiece(row, column) != Piece.Empty
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 1), column)
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 2), column)
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 3), column)) {
					return board.getPiece(row, column).getWinner();
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState leftRightDiagonalWin(@NotNull final Board board) {
		for (byte row = 0; row < board.getRowCount() - 3; row++) {
			for (byte column = 0; column < board.getColumnCount() - 3; column++) {
				if (board.getPiece(row, column) != Piece.Empty
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 1), (byte) (column + 1))
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 2), (byte) (column + 2))
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 3), (byte) (column + 3))) {
					return board.getPiece(row, column).getWinner();
				}
			}
		}
		return WinState.NoOne;
	}

	@Contract(pure = true)
	private static WinState rightLeftDiagonalWin(@NotNull final Board board) {
		for (byte row = 0; row < board.getRowCount() - 3; row++) {
			for (byte column = (byte) (board.getColumnCount() - 1); column > 3; column--) {
				if (board.getPiece(row, column) != Piece.Empty
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 1), (byte) (column - 1))
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 2), (byte) (column - 2))
						&& board.getPiece(row, column) == board.getPiece((byte) (row + 3), (byte) (column - 3))) {
					return board.getPiece(row, column).getWinner();
				}
			}
		}
		return WinState.NoOne;
	}
}
