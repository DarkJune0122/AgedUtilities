package aged;

import arc.util.Log;
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
    }

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
    }
}