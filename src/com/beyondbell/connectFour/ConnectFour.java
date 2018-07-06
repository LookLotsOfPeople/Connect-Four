package com.beyondbell.connectFour;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public final class ConnectFour extends Applet implements KeyListener, MouseListener {
    private boolean turn = false;
    private Piece gameWon = Piece.Empty;
    private int yellowWins = 0;
    private int redWins = 0;
    private Piece[][] board = {
            {Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
            {Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
            {Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
            {Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
            {Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
            {Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty, Piece.Empty},
    };

    public void init() {
        setFocusable(true);
        resize(800, 600);
        addMouseListener(this);
        addKeyListener(this);
    }

    public void paint(Graphics g) {
        drawBoard(g);
        drawPieces(g);
        drawScoreboard(g);
        drawRules(g);
        drawWinner(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 600);
    }

    private void drawPieces(Graphics g) {
        for (int i = 0; i < board.length; i++) {
            for (int h = 0; h < board[0].length; h++) {
                g.setColor(board[i][h].getColor());
                g.fillOval(10 + (100 * h), 10 + (100 * i), 80,80);
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
        if (gameWon != null) {
            switch (gameWon) {
                case Empty:
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
            }
        } else {
            g.drawString("Tie Game!", 710, 200);
            g.drawString("Press Space!", 710, 220);
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
        //Checks For Blackout
        gameWon = null;
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == Piece.Empty) {
                gameWon = Piece.Empty;
            }
        }

        //Checks Horizontal Wins
        for (int i = 0; i < board.length; i++) {
            for (int h = 0; h < board[0].length - 3; h++) {
                if (board[i][h] != Piece.Empty
                        && board[i][h] == board[i][h + 1]
                        && board[i][h] == board[i][h + 2]
                        && board[i][h] == board[i][h + 3]) {
                    gameWon = board[i][h];
                }
            }
        }

        //Checks Vertical Wins
        for (int i = 0; i < board.length - 3; i++) {
            for (int h = 0; h < board[0].length; h++) {
                if (board[i][h] != Piece.Empty
                        && board[i][h] == board[i + 1][h]
                        && board[i][h] == board[i + 2][h]
                        && board[i][h] == board[i + 3][h]) {
                    gameWon = board[i][h];
                }
            }
        }

        //Checks Diagonal \ Wins
        for (int i = 0; i < board.length - 3; i++) {
            for (int h = 0; h < board[0].length - 3; h++) {
                if (board[i][h] != Piece.Empty
                        && board[i][h] == board[i + 1][h + 1]
                        && board[i][h] == board[i + 2][h + 2]
                        && board[i][h] == board[i + 3][h + 3]) {
                    gameWon = board[i][h];
                }
            }
        }

        //Checks Diagonal / Win
        for (int i = 0; i < board.length - 3; i++) {
            for (int h = board[0].length - 1; h > 3; h--) {
                if (board[i][h] != Piece.Empty
                        && board[i][h] == board[i + 1][h - 1]
                        && board[i][h] == board[i + 2][h - 2]
                        && board[i][h] == board[i + 3][h - 3]) {
                    gameWon = board[i][h];
                }
            }
        }
    }

    private void resetGame() {
        turn = false;
        gameWon = Piece.Empty;
        //Reset Board
        for (int i = 0; i < board.length; i++) {
            for (int h = 0; h < board[0].length; h++) {
                board[i][h] = Piece.Empty;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(gameWon == Piece.Empty) {
            numberPress(e.getKeyChar());
            checkWin();
            repaint();
        } else {
            if(e.getKeyChar() == 32) {
                resetGame();
                repaint();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gameWon == Piece.Empty) {
            clickPiece(e.getX());
            checkWin();
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}