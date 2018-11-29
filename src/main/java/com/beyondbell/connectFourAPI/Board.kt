package com.beyondbell.connectFourAPI

import com.beyondbell.connectFourAPI.Piece.Empty

class Board {
	private val board = arrayOf(
			arrayOf(Empty, Empty, Empty, Empty, Empty, Empty, Empty),
			arrayOf(Empty, Empty, Empty, Empty, Empty, Empty, Empty),
			arrayOf(Empty, Empty, Empty, Empty, Empty, Empty, Empty),
			arrayOf(Empty, Empty, Empty, Empty, Empty, Empty, Empty),
			arrayOf(Empty, Empty, Empty, Empty, Empty, Empty, Empty),
			arrayOf(Empty, Empty, Empty, Empty, Empty, Empty, Empty)
	)

	fun getRowCount() : Int {
		return board.size
	}

	fun getColumnCount() : Int {
		return board[0].size
	}

	fun isFull() : Boolean {
		for (column in 0 until board[0].size) {
			if (board[0][column] == Empty) {
				return false
			}
		}
		return true
	}

	fun getPiece(row: Int, column: Int): Piece {
		return board[row][column]
	}

	fun setPiece(row: Int, column: Int, piece: Piece) {
		board[row][column] = piece
	}
}