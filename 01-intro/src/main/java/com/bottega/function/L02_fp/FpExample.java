package com.bottega.function.L02_fp;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class FpExample {

    public static void main(String[] args) {
        var i = 42;
        i = i + 1;

        var date = new Date();
        date.setTime(date.getTime() + 1);

        Instant now = Instant.now();
        Instant nowPlusSecond = now.plusSeconds(1);
        System.out.println(now);
        System.out.println(nowPlusSecond);

        User user = new User(Arrays.asList("apple", "orange"));

        List<String> userItems = user.getItems();
        Collections.sort(userItems);
        userItems.add("asdadsa");

        System.out.println(user.getItems());
    }
}
