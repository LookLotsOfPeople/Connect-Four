package com.beyondbell.connectFourAPI

import java.applet.Applet
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

internal class ConnectFourTest : Applet(), KeyListener, MouseListener {
    private var yellowWins = 0
    private var redWins = 0

    private var gameWon: WinState = WinState.NoOne
    private var turn = false
    private var board = Board()

    override fun init() {
        name = "Connect Four"
        background = Color.GRAY

        isFocusable = true
        isEnabled = true

        addMouseListener(this)
        addKeyListener(this)
    }

    override fun paint(g: Graphics) {
        drawBoard(g)
        drawPieces(g)
        drawScoreboard(g)
        drawRules(g)
        drawWinner(g)
    }

    private fun drawBoard(g: Graphics) {
        g.color = Color.BLACK
        g.fillRect(0, 0, 700, 600)
    }

    private fun drawPieces(g: Graphics) {
        for (row in 0 until board.getRowCount()) {
            for (column in 0 until board.getColumnCount()) {
                g.color = board.getPiece(row, column).color
                g.fillOval(10 + 100 * column, 10 + 100 * row, 80, 80)
            }
        }
    }

    private fun drawScoreboard(g: Graphics) {
        g.font = Font("Times New Roman", Font.PLAIN, 16)
        g.color = Color.BLACK
        g.drawString("Yellow", 710, 20)
        g.drawString("Red", 770, 20)
        g.drawString("-", 740, 40)
        g.drawString("" + yellowWins, 710, 40)
        g.drawString("" + redWins, 770, 40)
    }

    private fun drawRules(g: Graphics) {
        g.font = Font("Times New Roman", Font.PLAIN, 16)
        g.color = Color.BLACK
        g.drawString("How To Play", 710, 400)
        g.drawString("Either Click", 710, 420)
        g.drawString("A Row", 710, 440)
        g.drawString("Or Press", 710, 460)
        g.drawString("Numbers", 710, 480)
    }

    private fun drawWinner(g: Graphics) {
        g.font = Font("Times New Roman", Font.PLAIN, 16)
        g.color = Color.BLACK
        when (gameWon) {
            WinState.NoOne -> {
                g.drawString("It is ", 710, 200)
                if (turn) {
                    g.drawString("Red's", 710, 220)
                } else {
                    g.drawString("Yellow's", 710, 220)
                }
                g.drawString("turn!", 710, 240)
            }
            WinState.Yellow -> {
                g.drawString("Yellow Won!", 710, 200)
                yellowWins++
                g.drawString("Press Space!", 710, 220)
            }
            WinState.Red -> {
                g.drawString("Red Won!", 710, 200)
                redWins++
                g.drawString("Press Space!", 710, 220)
            }
            WinState.Tie -> {
                g.drawString("Tie Game!", 710, 200)
                g.drawString("Press Space!", 710, 220)
            }
        }
    }

    private fun clickPiece(realX: Int) {
        val x = (realX - 10) / 100
        if (x < board.getColumnCount() && x >= 0 && board.getPiece(0, x) === Piece.Empty) {
            updateBoard(x)
        }
    }

    private fun numberPress(realNumber: Char) {
        val e = (realNumber - 49).toInt()
        if (e >= 0 && e < board.getColumnCount() && board.getPiece(0, e) === Piece.Empty) {
            updateBoard(e)
        }
    }

    private fun updateBoard(x: Int) {
        val piece: Piece = if (turn) {
            Piece.Red
        } else {
            Piece.Yellow
        }
        turn = !turn

        for (y in (board.getRowCount() - 1) downTo 0) {
            if (board.getPiece(y, x) === Piece.Empty) {
                board.setPiece(y, x, piece)
                break
            }
        }
    }

    private fun checkWin() {
        gameWon = getWinState(board)
    }

    private fun resetGame() {
        turn = false
        gameWon = WinState.NoOne
        board = Board()
    }


    override fun mouseClicked(e: MouseEvent) {
        if (gameWon === WinState.NoOne) {
            clickPiece(e.x)
            checkWin()
            repaint()
        }
    }

    override fun keyTyped(e: KeyEvent) {
        if (gameWon === WinState.NoOne) {
            numberPress(e.keyChar)
            checkWin()
            repaint()
        } else {
            if (e.keyChar.toInt() == 32) {
                resetGame()
                repaint()
            }
        }
    }

    // Unimplemented

    override fun keyPressed(e: KeyEvent) {

    }

    override fun keyReleased(e: KeyEvent) {

    }

    override fun mouseReleased(e: MouseEvent) {

    }

    override fun mouseEntered(e: MouseEvent) {

    }

    override fun mouseExited(e: MouseEvent) {

    }

    override fun mousePressed(e: MouseEvent) {
        
    }
}