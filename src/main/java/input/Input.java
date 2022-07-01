package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private final boolean[] pressed;

    public Input() {
        pressed = new boolean[255];
    }

    public boolean isPressed(int keyCode){
        return pressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressed[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        pressed[keyEvent.getKeyCode()] = false;
    }
}
