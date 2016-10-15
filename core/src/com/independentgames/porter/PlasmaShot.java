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
    private float x, y, xv;
    private Animation fired;
    public static TextureRegion tilePiece;



    public static float TANK_SIZE = 10000f;


    public void create (float shipX, float shipY)
    {
        tiles = new Texture(MyGdxGame.ASSET_DIRECTORY + "ParsecTiles.png");
        grid = TextureRegion.split(tiles, MyGdxGame.WIDTH, (MyGdxGame.HEIGHT/2));
        shotA = grid[1][3];
        shotB = grid[1][4];
        tilePiece =  new TextureRegion();
        fired = new Animation(.15f, shotA, shotB);
        x = shipX;
        y = shipY;

    }

    public TextureRegion animationTile(float time)
    {
           return fired.getKeyFrame(time, true);
    }

    public void moveShot(float time)
    {
        xv = MyGdxGame.MAX_VELOCITY;
        x+= xv*time;
        tilePiece = fired.getKeyFrame(time);
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



