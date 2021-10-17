package org.test.bludbourne.systems;

import com.artemis.Component;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import net.mostlyoriginal.api.Singleton;
import org.test.bludbourne.globals.AppConstants;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InputStateHolder extends Component implements InputProcessor {

    private final Map<AppConstants.Keys, Boolean> keys;
    private final Map<AppConstants.Mouse, Boolean> mouseButtons;
    private final Vector3 mouseCoordinates;

    public InputStateHolder() {
        keys = new HashMap<>(Map.of(AppConstants.Keys.LEFT, false,
                AppConstants.Keys.RIGHT, false,
                AppConstants.Keys.UP, false,
                AppConstants.Keys.DOWN, false,
                AppConstants.Keys.QUIT, false));

        mouseButtons = new HashMap<>(Map.of(AppConstants.Mouse.SELECT, false,
                AppConstants.Mouse.DO_ACTION, false));

        mouseCoordinates = new Vector3();
    }

    public Map<AppConstants.Keys, Boolean> getKeys() {
        return keys;
    }

    public Map<AppConstants.Mouse, Boolean> getMouseButtons() {
        return mouseButtons;
    }

    @Override
    public boolean keyDown(int keycode) {
        if( keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
            keys.put(AppConstants.Keys.LEFT, true);
        }
        if( keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
            keys.put(AppConstants.Keys.RIGHT, true);
        }
        if( keycode == Input.Keys.UP || keycode == Input.Keys.W){
            keys.put(AppConstants.Keys.UP, true);
        }
        if( keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
            keys.put(AppConstants.Keys.DOWN, true);
        }
        if( keycode == Input.Keys.Q){
            keys.put(AppConstants.Keys.QUIT, true);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if( keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
            keys.put(AppConstants.Keys.LEFT, false);
        }
        if( keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
            keys.put(AppConstants.Keys.RIGHT, false);
        }
        if( keycode == Input.Keys.UP || keycode == Input.Keys.W){
            keys.put(AppConstants.Keys.UP, false);
        }
        if( keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
            keys.put(AppConstants.Keys.DOWN, false);
        }
        if( keycode == Input.Keys.Q){
            keys.put(AppConstants.Keys.QUIT, false);
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT || button == Input.Buttons.RIGHT){
            mouseCoordinates.x = screenX;
            mouseCoordinates.y = screenY;
        }

        if( button == Input.Buttons.LEFT){
            mouseButtons.put(AppConstants.Mouse.SELECT, true);
        }
        if( button == Input.Buttons.RIGHT){
            mouseButtons.put(AppConstants.Mouse.DO_ACTION, true);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if( button == Input.Buttons.LEFT){
            mouseButtons.put(AppConstants.Mouse.SELECT, false);
        }
        if( button == Input.Buttons.RIGHT){
            mouseButtons.put(AppConstants.Mouse.DO_ACTION, false);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
