package aged.export;

import arc.struct.Seq;

import java.util.HashSet;

public class TemplateWindow {
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Fields
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    private final HashSet<Object> requests = new HashSet<>();


    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Access
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    /** Returns a template, written by a user. */
    public String template() {
        return null;
    }

    public void request(Object requestor) {
        requests.add(requestor);
        show();
    }

    public void reject(Object requestor) {
        requests.remove(requestor);
        if (requests.isEmpty()) hide();
    }

    public void close() {
        requests.clear();
        hide();
    }

    private void show() {

    }

    private void hide() {

    }

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Drawing
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
}
