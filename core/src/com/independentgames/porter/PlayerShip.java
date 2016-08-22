package com.independentgames.porter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by jeffryporter on 8/21/16.
 */
public class PlayerShip
{
    private Texture tiles;
    private TextureRegion[][] grid;
    private TextureRegion[][] tinyGrid;
    private TextureRegion ship;
    private TextureRegion slowEngine;
    private TextureRegion medEngine;
    private TextureRegion fastEngine;
    private TextureRegion slowEngineReversed;
    private TextureRegion medEngineReversed;
    private TextureRegion fastEngineReversed;
    private TextureRegion weaponFire;
    private TextureRegion gotHit;
    private int windowHeight;
    private int windowWidth;
    private float x, y, xv, yv;
    private Animation speedUp;
    private Animation slowDown;
    private Animation flyNormal;

    private int score;
    private int health;
    private boolean damage;
    private boolean isAlive;

    private static int CYCLES = 1000;


    public void create () {

        tiles = new Texture(MyGdxGame.ASSET_DIRECTORY + "ParsecTiles.png");
        tinyGrid = TextureRegion.split(tiles, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT);
        grid = TextureRegion.split(tiles, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        ship = grid[0][0];
        slowEngine = tinyGrid[1][0];
        medEngine = tinyGrid[1][1];
        fastEngine = tinyGrid[2][0];
        slowEngineReversed = new TextureRegion(slowEngine);
        slowEngineReversed.flip(false, true);
        medEngineReversed = new TextureRegion(medEngine);
        medEngineReversed.flip(false, true);
        fastEngineReversed = new TextureRegion(fastEngine);
        fastEngineReversed.flip(false, true);
        slowDown = new Animation(.15f, slowEngine, slowEngineReversed);
        speedUp = new Animation(.15f, fastEngine, fastEngineReversed);
        flyNormal = new Animation(.15f, medEngine, medEngineReversed);

        score = 0;
        damage = false;
        isAlive = true;
    }

    public TextureRegion animationTile(float time)
    {
        TextureRegion img;
        if (xv<0)
        {
            img = slowDown.getKeyFrame(time, true);
        }
        else if (xv>0)
        {
            img = speedUp.getKeyFrame(time, true);
        }
        else
        {
            img = flyNormal.getKeyFrame(time, true);
        }
        return img;
    }

    public void moveShip()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
                yv = MyGdxGame.MAX_VELOCITY;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
                yv = -MyGdxGame.MAX_VELOCITY;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
                xv = MyGdxGame.MAX_VELOCITY;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
                xv = -MyGdxGame.MAX_VELOCITY;
        }

        float delta = Gdx.graphics.getDeltaTime();
        y+= yv * delta;
        x+= xv * delta;
        yv = decelerate(yv);
        xv = decelerate(xv);

        windowHeight = Gdx.graphics.getHeight();
        windowWidth = Gdx.graphics.getWidth();

        if (x<0)
        {
            x = windowWidth;
        }
        if (x>(windowWidth-MyGdxGame.WIDTH))
        {
            x = windowWidth-(MyGdxGame.WIDTH*MyGdxGame.SCALE_MULTIPLIER);
        }

        if (y<(-MyGdxGame.HEIGHT*MyGdxGame.SCALE_MULTIPLIER))
        {
            y = windowHeight;
        }
        if (y>(windowHeight))
        {
            y = -(MyGdxGame.HEIGHT*MyGdxGame.SCALE_MULTIPLIER);
        }

    }

    public TextureRegion getShipTile()
    {
        return ship;
    }

//    public void checkForDamage(float time)
//    {
//        damage = false;
//
//        for ( Monster monster : monsters)
//        {
//            if ((Math.abs(monster.getX() - x) < MyGdxGame.PROXIMITY_TOUCHING) &&
//                    (Math.abs(monster.getY() - y) < MyGdxGame.PROXIMITY_TOUCHING))
//            {
//                if (time - monster.getAttackTimer() >= 1)
//                {
//                    monster.setAttackTimer(time);
//                    health--;
//                    monster.getAttackSound().play(1.0f);
//                    damage = true;
//                }
//            }
//            if (health <= 0)
//            {
//                isAlive = false;
//            }
//        }
//    }

    public float decelerate(float velocity)
    {
        velocity *= MyGdxGame.DECLERATION_RATE;
        if (Math.abs(velocity) < MyGdxGame.STOP_THRESHHOLD)
        {
            velocity = 0;
        }
        return velocity;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public int getScore()
    {
        return score;
    }

    public int getHealth()
    {
        return health;
    }

    public TextureRegion getHitTile()
    {
        return gotHit;
    }
    public boolean getDamageStatus()
    {
        return damage;
    }

    public boolean isAlive()
    {
        return isAlive;
    }


}
