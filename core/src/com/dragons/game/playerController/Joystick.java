package com.dragons.game.playerController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.dragons.game.utilities.Constants;

import static com.dragons.game.utilities.Constants.HEIGHT;
import static com.dragons.game.utilities.Constants.JOYSTICK_ORIGIN_Y;
import static com.dragons.game.utilities.Constants.JOYSTICK_PERIMETER_RADIUS;
import static com.dragons.game.utilities.Constants.JOYSTICK_ORIGIN_X;
import static com.dragons.game.utilities.Constants.JOYSTICK_RADIUS;

public class Joystick implements InputProcessor {

    private Circle joystick;
    private final Circle perimeter;
//    private GameObject player; // body and player

    private final Texture joystickBG;
    private final Texture joystickTexture;

    public Joystick() {
        joystick = new Circle(JOYSTICK_ORIGIN_X, JOYSTICK_ORIGIN_Y, JOYSTICK_RADIUS);
        perimeter = new Circle(JOYSTICK_ORIGIN_X, JOYSTICK_ORIGIN_Y, JOYSTICK_PERIMETER_RADIUS);
        Gdx.input.setInputProcessor(this);

        Pixmap pixmapJoystick = new Pixmap((int) (2*joystick.radius+1), (int) (2*joystick.radius+1), Pixmap.Format.RGBA8888);
        pixmapJoystick.setBlending(Pixmap.Blending.None);
        pixmapJoystick.setColor(Color.BLACK);
        pixmapJoystick.fillCircle((int) joystick.radius, (int) joystick.radius, (int) joystick.radius);
        joystickTexture = new Texture(pixmapJoystick);

        Pixmap pixmapJoystickBG = new Pixmap((int) (2*perimeter.radius), (int) (2*perimeter.radius), Pixmap.Format.RGBA8888);
        pixmapJoystickBG.setBlending(Pixmap.Blending.None);
        pixmapJoystickBG.setColor(new Color(0,0,0,0.3f));
        pixmapJoystickBG.fillCircle((int) perimeter.radius, (int) perimeter.radius, (int) perimeter.radius);
        joystickBG = new Texture(pixmapJoystickBG);
    }

    public void render(SpriteBatch sb) {
//        sb.draw(joystickBG, origo.x - (float)joystickBG.getWidth()/2, origo.y - (float)joystickBG.getHeight()/2);
//        sb.draw(joystickTexture, origo.x - (float)joystickTexture.getWidth()/2, origo.y - (float)joystickTexture.getHeight()/2);
        sb.draw(joystickTexture, joystick.x-joystick.radius, joystick.y-joystick.radius);
        sb.draw(joystickBG, JOYSTICK_ORIGIN_X - JOYSTICK_PERIMETER_RADIUS,JOYSTICK_ORIGIN_Y - JOYSTICK_PERIMETER_RADIUS);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touch = new Vector2(screenX, HEIGHT-screenY);
        

//        if (perimeter.contains(joystick)) {  // Contains the joystick circle in the perimeter.
//            joystick.x = screenX;            // Does not show the joystick with this
//            joystick.y = screenY;            // Used next two lines to test -v
//        }

        joystick.x = screenX;  // For testing
        joystick.y = HEIGHT-screenY;

        float relativeX = screenX - JOYSTICK_ORIGIN_X+JOYSTICK_RADIUS;
        float relativeY = HEIGHT - screenY + JOYSTICK_ORIGIN_Y-JOYSTICK_RADIUS;

        if (relativeX < relativeY && -relativeX < relativeY) {
            Gdx.app.log("Touch down", String.format("UP\t\tscreenX: %d\trelativeX: %.1f\n\t\t\t\t\tscreenY: %d\trelativeY: %.1f", screenX, relativeX, screenY, relativeY));
//            player.body.setVelocity("UP");
        } else if (relativeX > relativeY && -relativeX < relativeY) {
            Gdx.app.log("Touch down", String.format("RIGHT\tscreenX: %d\trelativeX: %.1f\n\t\t\t\t\tscreenY: %d\trelativeY: %.1f", screenX, relativeX, screenY, relativeY));
//            player.body.setVelocity("RIGHT");
        } else if (relativeX < relativeY && -relativeX > relativeY) {
            Gdx.app.log("Touch down", String.format("LEFT\tscreenX: %d\trelativeX: %.1f\n\t\t\t\t\tscreenY: %d\trelativeY: %.1f", screenX, relativeX, screenY, relativeY));
//            player.body.setVelocity("LEFT");
        } else {
            Gdx.app.log("Touch down", String.format("DOWN\tscreenX: %d\trelativeX: %.1f\n\t\t\t\t\tscreenY: %d\trelativeY: %.1f", screenX, relativeX, screenY, relativeY));
//            player.body.setVelocity("DOWN");
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        joystick.x = JOYSTICK_ORIGIN_X;
        joystick.y = JOYSTICK_ORIGIN_Y;
//        player.body.setVelocity("NULL");
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        if (perimeter.contains(joystick)) {  // Contains the joystick circle in the perimeter.
//            joystick.x = screenX;            // Does not show the joystick with this
//            joystick.y = screenY;            // Used next two lines to test -v
//        }
        joystick.x = screenX;  // For testing
        joystick.y = HEIGHT - screenY;

        float relativeX = screenX - JOYSTICK_ORIGIN_X;
        float relativeY = screenY - JOYSTICK_ORIGIN_Y;
        if (relativeX < relativeY && -relativeX < relativeY) {
            Gdx.app.log("Touch dragged", "UP");
//            player.body.setVelocity("UP");
        } else if (relativeX > relativeY && -relativeX < relativeY) {
            Gdx.app.log("Touch dragged", "RIGHT");
//            player.body.setVelocity("RIGHT");
        } else if (relativeX < relativeY && -relativeX > relativeY) {
            Gdx.app.log("Touch dragged", "LEFT");
//            player.body.setVelocity("LEFT");
        } else {
            Gdx.app.log("Touch dragged", "DOWN");
//            player.body.setVelocity("DOWN");
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
