package com.dragons.game.model.bomb;

import com.badlogic.gdx.math.Vector2;
import com.dragons.game.model.IModelType;
import com.dragons.game.model.Model;


public class Fire extends Model implements IFire{


    public Fire(Vector2 position, IModelType type, float width, float height) {
        super(position, type, width, height);

    }


}
