package com.hawolt.netherscript;

public interface NetherInstruction<T> {
    String manipulate(String[] arguments, T source) throws Exception;
}
