package aged.analysis;

public class Analyzers {
    public static SetBlockAnalyzer setBlock = new SetBlockAnalyzer();

    private static Analyzer current;

    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    //
    //                    Access
    //
    //  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==
    public static Analyzer getCurrent() { return current; }
    public static void set(Analyzer analyzer) {
        if (analyzer == current) return;
        if (current != null) current.reject();
        current = analyzer;
    }

    public static void remove(Analyzer analyzer) {
        if (analyzer == null) return;
        if (analyzer == current) {
            current = null;
        }
    }
}
