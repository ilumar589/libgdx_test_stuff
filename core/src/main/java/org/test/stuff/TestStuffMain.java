package org.test.stuff;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.injection.FieldResolver;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import org.test.bludbourne.components.*;
import org.test.bludbourne.systems.InputStateHolder;
import org.test.bludbourne.systems.PlayerKeyboardInputSystem;
import org.test.bludbourne.systems.PlayerMouseInputSystem;
import org.test.bludbourne.systems.PlayerSetupSystem;

import static com.artemis.WorldConfigurationBuilder.Priority.HIGHEST;
import static com.artemis.WorldConfigurationBuilder.Priority.NORMAL;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class TestStuffMain extends ApplicationAdapter {
    private MenuBar menuBar;
    private Stage stage;
    private World world;

    @Override
    public void create() {
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(HIGHEST, new PlayerSetupSystem())
                .with(NORMAL, new PlayerKeyboardInputSystem())
                .with(NORMAL, new PlayerMouseInputSystem())
                .build();

        // set up input processor
        InputStateHolder inputStateHolder = new InputStateHolder();
        Gdx.input.setInputProcessor(inputStateHolder);

        world = new World(setup
                .register(inputStateHolder));

        int player = world.create();
        EntityEdit playerEdit = world.edit(player);
        playerEdit.create(PlayerTagComponent.class);
        playerEdit.create(KeyboardInputComponent.class);
        playerEdit.create(MouseInputComponent.class);
        playerEdit.create(CurrentFrameComponent.class);
        playerEdit.create(PositionComponent.class);
        playerEdit.create(VelocityComponent.class);
        playerEdit.create(WalkDownAnimationComponent.class);
        playerEdit.create(WalkLeftAnimationComponent.class);
        playerEdit.create(WalkRightAnimationComponent.class);
        playerEdit.create(WalkUpAnimationComponent.class);

        PlayerSetupSystem playerSetupSystem = world.getSystem(PlayerSetupSystem.class);
        // run setup systems ony once and disable
        playerSetupSystem.process();
        playerSetupSystem.setEnabled(false);
//
//        VisUI.setSkipGdxVersionCheck(true);
//        VisUI.load(SkinScale.X1);
//
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//
//        Table root = new Table();
//        root.setFillParent(true);
//        stage.addActor(root);
//
//        menuBar = new MenuBar();
//        root.add(menuBar.getTable()).growX().row();
//        root.add().grow();
//
//        createMenus();
//
//        stage.addActor(new TestWindow());
    }

    private void createMenus() {
        Menu startTestMenu = new Menu("start test");
        Menu fileMenu = new Menu("file");
        Menu editMenu = new Menu("edit");

        startTestMenu.addItem(new MenuItem("listview", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(new TestListView());
            }
        }));

        startTestMenu.addItem(new MenuItem("tabbed pane", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(new TestTabbedPane());
            }
        }));

        startTestMenu.addItem(new MenuItem("collapsible", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(new TestCollapsible());
            }
        }));

        //creating dummy menu items for showcase
        fileMenu.addItem(new MenuItem("menuitem #1"));
        fileMenu.addItem(new MenuItem("menuitem #2").setShortcut("f1"));
        fileMenu.addItem(new MenuItem("menuitem #3").setShortcut("f2"));

        editMenu.addItem(new MenuItem("menuitem #4"));
        editMenu.addItem(new MenuItem("menuitem #5"));
        editMenu.addSeparator();
        editMenu.addItem(new MenuItem("menuitem #6"));
        editMenu.addItem(new MenuItem("menuitem #7"));

        menuBar.addMenu(startTestMenu);
        menuBar.addMenu(fileMenu);
        menuBar.addMenu(editMenu);
    }

    @Override
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        world.process();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        stage.dispose();
    }
}