package com.beyondbell.connectFour;

import org.jetbrains.annotations.NotNull;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class ConnectFour extends Applet implements KeyListener, MouseListener {
	private int yellowWins = 0;
	private int redWins = 0;

	private WinState gameWon = WinState.NoOne;
	private boolean turn = false;
	private Piece[][] board = getEmptyBoard();

	public final void init() {
		setName("Connect Four");
		setBackground(Color.GRAY);

		final Dimension dimension = new Dimension(800, 600);
		resize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);

		setFocusable(true);
		setEnabled(true);

		addMouseListener(this);
		addKeyListener(this);
	}

	public final void paint(Graphics g) {
		drawBoard(g);
		drawPieces(g);
		drawScoreboard(g);
		drawRules(g);
		drawWinner(g);
	}

	private void drawBoard(@NotNull Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 600);
	}

	private void drawPieces(Graphics g) {
		for (int i = 0; i < board.length; i++) {
			for (int h = 0; h < board[0].length; h++) {
				g.setColor(board[i][h].getColor());
				g.fillOval(10 + (100 * h), 10 + (100 * i), 80, 80);
			}
		}
	}

	private void drawScoreboard(Graphics g) {
		g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g.setColor(Color.BLACK);
		g.drawString("Yellow", 710, 20);
		g.drawString("Red", 770, 20);
		g.drawString("-", 740, 40);
		g.drawString("" + yellowWins, 710, 40);
		g.drawString("" + redWins, 770, 40);
	}

	private void drawRules(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g.drawString("How To Play", 710, 400);
		g.drawString("Either Click", 710, 420);
		g.drawString("A Row", 710, 440);
		g.drawString("Or Press", 710, 460);
		g.drawString("Numbers", 710, 480);
	}

	private void drawWinner(Graphics g) {
		g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		g.setColor(Color.BLACK);
		switch (gameWon) {
			case NoOne:
				g.drawString("It is ", 710, 200);
				if (turn) {
					g.drawString("Red's", 710, 220);
				} else {
					g.drawString("Yellow's", 710, 220);
				}
				g.drawString("turn!", 710, 240);
				break;
			case Yellow:
				g.drawString("Yellow Won!", 710, 200);
				yellowWins++;
				g.drawString("Press Space!", 710, 220);
				break;
			case Red:
				g.drawString("Red Won!", 710, 200);
				redWins++;
				g.drawString("Press Space!", 710, 220);
				break;
			case Tie:
				g.drawString("Tie Game!", 710, 200);
				g.drawString("Press Space!", 710, 220);
				break;
		}
	}

	private void clickPiece(int x) {
		x = (x - 10) / 100;
		if (x < board[0].length && x >= 0 && board[0][x] == Piece.Empty) {
			updateBoard(x);
		}
	}

	private void numberPress(int e) {
		e = e - 49;
		if (e >= 0 && e < board[0].length && board[0][e] == Piece.Empty) {
			updateBoard(e);
		}
	}

	private void updateBoard(int x) {
		final Piece piece;
		if (turn) {
			piece = Piece.Red;
		} else {
			piece = Piece.Yellow;
		}
		turn = !turn;

		for (int y = board.length - 1; y >= 0; y--) {
			if (board[y][x] == Piece.Empty) {
				board[y][x] = piece;
				break;
			}
		}
	}

	private void checkWin() {
		gameWon = WinChecker.getWinState(board);
	}

	private void resetGame() {
		turn = false;
		gameWon = WinState.NoOne;
		board = getEmptyBoard();
	}

	private Piece[][] getEmptyBoard() {
		return new Piece[][]{
				{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
				{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
				{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
				{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
				{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
				{Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
		};
	}


	@Override
	public final void mouseClicked(MouseEvent e) {
		if (gameWon == WinState.NoOne) {
			clickPiece(e.getX());
			checkWin();
			repaint();
		}
	}

	@Override
	public final void keyTyped(KeyEvent e) {
		if (gameWon == WinState.NoOne) {
			numberPress(e.getKeyChar());
			checkWin();
			repaint();
		} else {
			if (e.getKeyChar() == 32) {
				resetGame();
				repaint();
			}
		}
	}

	// Unimplemented

	@Override
	public void keyPressed(final KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}