package com.bottega.function.L00;

import com.bottega.function.L00_generics.Pair;
import com.bottega.function.L00_generics.Tuple3;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PairTest {

    @Test
    public void twoPairsWithSameValuesAreEqual() {
        // given
        var p1 = new Pair<>("p1", 10L);
        var p2 = new Pair<String, Long>("p1", 10L);

        // expect
        assertThat(p1).isEqualTo(p2);
        assertThat(p2).isEqualTo(p1);
    }

    @Test
    public void canAccessPairElements() {
        // given
        var p = new Pair<>("p1", 10L);

        // expect
        assertThat(p.first()).isEqualTo("p1");
        assertThat(p.second()).isEqualTo(10L);
    }

    @Test
    public void canAccessTupleElements() {
        // given
        var p = new Tuple3<>("p1", 10L, BigDecimal.ZERO);

        // expect
        assertThat(p.t1()).isEqualTo("p1");
        assertThat(p.t2()).isEqualTo(10L);
        assertThat(p.t3()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void createsPairsUsingFactoryMethod() {
        // given
        var p1 = new Pair<>("p1", 10L);
        var p2 = Pair.of("p1", 10L);

        // expect
        assertThat(p1).isEqualTo(p2);
    }

}
