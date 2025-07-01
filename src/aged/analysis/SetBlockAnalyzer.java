package aged.analysis;

import aged.selection.Selectors;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.Colors;
import arc.math.Mathf;
import arc.util.Log;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import java.awt.*;

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
            builder.append("setblock block @");
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

    @Override
    public void draw() {
        Color init = Pal.redDust;
        Color end = Pal.techBlue;
        float iterator = 0;
        int length = Selectors.linear.selected.size;
        for (var block : Selectors.linear.selected)
        {
            iterator++;
            Drawf.square(block.x, block.y, Mathf.sqrt2 * block.block.size, lerp(init, end, iterator / length));
        }
    }
    Color lerp(Color a, Color b, float t)
    {
        float rt = 1 - t;
        return new Color(
                b.r * t + a.r * rt,
                b.g * t + a.g * rt,
                b.b * t + a.b * rt,
                b.a * t + a.a * rt
        );
    }
}
