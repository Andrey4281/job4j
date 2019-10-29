package pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int moving = 1;
        while (!Thread.currentThread().isInterrupted()) {
            if (this.rect.getX() >= 300 - this.rect.getWidth()) {
                moving = -1;
            } else if (this.rect.getX() <= 0) {
                moving = +1;
            }

            this.rect.setX(this.rect.getX() + moving);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}