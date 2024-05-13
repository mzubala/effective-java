package com.bottega.function.E00;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoxTest {

    // given
    // setup system under test (sut)

    // when
    // interaction with the sut

    // then
    // check assertions regarding sut

    // -----------

    // expect

    @Test
    public void createsAnEmptyBox() {
        // expect
        assertThat(Box.create().isEmpty()).isTrue();
    }

    @Test
    public void createsANonEmptyBox() {
        // expect
        assertThat(Box.create("test").isEmpty()).isFalse();
    }

    @Test
    public void emptiesABox() {
        // given
        var box = Box.create("test");

        // when
        var element = box.empty();

        // then
        assertThat(element).isEqualTo("test");
        assertThat(box.isEmpty()).isTrue();
    }

    @Test
    public void cannotFillANonEmptyBox() {
        // given
        var box = Box.create("test");

        // expect
        assertThatThrownBy(() -> {
            box.put("something else");
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void anEmptyBoxCannotBeEmptied() {
        // given
        var box = Box.create();

        // expect
        assertThatThrownBy(() -> {
            box.empty();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void fillsAndEmptiesABoxMultipleTimes() {
        // given
        var box = Box.<String>create();

        // when
        box.put("first");
        box.empty();
        box.put("second");
        var element = box.empty();

        // then
        assertThat(element).isEqualTo("second");
    }
}
