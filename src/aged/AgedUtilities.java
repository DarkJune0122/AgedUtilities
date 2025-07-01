package aged;

import aged.analysis.Analyzers;
import arc.Events;
import arc.util.Log;
import mindustry.game.EventType;
import mindustry.mod.Mod;

public class AgedUtilities extends Mod {
    public final String name = "aged-aged.utilities";

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Constructor
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    public AgedUtilities(){
        AgedLib.control = new Control();
        Control.init();
        Events.run(EventType.Trigger.drawOver, () -> {
            if (Analyzers.getCurrent() != null) Analyzers.getCurrent().draw();
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
    }
}