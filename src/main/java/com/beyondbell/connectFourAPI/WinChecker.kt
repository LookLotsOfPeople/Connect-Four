package com.beyondbell.connectFourAPI

fun getWinState(board: Board): WinState? {
	//Checks Horizontal Wins
	val horizontal = horizontalWin(board)
	if (horizontal !== WinState.NoOne) {
		return horizontal
	}
	//Checks Vertical Wins
	val vertical = verticalWin(board)
	if (vertical !== WinState.NoOne) {
		return vertical
	}
	//Checks Diagonal \ Wins
	val leftRightDiagonal = leftRightDiagonalWin(board)
	if (leftRightDiagonal !== WinState.NoOne) {
		return leftRightDiagonal
	}
	//Checks Diagonal / Win
	val rightLeftDiagonal = rightLeftDiagonalWin(board)
	if (rightLeftDiagonal !== WinState.NoOne) {
		return rightLeftDiagonal
	}

	// Checks for Full com.beyondbell.connectFourAPI.Board
	return if (board.isFull()) {
		WinState.NoOne
	} else {
		WinState.Tie
	}
}

private fun horizontalWin(board: Board): WinState {
	for (row in 0 until board.getRowCount()) {
		for (column in 0 until board.getColumnCount()) {
			if (board.getPiece(row, column) !== Piece.Empty
					&& board.getPiece(row, column) === board.getPiece(row, (column + 3).toByte().toInt())
					&& board.getPiece(row, column) === board.getPiece(row, (column + 3).toByte().toInt())
					&& board.getPiece(row, column) === board.getPiece(row, (column + 3).toByte().toInt())) {
				return board.getPiece(row, column).getWinner()
			}
		}
	}
	return WinState.NoOne
}

private fun verticalWin(board: Board): WinState {
	for (row in 0 until board.getRowCount() - 3) {
		for (column in 0 until board.getColumnCount()) {
			if (board.getPiece(row, column) !== Piece.Empty
					&& board.getPiece(row, column) === board.getPiece((row + 1).toByte().toInt(), column)
					&& board.getPiece(row, column) === board.getPiece((row + 2).toByte().toInt(), column)
					&& board.getPiece(row, column) === board.getPiece((row + 3).toByte().toInt(), column)) {
				return board.getPiece(row, column).getWinner()
			}
		}
	}
	return WinState.NoOne
}

private fun leftRightDiagonalWin(board: Board): WinState {
	for (row in 0 until board.getRowCount() - 3) {
		for (column in 0 until board.getColumnCount() - 3) {
			if (board.getPiece(row, column) !== Piece.Empty
					&& board.getPiece(row, column) === board.getPiece((row + 1).toByte().toInt(), (column + 1).toByte().toInt())
					&& board.getPiece(row, column) === board.getPiece((row + 2).toByte().toInt(), (column + 2).toByte().toInt())
					&& board.getPiece(row, column) === board.getPiece((row + 3).toByte().toInt(), (column + 3).toByte().toInt())) {
				return board.getPiece(row, column).getWinner()
			}
		}
	}
	return WinState.NoOne
}

private fun rightLeftDiagonalWin(board: Board): WinState {
	for (row in 0 until board.getRowCount() - 3) {
		for (column in (board.getColumnCount() - 1).toByte() downTo 4) {
			if (board.getPiece(row, column) !== Piece.Empty
					&& board.getPiece(row, column) === board.getPiece((row + 1).toByte().toInt(), (column - 1).toByte().toInt())
					&& board.getPiece(row, column) === board.getPiece((row + 2).toByte().toInt(), (column - 2).toByte().toInt())
					&& board.getPiece(row, column) === board.getPiece((row + 3).toByte().toInt(), (column - 3).toByte().toInt())) {
				return board.getPiece(row, column).getWinner()
			}
		}
	}
	return WinState.NoOne
}