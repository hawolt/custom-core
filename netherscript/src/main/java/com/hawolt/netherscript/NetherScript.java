package com.hawolt.netherscript;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NetherScript<T extends NetherSource> {

    private static final String INTERNAL_WRAP = "<<!~INTERNAL~!>>";

    private final Map<String, NetherInstruction<T>> INSTRUCTION_MAP = new HashMap<>();
    private final Map<String, String> VARIABLE_MAP = new HashMap<>();

    public NetherScript() {
        add("var", (arguments, source) -> VARIABLE_MAP.getOrDefault(arguments[0], "5"));
    }

    public void add(String name, NetherInstruction<T> instruction) {
        this.INSTRUCTION_MAP.put(name, instruction);
    }

    public String parse(T source, String input) {
        try {
            return internal(source, input);
        } catch (Exception e) {
            return "ERROR_PROCESSING_EXPRESSION";
        }
    }

    private static String wrap(String content) {
        return INTERNAL_WRAP + content + INTERNAL_WRAP;
    }

    private String unwrap(String content) {
        if (content != null && content.startsWith(INTERNAL_WRAP) && content.endsWith(INTERNAL_WRAP)) {
            return content.substring(INTERNAL_WRAP.length(), content.length() - INTERNAL_WRAP.length());
        }
        return content;
    }

    private boolean isInsideInternal(int pos, String text) {
        int index = 0;
        while (true) {
            int start = text.indexOf(INTERNAL_WRAP, index);
            if (start == -1) break;
            int end = text.indexOf(INTERNAL_WRAP, start + INTERNAL_WRAP.length());
            if (end == -1) break;
            if (pos >= start && pos <= end + INTERNAL_WRAP.length()) {
                return true;
            }
            index = end + INTERNAL_WRAP.length();
        }
        return false;
    }

    private String internal(T source, String input) throws Exception {
        StringBuilder builder = new StringBuilder(input);

        while (true) {
            Stack<Integer> stack = new Stack<>();
            int start = -1, end = -1;
            for (int i = 0; i < builder.length(); i++) {
                if (builder.charAt(i) == '$' && i + 1 < builder.length() && builder.charAt(i + 1) == '(') {
                    if (!isInsideInternal(i, builder.toString())) {
                        stack.push(i);
                        i++;
                    }
                } else if (builder.charAt(i) == ')' && !stack.isEmpty()) {
                    start = stack.pop();
                    end = i;
                    break;
                }
            }

            if (start != -1 && end != -1) {
                String expression = builder.substring(start + 2, end).trim(); // Skip "$(" and ")"
                String[] parts = expression.split(" ");
                String command = parts[0];
                String[] marked = Arrays.copyOfRange(parts, 1, parts.length);
                String[] arguments = Arrays.stream(marked)
                        .map(this::unwrap)
                        .toArray(String[]::new);

                String result = "";
                if ("set".equals(command)) {
                    String value = String.join(" ", Arrays.copyOfRange(arguments, 1, arguments.length));
                    VARIABLE_MAP.put(arguments[0], wrap(value));
                } else if ("var".equals(command)) {
                    String value = VARIABLE_MAP.getOrDefault(arguments[0], "");
                    result = wrap(unwrap(value));
                } else if (INSTRUCTION_MAP.containsKey(command)) {
                    NetherInstruction<T> instruction = INSTRUCTION_MAP.get(command);
                    String output = instruction.manipulate(arguments, source);
                    result = wrap(output);
                } else {
                    result = "UNKNOWN_INSTRUCTION";
                }

                builder.replace(start, end + 1, result);
            } else {
                break;
            }
        }

        return builder.toString().replace(INTERNAL_WRAP, "").trim();
    }
}
