import com.hawolt.netherscript.NetherInstruction;

public class AddAllInstruction implements NetherInstruction<FooSource> {
    @Override
    public String manipulate(String[] arguments, FooSource source) {
        int base = 0;
        for (String argument : arguments) {
            base += Integer.parseInt(argument);
        }
        return String.join(":", String.valueOf(base), source.compute(arguments));
    }
}
