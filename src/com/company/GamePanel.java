package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    final int FIELD_SIZE = 40;

    private int[][] field = new int[FIELD_SIZE][FIELD_SIZE];

    Random rand = new Random();

    private boolean isStarted;

    private int snakeX = 0;
    private int snakeY = 0;
    private LinkedList<SnakePart> parts = new LinkedList<>();

    private Direction direction = Direction.Right;

    private int foodX = 0;
    private int foodY = 0;
    ImageIcon food = new ImageIcon("C:/apple.png");

    private Color evenCellColor = new Color(170, 215, 81);
    private Color oddCellColor = new Color(162, 209, 73);

    Timer timer = new Timer(100, this);

    public GamePanel() {
        super(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        isStarted = false;

        foodX = FIELD_SIZE - 5;
        foodY = FIELD_SIZE / 2;

        snakeX = 5;
        snakeY = FIELD_SIZE / 2;

        parts.add(new SnakePart(snakeX-1,snakeY));

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        int cellSize = this.getWidth() / FIELD_SIZE;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(evenCellColor);
                } else {
                    g.setColor(oddCellColor);
                }
                int startX = j * cellSize;
                int startY = i * cellSize;

                g.fillRect(startX, startY, cellSize, cellSize);
            }

            g.setColor(new Color(255, 255, 255));
            g.fillOval(snakeX * cellSize, snakeY * cellSize, cellSize, cellSize);

            for (SnakePart part : parts){
                g.fillOval(part.getX() * cellSize, part.getY() * cellSize, cellSize, cellSize);
            }

            food.paintIcon(this, g, foodX * cellSize, foodY * cellSize);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (!isStarted) {
            return;
        }

        boolean isFoodEaten = false;
        if (snakeX == foodX && snakeY == foodY){
            isFoodEaten = true;
            generateFoodPosition();
        }

        if (!isFoodEaten){
            parts.removeFirst();
        }
        parts.add(new SnakePart(snakeX,snakeY));

        if (direction == Direction.Up) {
            snakeY -= 1;
        } else if (direction == Direction.Down) {
            snakeY += 1;
        } else if (direction == Direction.Left) {
            snakeX -= 1;
        } else {
            snakeX += 1;
        }

        this.repaint();
    }

    private void generateFoodPosition() {
        foodX = rand.nextInt(FIELD_SIZE);
        foodY = rand.nextInt(FIELD_SIZE);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            isStarted = !isStarted;
        }

        if (keyCode == KeyEvent.VK_UP && direction != Direction.Down) {
            direction = Direction.Up;
        } else if (keyCode == KeyEvent.VK_DOWN && direction != Direction.Up) {
            direction = Direction.Down;
        } else if (keyCode == KeyEvent.VK_LEFT && direction != Direction.Right) {
            direction = Direction.Left;
        } else if (keyCode == KeyEvent.VK_RIGHT && direction != Direction.Left) {
            direction = Direction.Right;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
