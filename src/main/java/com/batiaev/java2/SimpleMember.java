package com.batiaev.java2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleMember implements Member {
    private String name;
    private boolean result;

    public SimpleMember(String name) {
        this.name = name;
    }

    @Override
    public boolean getResult() {
        return result;
    }
}
