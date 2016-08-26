package com.independentgames.porter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jeffryporter on 8/22/16.
 */
public class PlasmaShot
{
    private Texture tiles;
    private TextureRegion[][] grid;
    private TextureRegion shotA;
    private TextureRegion shotB;
    private int windowHeight;
    private int windowWidth;
    private float x, y, xv, yv;
    private Animation fired;



    public static float TANK_SIZE = 10000f;


    public void create (float shipX, float shipY)
    {
        tiles = new Texture(MyGdxGame.ASSET_DIRECTORY + "ParsecTiles.png");
        grid = TextureRegion.split(tiles, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        shotA = grid[1][4];
        shotB = grid[1][5];
        fired = new Animation(.15f, shotA, shotB);

    }

    public TextureRegion animationTile(float time)
    {
           return fired.getKeyFrame(time, true);
    }

    public void moveShot()
    {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            xv = MyGdxGame.MAX_VELOCITY * 5;
        }

        float delta = Gdx.graphics.getDeltaTime();
//        y+= yv * delta;
//        x+= xv * delta;
//        yv = decelerate(yv);
//        xv = decelerate(xv);

        windowHeight = Gdx.graphics.getHeight();
        windowWidth = Gdx.graphics.getWidth();

    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }



}



