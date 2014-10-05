/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;

/**
 *
 * @author Zack
 */
public class StartScreen extends AbstractAppState implements ActionListener {

    private Main main;
    private AppStateManager asm;
    private BitmapText titleText, startText;

    public void onAction(String name, boolean isPressed, float tpf) {
        if (isPressed) {
            if (name.equals("Quit")) {
                quitGame();
            }
            if (name.equals("Start")) {
                Game g = new Game();
                asm.attach(g);
                asm.detach(this);
            }
        }
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        main = (Main) app;
        asm = stateManager;
        Main.clearJMonkey(main);
        
        // text
        BitmapFont bmf = main.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        titleText = new BitmapText(bmf);
        titleText.setSize(bmf.getCharSet().getRenderedSize() * 5);
        titleText.setColor(ColorRGBA.Red);
        titleText.setText("Surfaces");
        AppSettings s = main.getSettings();
        titleText.setLocalTranslation((s.getWidth() - titleText.getLineWidth()) / 2,
                (s.getHeight() + titleText.getLineHeight()) / 2, 0f);
        main.getGuiNode().attachChild(titleText);
        startText = new BitmapText(bmf);
        startText.setSize(bmf.getCharSet().getRenderedSize() * 2);
        startText.setColor(ColorRGBA.White);
        startText.setText("Press SPACE to start");
        startText.setLocalTranslation((s.getWidth() - startText.getLineWidth()) / 2,
                (s.getHeight() - s.getHeight() + startText.getLineHeight()), 0f);
        main.getGuiNode().attachChild(startText);

        // keys
        InputManager inputManager = main.getInputManager();
        inputManager.addMapping("Quit", new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addMapping("Start", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Quit", "Start");
    }
    
    public void quitGame() {
        System.exit(0);
    }
}
