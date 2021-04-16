package com.dragons.game.playerController;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import static com.dragons.game.utilities.Constants.JOYSTICK_ORIGIN_X;
import static com.dragons.game.utilities.Constants.JOYSTICK_ORIGIN_Y;
import static com.dragons.game.utilities.Constants.JOYSTICK_PERIMETER_RADIUS;

public class JoystickView {

    private final Joystick joystick;
    private Circle pos;

    private final Texture joystickTexture;
    private final Texture joystickBG;

    public JoystickView(Joystick joystick) {
        this.joystick = joystick;

        pos = joystick.getCircle();
        Circle perimeter = joystick.getPerimeter();

        Pixmap pixmapJoystick = new Pixmap((int) (2 * pos.radius + 1), (int) (2 * pos.radius + 1), Pixmap.Format.RGBA8888);
        pixmapJoystick.setBlending(Pixmap.Blending.None);
        pixmapJoystick.setColor(Color.BLACK);
        pixmapJoystick.fillCircle((int) pos.radius, (int) pos.radius, (int) pos.radius);
        joystickTexture = new Texture(pixmapJoystick);

        // Create texture for joystick background
        Pixmap pixmapJoystickBG = new Pixmap((int) (2 * perimeter.radius), (int) (2 * perimeter.radius), Pixmap.Format.RGBA8888);
        pixmapJoystickBG.setBlending(Pixmap.Blending.None);
        pixmapJoystickBG.setColor(new Color(0, 0, 0, 0.3f));
        pixmapJoystickBG.fillCircle((int) perimeter.radius, (int) perimeter.radius, (int) perimeter.radius);
        joystickBG = new Texture(pixmapJoystickBG);
    }

    public void render(SpriteBatch batch) {
        pos = joystick.getCircle();

        batch.draw(joystickTexture, pos.x-pos.radius, pos.y-pos.radius);
        batch.draw(joystickBG, JOYSTICK_ORIGIN_X - JOYSTICK_PERIMETER_RADIUS,JOYSTICK_ORIGIN_Y - JOYSTICK_PERIMETER_RADIUS);
    }

}
