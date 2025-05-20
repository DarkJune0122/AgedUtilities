package aged;

import aged.analysis.Analyzers;
import arc.Core;
import arc.KeyBinds;
import arc.input.InputProcessor;
import arc.input.KeyCode;

public class Control implements InputProcessor {
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                 Initialization
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    private static boolean isInitialized;
    public Control() {
        Core.input.getInputMultiplexer().addProcessor(this);
    }

    public static void init()
    {
        if (isInitialized) return;
        isInitialized = true;

        // TODO: Make mod compatible with other mods by only extending bindings - not fully replacing them.
        // Modifies settings, so they contain new keybind.
        KeyBinds.KeyBind[] original = mindustry.input.Binding.values();
        KeyBinds.KeyBind[] additional = Binding.values();
        KeyBinds.KeyBind[] combined = new KeyBinds.KeyBind[original.length + additional.length];
        System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(additional, 0, combined, original.length, additional.length);
        Core.keybinds.setDefaults(combined);
    }


    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                   Processing
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    @Override
    public boolean keyDown(KeyCode keycode) {
        // Works with analyzers.
        if (keycode == Core.keybinds.get(Binding.analyzer_set_block).key) {
            if (Analyzers.getCurrent() == Analyzers.setBlock) {
                Analyzers.setBlock.reject();
            } else {
                Analyzers.setBlock.analyze();
            }
            return true;
        }

        if (keycode == Core.keybinds.get(Binding.approve).key && Analyzers.getCurrent() != null)
        {
            Analyzers.getCurrent().approve();
            return true;
        }

        return InputProcessor.super.keyDown(keycode);
    }
}
