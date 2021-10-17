package org.test.bludbourne.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.test.bludbourne.asset.Utility;
import org.test.bludbourne.components.*;

import java.util.Arrays;

import static org.test.bludbourne.globals.AppConstants.FRAME_HEIGHT;
import static org.test.bludbourne.globals.AppConstants.FRAME_WIDTH;

@All({PlayerTagComponent.class,
        PositionComponent.class,
        VelocityComponent.class,
        WalkLeftAnimationComponent.class,
        WalkRightAnimationComponent.class,
        WalkUpAnimationComponent.class,
        WalkDownAnimationComponent.class,
        CurrentFrameComponent.class,
        KeyboardInputComponent.class,
        MouseInputComponent.class})
public class PlayerSetupSystem extends BaseEntitySystem {

    ComponentMapper<PositionComponent> positionComponentComponentMapper;
    ComponentMapper<VelocityComponent> velocityComponentComponentMapper;
    ComponentMapper<WalkLeftAnimationComponent> walkLeftAnimationComponentComponentMapper;
    ComponentMapper<WalkRightAnimationComponent> walkRightAnimationComponentComponentMapper;
    ComponentMapper<WalkUpAnimationComponent> walkUpAnimationComponentComponentMapper;
    ComponentMapper<WalkDownAnimationComponent> walkDownAnimationComponentComponentMapper;
    ComponentMapper<CurrentFrameComponent> currentFrameComponentComponentMapper;
    ComponentMapper<KeyboardInputComponent> keyboardInputComponentComponentMapper;
    ComponentMapper<MouseInputComponent> mouseInputComponentComponentMapper;

    @Override
    protected void processSystem() {
        Arrays.stream(getEntityIds()
                        .getData())
                .forEach(entityId -> {
                    System.out.printf("Player Setup System has started for entity id %d%n", entityId);

                    keyboardInputInitialState(entityId);
                    mouseInputInitialState(entityId);
                    positionComponentComponentMapper.get(entityId).position = new Vector2();
                    velocityComponentComponentMapper.get(entityId).velocity = new Vector2(2, 2);
                    currentFrameComponentComponentMapper.get(entityId).currentFrame = currentFrameInitialState();
                    animationInitialState(entityId);
                });
    }

    private void mouseInputInitialState(int entityId) {
        MouseInputComponent mouseInputComponent = mouseInputComponentComponentMapper.get(entityId);
        mouseInputComponent.select = false;
        mouseInputComponent.doAction = false;
    }

    private void keyboardInputInitialState(int entityId) {
        KeyboardInputComponent keyboardInputComponent = keyboardInputComponentComponentMapper.get(entityId);
        keyboardInputComponent.left = false;
        keyboardInputComponent.right = false;
        keyboardInputComponent.up = false;
        keyboardInputComponent.down = false;
        keyboardInputComponent.quit = false;
    }

    private TextureRegion currentFrameInitialState() {
        return textureFramesInitialState()[0][0];
    }

    private void animationInitialState(int entityId) {
        TextureRegion[][] textureFrames = textureFramesInitialState();
        Array<TextureRegion> walkLeftFrames = new Array<>(4);
        Array<TextureRegion> walkRightFrames = new Array<>(4);
        Array<TextureRegion> walkUpFrames = new Array<>(4);
        Array<TextureRegion> walkDownFrames = new Array<>(4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                TextureRegion region = textureFrames[i][j];
                switch (i) {
                    case 0 -> walkDownFrames.insert(j, region);
                    case 1 -> walkLeftFrames.insert(j, region);
                    case 2 -> walkRightFrames.insert(j, region);
                    case 3 -> walkUpFrames.insert(j, region);
                }

            }
        }
        walkLeftAnimationComponentComponentMapper.get(entityId).animation =
                new Animation<>(0.25f, walkLeftFrames, Animation.PlayMode.LOOP);

        walkRightAnimationComponentComponentMapper.get(entityId).animation =
                new Animation<>(0.25f, walkRightFrames, Animation.PlayMode.LOOP);

        walkUpAnimationComponentComponentMapper.get(entityId).animation =
                new Animation<>(0.25f, walkUpFrames, Animation.PlayMode.LOOP);

        walkDownAnimationComponentComponentMapper.get(entityId).animation =
                new Animation<>(0.25f, walkDownFrames, Animation.PlayMode.LOOP);
    }

    private TextureRegion[][] textureFramesInitialState() {
        Utility.loadTextureAsset("sprites/characters/Warrior.png");
        Texture texture = Utility.getTextureAsset("sprites/characters/Warrior.png");
        return TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
    }
}
