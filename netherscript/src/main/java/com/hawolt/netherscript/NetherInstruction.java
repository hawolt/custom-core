package com.hawolt.netherscript;

public interface NetherInstruction<T extends NetherSource> {
    String manipulate(String[] arguments, T source) throws Exception;
}
