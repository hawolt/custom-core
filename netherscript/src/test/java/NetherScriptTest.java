import com.hawolt.netherscript.NetherScript;

public class NetherScriptTest {
    public static void main(String[] args) {
        NetherScript<FooSource> script = new NetherScript<>();
        String input = "$(set custom 7)$(add 1 2 $(var custom))";
        FooSource tmp = new FooSource("foo");
        String value;
        //value = script.parse(tmp, input);
        //System.out.println(value);
        script.add("add", new AddAllInstruction());
        value = script.parse(tmp, input);
        System.out.println(value);
    }
}
