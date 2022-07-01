package display;

import game.Game;
import input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Display extends JFrame {
    private final Input input;
    private final Game game;
    private final Canvas canva;
    private final Renderer renderer;
    private JLabel scoreLabel;
    private String name;
    private int score;
    private String finalScore;

    public Display(int width, int height, Game game ,Input input){
        this.game = game;
        this.input = input;
        finalScore = "";
        setTitle("Juego");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setMenu();

        canva = new Canvas();
        canva.setPreferredSize(new Dimension(width, height));
        canva.setFocusable(false);
        add(canva);
        pack();

        canva.createBufferStrategy(3);

        this.renderer = new Renderer();

        setVisible(true);
    }

    private void setMenu() {
        JPanel menuPanel = new JPanel();
        scoreLabel = new JLabel();
        JLabel userLabel = new JLabel();
        JButton playButton = new JButton();
        JTextField userInput = new JTextField();

        menuPanel.setLayout(null);
        menuPanel.setLocation(0, 600);
        menuPanel.setSize(800, 120);

        userLabel.setSize(600, 40);
        userLabel.setText("Usuario: " + (name != null ? name : ""));
        userLabel.setLocation(50,20);

        playButton.setSize(120, 40);
        playButton.setText("Play");
        playButton.setLocation(600, 20);
        playButton.setEnabled(false);
        playButton.addActionListener(actionEvent -> {
            if (name == null){
                JLabel alertText = new JLabel("Ingresa tu nombre.");
                JDialog alert = new JDialog();
                alert.setTitle("Error");
                alert.setSize(200,120);
                alert.setVisible(true);
                alert.setLocationRelativeTo(null);
                alert.add(alertText);
            } else if (game.isPlaying()){
                finalScore = finalScore.concat("Jugador: " + name + ". Puntuacion: " + score + "\n");
                try {
                    PrintWriter writer = new PrintWriter("practica.out", StandardCharsets.UTF_8);
                    writer.println(finalScore);
                    writer.close();
                    game.reset();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = null;
                userLabel.setText("Usuario: ");
                playButton.setText("Play");
                userInput.setVisible(true);
                userInput.setFocusable(true);
                playButton.setEnabled(false);
                scoreLabel.setVisible(false);
                scoreLabel.setText("Puntuacion: 0");
            } else {
                playButton.setText("Save");
                playButton.setFocusable(false);
                game.setIsPlaying(true);
                canva.setFocusable(true);
                canva.addKeyListener(input);
                canva.requestFocusInWindow();
            }
        });

        userInput.setSize(200, 40);
        userInput.setLocation(120,20);
        userInput.addActionListener(actionEvent -> {
            name = userInput.getText();
            userLabel.setText("Usuario: " + (name != null ? name : ""));
            userInput.setFocusable(false);
            userInput.setVisible(false);
            playButton.setEnabled(true);
            scoreLabel.setVisible(true);
        });

        scoreLabel.setSize(600, 40);
        scoreLabel.setText("Puntuacion: 0");
        scoreLabel.setLocation(350,20);
        scoreLabel.setVisible(false);

        menuPanel.add(userLabel);
        menuPanel.add(userInput);
        menuPanel.add(scoreLabel);
        menuPanel.add(playButton);
        add(menuPanel);
    }

    public void update(int score){
        this.score = score;
        scoreLabel.setText("Puntuacion: " + score);
    }

    public void render(){
        BufferStrategy bufferStrategy = canva.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,canva.getWidth(), canva.getHeight());

        renderer.render(game, graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
