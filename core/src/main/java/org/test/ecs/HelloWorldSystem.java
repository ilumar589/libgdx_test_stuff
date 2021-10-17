package org.test.ecs;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

@All(Hello.class)
public class HelloWorldSystem extends IteratingSystem {

    protected ComponentMapper<Hello> helloComponentMapper;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void process(int entityId) {
        System.out.println(helloComponentMapper.get(entityId).message);
    }
}
