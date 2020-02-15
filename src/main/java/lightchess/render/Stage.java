package lightchess.render;

import java.awt.*;

public interface Stage {

    void render(Graphics2D g);

    void tick();

    void start();

    void end();
}
