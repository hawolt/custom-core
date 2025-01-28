package com.hawolt.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Startup {

    public static class Builder {
        private String[] mandatory = new String[0];
        private String[] optional = new String[0];

        public Builder mandatory(String... mandatory) {
            this.mandatory = append(this.mandatory, mandatory);
            return this;
        }

        public Builder optional(String... optional) {
            this.optional = append(this.optional, optional);
            return this;
        }

        private String[] append(String[] original, String[] add) {
            if (add == null || add.length == 0) {
                return original;
            }
            if (original == null || original.length == 0) {
                return add;
            }
            String[] result = Arrays.copyOf(original, original.length + add.length);
            System.arraycopy(add, 0, result, original.length, add.length);
            return result;
        }

        public ProgramConfig build(String[] args) {
            return parse(mandatory, optional, args);
        }
    }


    private static ProgramConfig parse(String[] args) throws BadArgumentException {
        return parse(new String[0], new String[0], args);
    }

    private static ProgramConfig parse(String[] mandatory, String[] optional, String[] args) throws BadArgumentException {
        ProgramConfig config = new ProgramConfig(mandatory, optional);

        for (int i = 0; i < args.length; i++) {
            String current = args[i];
            int index = current.lastIndexOf("-") + 1;
            String raw = current.substring(index);
            int next = i + 1;
            boolean available = next < args.length;
            if (available && !args[next].startsWith("-")) {
                String argument = args[++i];
                config.put(
                        raw,
                        argument.matches("\\d+") ?
                                Integer.parseInt(argument) :
                                argument
                );
            } else {
                config.put(raw, null);
            }
        }

        String[] arguments = Stream.concat(
                Stream.of(mandatory),
                Stream.of(optional)
        ).toArray(String[]::new);

        for (String key : arguments) {
            String minimal = getShortParameter(key, arguments);
            if (!config.has(minimal) && !config.has(key)) continue;
            config.link(minimal, key);
        }

        List<String> list = new ArrayList<>();
        for (String required : mandatory) {
            if (!config.has(required)) list.add(required);
        }

        if (!list.isEmpty()) {
            String missing = String.join(", ", list);
            throw new BadArgumentException(
                    "Missing the following mandatory arguments: " + missing + "." +
                            System.lineSeparator() +
                            System.lineSeparator() +
                            config.help()
            );
        }

        return config;
    }

    private static String getShortParameter(String parameter, String[] arguments) {
        int length = 1;
        String guess = parameter.substring(0, length);
        for (String required : arguments) {
            if (required.equals(parameter)) continue;
            while (required.startsWith(guess)) {
                if (length > parameter.length()) return parameter;
                guess = parameter.substring(0, ++length);
            }
        }
        return guess;
    }
}
