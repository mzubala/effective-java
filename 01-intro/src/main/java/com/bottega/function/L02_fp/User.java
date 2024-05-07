package com.bottega.function.L02_fp;

import java.util.ArrayList;
import java.util.List;

class User {

    private final List<String> items;

    User(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    List<String> getItems() {
        return new ArrayList<>(items);
    }
}
