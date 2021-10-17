package org.test.bludbourne.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import org.test.bludbourne.components.MouseInputComponent;
import org.test.bludbourne.components.PlayerTagComponent;
import org.test.bludbourne.globals.AppConstants;

import java.util.Map;

@All({PlayerTagComponent.class,
        MouseInputComponent.class})
public class PlayerMouseInputSystem extends IteratingSystem {
    @Wire
    InputStateHolder inputStateHolder;
    ComponentMapper<MouseInputComponent> mouseInputComponentComponentMapper;

    @Override
    protected void process(int entityId) {
        System.out.printf("PlayerMouseInputSystem has started for entity id %d%n", entityId);

        Map<AppConstants.Mouse, Boolean> mouseButtonsState = inputStateHolder.getMouseButtons();
        MouseInputComponent mouseInputComponent = mouseInputComponentComponentMapper.get(entityId);
        mouseInputComponent.select = mouseButtonsState.get(AppConstants.Mouse.SELECT);
        mouseInputComponent.doAction = mouseButtonsState.get(AppConstants.Mouse.DO_ACTION);

        System.out.printf("PlayerMouseInputSystem has the following state %s", mouseButtonsState);
    }
}
