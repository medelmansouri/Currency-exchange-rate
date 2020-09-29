package com.chalenge.exchangerate.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Used instead of an Intent in order to pass arguments between fragments
 *
 * @author Mohamed EL MANSOURI
 */
public class FragmentCall {
    private String fragmentName;
    private Map<String, Object> arguments = new HashMap<>();

    public FragmentCall() {
    }

    public FragmentCall(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void putArgument(String key, Object value) {
        arguments.put(key, value);
    }
}