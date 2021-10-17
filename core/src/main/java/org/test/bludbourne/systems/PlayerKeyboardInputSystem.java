package org.test.bludbourne.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import org.test.bludbourne.components.KeyboardInputComponent;
import org.test.bludbourne.components.PlayerTagComponent;
import org.test.bludbourne.globals.AppConstants;

import java.util.Map;

@All({PlayerTagComponent.class,
        KeyboardInputComponent.class})
public class PlayerKeyboardInputSystem extends IteratingSystem {
    @Wire
    InputStateHolder inputStateHolder;
    ComponentMapper<KeyboardInputComponent> keyboardInputComponentComponentMapper;

    @Override
    protected void process(int entityId) {

        System.out.printf("PlayerKeyboardInputSystem has started for entity id %d%n", entityId);

        Map<AppConstants.Keys, Boolean> keysState = inputStateHolder.getKeys();
        KeyboardInputComponent keyboardInputComponent = keyboardInputComponentComponentMapper.get(entityId);
        keyboardInputComponent.left = keysState.get(AppConstants.Keys.LEFT);
        keyboardInputComponent.right = keysState.get(AppConstants.Keys.RIGHT);
        keyboardInputComponent.up = keysState.get(AppConstants.Keys.UP);
        keyboardInputComponent.down = keysState.get(AppConstants.Keys.DOWN);
        keyboardInputComponent.quit = keysState.get(AppConstants.Keys.QUIT);

        System.out.printf("PlayerKeyboardInputSystem has the following state %s", keysState);
    }
}
