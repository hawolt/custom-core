import com.hawolt.netherscript.NetherSource;

public class FooSource implements NetherSource {
    private final String source;

    public FooSource(String source) {
        this.source = source;
    }

    @Override
    public String compute(String[] args) {
        return String.join(":", this.source, String.valueOf(args.length));
    }
}
