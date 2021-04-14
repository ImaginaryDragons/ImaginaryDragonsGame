package com.dragons.game.model.bomb;

import com.badlogic.gdx.math.Vector2;

import com.dragons.game.model.Model;
import com.dragons.game.model.gameWorld.GameMap;
import com.dragons.game.utilities.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.dragons.game.model.bomb.BombType.NORMALBOMB;


public class Bomb extends Model implements IBomb {

    private float loadingTime;
    private boolean bombExploded;
    private float bombRange;
    private static final boolean isStatic = false;
    private static final boolean isSensor = false;


    public Bomb(Vector2 pos, float width, float bombRange){
        super(pos, NORMALBOMB, width, width, isStatic, isSensor);
        this.bombRange = bombRange;
        this.bombExploded = false;
        loadingTime = Constants.BombExplodeTime;

    }

    public void update(float timestep){
        loadingTime -= timestep;
        if (loadingTime < 0) {
            this.bombExploded = true;
        }
    }

    public ArrayList<Vector2> getFireTiles(GameMap gameMap) {
        ArrayList<Vector2> fireTiles = new ArrayList<Vector2>();
        fireTiles.addAll(checkForWall("up", gameMap));
        fireTiles.addAll(checkForWall("down", gameMap));
        fireTiles.addAll(checkForWall("left", gameMap));
        fireTiles.addAll(checkForWall("right", gameMap));
        return fireTiles;
    }

    private ArrayList<Vector2> checkForWall(String direction, GameMap gameMap) {
        Vector2 checkTile = new Vector2(0, 0);
        ArrayList<Vector2> fireTiles = new ArrayList<Vector2>();
        int startPos;
        int increment;
        switch (direction) {
            case "up":
                startPos = (int) super.getPosition().y;
                checkTile.x = (int) super.getPosition().x;
                increment = 32;
                break;
            case "down":
                startPos = (int) super.getPosition().y;
                checkTile.x = (int) super.getPosition().x;
                increment = -32;
                break;
            case "left":
                startPos = (int) super.getPosition().x;
                checkTile.y = (int) super.getPosition().y;
                increment = -32;
                break;
            case "right":
                startPos = (int) super.getPosition().x;
                checkTile.y = (int) super.getPosition().y;
                increment = 32;
                break;
            default:
                startPos = 0;
                increment = 0;
        }

        for (int i = 0; i < bombRange; i += 32) {
            if (direction == "up" || direction == "down") {
                checkTile.y = startPos + increment;
            } else if (direction == "left" || direction == "right") {
                checkTile.x = startPos + increment;
            }
            Vector2 tile = gameMap.pos2tile(checkTile);

            if (gameMap.tileContainers.get(tile.x, tile.y).contains("desblock")) {
                //denne tilen skal bli lik et bilde, starter i tilestart, 32x32, og så stoppe
                //Sjekke kontakt, Eldar og Jakob skal se på det
                fireTiles.add(tile);

            } else if (gameMap.tileContainers.get(tile.x, tile.y).contains("wallblock")) {
                //denne tilen skal ikke bli lik et bilde
                //Sjekke kontakt, Eldar og Jakob skal se på det

            } else if (gameMap.tileContainers.get(tile.x, tile.y).contains("desPowerupBlock")) {
                //denne tilen skal bli til en powerup
                //Sjekke kontakt, Eldar og Jakob skal se på det
                fireTiles.add(tile);
            } else {
                //Bli lik et bilde, sjekke videre
                fireTiles.add(tile);
            }

        }
        return fireTiles;
    }
}
