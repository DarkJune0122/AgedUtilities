package aged.selection;

import arc.ApplicationListener;
import arc.Core;
import arc.KeyBinds;
import arc.input.InputProcessor;
import arc.input.KeyCode;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Tile;

public class LinearSelection implements Selector, InputProcessor, ApplicationListener {
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                     Fields
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    public final Seq<Building> selected = new Seq<>();
    private boolean state;

    // Input vars
    private int lastX, lastY;
    private boolean isSelecting;


    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                     Access
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    @Override
    public KeyBinds.KeyBind binding() {
        return aged.Binding.analyzer_set_block;
    }

    public void start() {
        Core.app.addListener(this);
        Core.input.getInputMultiplexer().addProcessor(this);
        state = true;
    }

    @Override
    public void stop() {
        Core.app.removeListener(this);
        Core.input.getInputMultiplexer().removeProcessor(this);
        state = false;
        selected.clear();
    }

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Selecting
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    public void select(Tile tile) {
        if (tile != null && tile.build != null && !selected.contains(tile.build)) {
            if (Vars.state.isEditor()) selected.add(tile.build);
            else if (tile.getTeamID() == Vars.player.team().id) {
                // Maybe we can make it, so you can select a specific teams, rather than only your own?
                // Like select/deselect-only mode, but for teams.
                selected.add(tile.build);
            }
        }
    }

    public void select(Building build) {
        if (build != null && !selected.contains(build)) {
            if (Vars.state.isEditor()) selected.add(build);
            else if (build.team().id == Vars.player.team().id) {
                // Maybe we can make it, so you can select a specific teams, rather than only your own?
                // Like select/deselect-only mode, but for teams.
                selected.add(build);
            }
        }
    }

    private void deselect(Tile tile) {
        if (tile != null && tile.build != null)
            selected.remove(tile.build);
    }

    private void deselect(Building build) {
        if (build != null)
            selected.remove(build);
    }


    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                     Drawing
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    public void update() {
        for (var tile : selected) {
            Drawf.selected(tile, Pal.accent);
        }
    }


    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Controls
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, KeyCode button) {
        if (!state) return false;
        if (pointer == 0)
        {
            Vec2 pos = Core.input.mouseWorld();
            int x = lastX = (int)((pos.x + 4) / Vars.tilesize);
            int y = lastY = (int)((pos.y + 4) / Vars.tilesize);
            Log.info("original: " + pos);
            Log.info("x: " + x);
            Log.info("y: " + y);
            Building build = Vars.world.build(x, y);
            isSelecting = !selected.contains(build);
            if (isSelecting) {
                select(build);
            } else {
                deselect(build);
            }
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!state) return false;
        if (pointer == 0)
        {
            Vec2 pos = Core.input.mouseWorld();
            int x = (int)((pos.x + 4) / Vars.tilesize);
            int y = (int)((pos.y + 4) / Vars.tilesize);
            if (x != lastX || y != lastY) {
                Building build = Vars.world.build(x, y);
                if (isSelecting) {
                    select(build);
                } else {
                    deselect(build);
                }

                lastX = x;
                lastY = y;
            }
        }

        return true;
    }
}
