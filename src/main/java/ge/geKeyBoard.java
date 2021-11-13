package ge;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class geKeyBoard implements KeyListener {

    private static float unit = 0.01f;
    private static final float JUMP_HEIGHT = 5 * unit;

    private geCore core = null;
    private String spriteName = null;
    private String background = null;

    float land_y = 0;
    private LAND landPosition = LAND.EQUAL;
    DIRECTION lastDirection = DIRECTION.NULL;

    private enum DIRECTION{
        LEFR, RIGHT, UP, DOWN, NULL
    }

    private enum LAND{
        HEIGHER, LOWER, EQUAL
    }

    private static geKeyBoard instance;

    public geKeyBoard() {
    }

    public static geKeyBoard getInstance(){
        if(instance == null){
            instance = new geKeyBoard();
        }
        return instance;
    }

    public void setObject(geCore core, String spriteName, String background){
        this.core = core;
        this.spriteName = spriteName;
        this.background = background;
        land_y = core.getSpriteByName(spriteName).getY() - core.getSpriteByName(spriteName).getHeight();
    }

    public void setUnit(float unit) {
        this.unit = unit;
    }

    public static float getUnit() {
        return unit;
    }

    public void setLand_y(float land_y) {
        this.land_y = land_y;
    }

    private LAND isLanded(){
        if(core.getSpriteByName(spriteName).getY() - core.getSpriteByName(spriteName).getHeight() > land_y){
            return LAND.HEIGHER;
        }else if(core.getSpriteByName(spriteName).getY() - core.getSpriteByName(spriteName).getHeight() < land_y){
            return LAND.LOWER;
        }else{
            return LAND.EQUAL;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        press(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /* only change the place of sprite */
    public void pressSprtie(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                this.core.getSpriteByName(this.spriteName).move(-unit,0);
                lastDirection = DIRECTION.LEFR;
                break;
            case KeyEvent.VK_RIGHT:
                this.core.getSpriteByName(this.spriteName).move(unit,0);
                lastDirection = DIRECTION.RIGHT;
                break;
            case KeyEvent.VK_UP:
                if(lastDirection == DIRECTION.LEFR){
                    this.core.getSpriteByName(this.spriteName).move(-unit,JUMP_HEIGHT);
                }else if(lastDirection == DIRECTION.RIGHT){
                    this.core.getSpriteByName(this.spriteName).move(unit,JUMP_HEIGHT);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(lastDirection == DIRECTION.LEFR && isLanded() == LAND.HEIGHER){
                    this.core.getSpriteByName(this.spriteName).move(-unit,-JUMP_HEIGHT);
                }else if(lastDirection == DIRECTION.RIGHT && isLanded() == LAND.HEIGHER){
                    this.core.getSpriteByName(this.spriteName).move(unit,-JUMP_HEIGHT);
                }else if(lastDirection == DIRECTION.LEFR && isLanded() == LAND.EQUAL){
                    this.core.getSpriteByName(this.spriteName).move(-unit,0f);
                }else if(lastDirection == DIRECTION.RIGHT && isLanded() == LAND.EQUAL){
                    this.core.getSpriteByName(this.spriteName).move(unit,0f);
                }
                break;
            default:
                break;
        }
    }

    /* change the place both sprite and background */
    public void press(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                this.core.getLayerByName(this.background).move(unit, 0f);
                lastDirection = DIRECTION.LEFR;
                getDown();
                break;
            case KeyEvent.VK_RIGHT:
                this.core.getLayerByName(this.background).move(-unit, 0f);
                lastDirection = DIRECTION.RIGHT;
                getDown();
                break;
            case KeyEvent.VK_UP:
                if(lastDirection == DIRECTION.LEFR){
                    this.core.getLayerByName(this.background).move(unit, 0f);
                    this.core.getSpriteByName(this.spriteName).move(0f, JUMP_HEIGHT);
                }else if(lastDirection == DIRECTION.RIGHT){
                    this.core.getLayerByName(this.background).move(-unit, 0f);
                    this.core.getSpriteByName(this.spriteName).move(0f, JUMP_HEIGHT);
                }
                break;
            case KeyEvent.VK_DOWN:

                break;
            default:
                break;
        }
    }

    private void getDown(){
        if(lastDirection == DIRECTION.LEFR && isLanded() == LAND.HEIGHER){
            this.core.getLayerByName(this.background).move(unit, 0f);
            this.core.getSpriteByName(this.spriteName).move(0f, -JUMP_HEIGHT);
        }else if(lastDirection == DIRECTION.RIGHT && isLanded() == LAND.HEIGHER){
            this.core.getLayerByName(this.background).move(-unit, 0f);
            this.core.getSpriteByName(this.spriteName).move(0f, -JUMP_HEIGHT);
        }else if(lastDirection == DIRECTION.LEFR && isLanded() == LAND.EQUAL){
            this.core.getLayerByName(this.background).move(unit, 0f);
        }else if(lastDirection == DIRECTION.RIGHT && isLanded() == LAND.EQUAL){
            this.core.getLayerByName(this.background).move(-unit, 0f);
        }
    }

}
