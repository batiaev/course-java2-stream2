package com.batiaev.java2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiarMember implements Member {
    private String name;

    public LiarMember(String name) {
        this.name = name;
    }

    @Override
    public boolean getResult() {
        return true;
    }

    @Override
    public void setResult(boolean value) {
        // I don't care, I'll lie anyway
    }
}
