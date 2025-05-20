package aged.analysis;

import aged.selection.Selectors;
import arc.Core;
import arc.util.Log;

public class SetBlockAnalyzer implements Analyzer {

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Access
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    @Override
    public void analyze() {
        Analyzers.set(this);
        Selectors.linear.start();
        Log.info("SetBlock started.");
    }

    @Override
    public void approve() {
        var blocks = Selectors.linear.selected;
        Log.info("Approved! Selected a total of " + blocks.size + " blocks.");
        StringBuilder builder = new StringBuilder();
        for (var build : blocks) {
            builder.append("setblock build @");
            builder.append(build.block().name);
            builder.append(' ');
            builder.append(build.tile.x);
            builder.append(' ');
            builder.append(build.tile.y);
            builder.append(" @");
            builder.append(build.team().name);
            builder.append(' ');
            builder.append(build.rotation);
            builder.append('\n');
        }

        // Removes last new line char.
        if (builder.length() > 0)
        {
            builder.deleteCharAt(builder.length() - 1);
        }

        Core.app.setClipboardText(builder.toString());
        reject();
    }

    @Override
    public void reject() {
        Selectors.linear.stop();
        Analyzers.remove(this);
        Log.info("SetBlock rejected.");
    }
}
