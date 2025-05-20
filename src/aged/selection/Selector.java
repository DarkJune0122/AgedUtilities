package aged.selection;

import arc.KeyBinds;

public interface Selector {
    KeyBinds.KeyBind binding();
    void start();
    void stop();
}
