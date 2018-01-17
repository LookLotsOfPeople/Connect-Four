import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Connect4 extends Applet implements KeyListener, MouseListener {
    private boolean turn = false;
    private int gameWon = 0;
    private int yellowWins = 0;
    private int redWins = 0;
    private int[][] board = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
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
                switch (board[i][h]) {
                    case 0:
                        g.setColor(Color.WHITE);
                        break;
                    case 1:
                        g.setColor(Color.YELLOW);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        break;
                }
                g.fillOval(10 + (100 * h), 10 + (100 * i), 80,80);
            }
        }
    }

    private void drawScoreboard(Graphics g) {
        g.setFont(new Font("Times New Roman", 0, 16));
        g.setColor(Color.BLACK);
        g.drawString("Yellow", 710, 20);
        g.drawString("Red", 770, 20);
        g.drawString("-", 740, 40);
        g.drawString("" + yellowWins, 710, 40);
        g.drawString("" + redWins, 770, 40);
    }

    private void drawRules(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", 0, 16));
        g.drawString("How To Play", 710, 400);
        g.drawString("Either Click", 710, 420);
        g.drawString("A Row", 710, 440);
        g.drawString("Or Press", 710, 460);
        g.drawString("Numbers", 710, 480);
    }

    private void drawWinner(Graphics g) {
        g.setFont(new Font("Times New Roman", 0, 16));
        g.setColor(Color.BLACK);
        switch (gameWon) {
            case 1:
                g.drawString("Yellow Won!", 710, 200);
                yellowWins++;
                g.drawString("Press Space!", 710, 220);
                break;
            case 2:
                g.drawString("Red Won!", 710, 200);
                redWins++;
                g.drawString("Press Space!", 710, 220);
                break;
            case 3:
                g.drawString("Tie Game!", 710, 200);
                g.drawString("Press Space!", 710, 220);
                break;
            default:
                g.drawString("It is ", 710, 200);
                if (turn) {
                    g.drawString("Red's", 710, 220);
                } else {
                    g.drawString("Yellow's", 710, 220);
                }
                g.drawString("turn!", 710, 240);
                break;
        }
    }

    private void clickPiece(int x) {
        x = (x - 10) / 100;
        if (x < board[0].length && x >= 0 && board[0][x] == 0) {
            updateBoard(x);
        }
    }

    private void numberPress(int e) {
        e = e - 49;
        if (e >= 0 && e < board[0].length && board[0][e] == 0) {
            updateBoard(e);
        }
    }

    private void updateBoard(int x) {
        int piece;

        if (turn) {
            piece = 2;
        } else {
            piece = 1;
        }
        turn = !turn;

        for (int y = board.length - 1; y >= 0; y--) {
            if (board[y][x] == 0) {
                board[y][x] = piece;
                break;
            }
        }
    }

    private void checkWin() {
        //Checks For Blackout
        gameWon = 3;
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 0) {
                gameWon = 0;
            }
        }

        //Checks Horizontal Wins
        for (int i = 0; i < board.length; i++) {
            for (int h = 0; h < board[0].length - 3; h++) {
                if (board[i][h] != 0
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
                if (board[i][h] != 0
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
                if (board[i][h] != 0
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
                if (board[i][h] != 0
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
        gameWon = 0;
        //Reset Board
        for (int i = 0; i < board.length; i++) {
            for (int h = 0; h < board[0].length; h++) {
                board[i][h] = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(gameWon == 0) {
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
        if(gameWon == 0) {
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