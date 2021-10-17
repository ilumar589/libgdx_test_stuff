package org.test.bludbourne.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WalkLeftAnimationComponent extends PooledComponent {

    public Animation<TextureRegion> animation;

    @Override
    protected void reset() {
        // reset animation to initial key frames
    }
}
