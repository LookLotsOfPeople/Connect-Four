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
	private Board board = new Board();

	public void init() {
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
		for (byte i = 0; i < board.getRowCount(); i++) {
			for (byte h = 0; h < board.getColumnCount(); h++) {
				g.setColor(board.getPiece(i, h).getColor());
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

	private void clickPiece(byte x) {
		x = (byte) ((x - 10) / 100);
		if (x < board.getColumnCount() && x >= 0 && board.getPiece((byte) 0, x) == Piece.Empty) {
			updateBoard(x);
		}
	}

	private void numberPress(byte e) {
		e = (byte) (e - 49);
		if (e >= 0 && e < board.getColumnCount() && board.getPiece((byte) 0, e) == Piece.Empty) {
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

		for (byte y = (byte) (board.getRowCount() - 1); y >= 0; y--) {
			if (board.getPiece(y, (byte) x) == Piece.Empty) {
				board.setPiece(y, (byte) x, piece);
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
		board = new Board();
	}

	private Piece[][] getEmptyBoard() {
		return new Piece[][]{

		};
	}


	@Override
	public final void mouseClicked(MouseEvent e) {
		if (gameWon == WinState.NoOne) {
			clickPiece((byte) e.getX());
			checkWin();
			repaint();
		}
	}

	@Override
	public final void keyTyped(KeyEvent e) {
		if (gameWon == WinState.NoOne) {
			numberPress((byte) e.getKeyChar());
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