package aged;

import arc.KeyBinds;
import arc.input.InputDevice;
import arc.input.KeyCode;

public enum Binding implements KeyBinds.KeyBind {
    //analyzer_base_key(KeyCode.controlRight, "Aged Utilities"),
    approve(KeyCode.l, "Aged Utilities"),
    analyzer_set_block(KeyCode.j)
    ;

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                   Variables
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    private final KeyBinds.KeybindValue defaultValue;
    private final String category;

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                 Constructors
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    Binding(KeyBinds.KeybindValue defaultValue, String category){
        this.defaultValue = defaultValue;
        this.category = category;
    }

    Binding(KeyBinds.KeybindValue defaultValue){
        this(defaultValue, null);
    }

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //               Implementations
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    @Override
    public KeyBinds.KeybindValue defaultValue(InputDevice.DeviceType type) {
        return defaultValue;
    }
    @Override
    public String category() {
        return category;
    }
}
