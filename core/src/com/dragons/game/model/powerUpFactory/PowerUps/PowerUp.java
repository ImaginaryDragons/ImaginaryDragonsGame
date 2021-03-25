package com.dragons.game.model.powerUpFactory.PowerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.dragons.game.model.Subject;
import com.dragons.game.view.modelViews.IModelObserver;


public abstract class PowerUp extends Subject {

    // TODO: add/modify fields
    protected float x, y;
    protected Body body;
    protected Texture texture;

    public PowerUp(IModelObserver observer) {
        super(observer);
    }

    public abstract void whenPickedUp();



}
