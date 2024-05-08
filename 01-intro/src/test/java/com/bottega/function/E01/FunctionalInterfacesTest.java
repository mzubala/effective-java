package com.bottega.function.E01;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Function;

import static com.bottega.function.E01.FunctionalInterfaces.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalInterfacesTest {

    @Test
    public void l1_constantSupplier() {
        assertThat(L1_constantSupplier().get()).isEqualTo("test");
    }

    @Test
    public void l2_evenPredicate() {
        var random = Double.valueOf(Math.random() * 1000).longValue();
        assertThat(L2_evenPredicate().test(2 * random)).isTrue();
        assertThat(L2_evenPredicate().test(2 * random + 1)).isFalse();
    }

    @Test
    public void l3_TriFunction() throws NoSuchMethodException {
        var klass = FunctionalInterfaces.L3_TriFunction.class;
        assertThat(klass).isInterface()
            .hasAnnotations(FunctionalInterface.class);
        assertThat(klass.getTypeParameters()).hasSize(4);
        var method = Arrays.stream(klass.getMethods()).filter(m -> m.getName().equals("apply")).findFirst().orElseThrow();
        assertThat(method.getParameterCount()).isEqualTo(3);
        assertThat(method.getGenericParameterTypes()).containsExactly(klass.getTypeParameters()[0], klass.getTypeParameters()[1], klass.getTypeParameters()[2]);
        assertThat(method.getGenericReturnType()).isEqualTo(klass.getTypeParameters()[3]);
    }

    @Test
    public void l4_absSqrt() {
        assertThat(L4_absSqrt().apply(-4.0)).isEqualTo(2.0);
        assertThat(L4_absSqrt().apply(4.0)).isEqualTo(2.0);
        assertThat(L4_absSqrt().apply(16.0)).isEqualTo(4.0);
    }

    @Test
    public void l5_printer() {
        var os = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(os, true)) {
            L5_printer(ps).accept("tEsT");
        }
        assertThat(os.toString()).isEqualTo("test\n");
    }

    @Test
    public void l6_userFactory() {
        assertThat(L6_userFactory().apply("John", "Doe")).isEqualTo(new User("John", "Doe"));
    }

    @Test
    public void l7_userNameSupplier() {
        assertThat(L7_userNameSupplier(new User("John", "Doe")).get()).isEqualTo("John Doe");
    }

    @Test
    public void l8_toConsumer() {
        // given
        var sb = new StringBuilder();
        Function<String, String> function = (s) -> {
            sb.append(s);
            return s;
        };

        // when
        var consumer = L8_toConsumer(function);
        consumer.accept("test value");

        // then
        assertThat(sb.toString()).isEqualTo("test value");
    }

    @Test
    public void l9_userNameGetter() {
        assertThat(L9_userNameGetter().apply(new User("John", "Doe"))).isEqualTo("John Doe");
    }

    @Test
    public void l10_lastNameAndFirstNameComparator() {
        // given
        var comparator = L10_lastNameAndFirstNameComparator();

        // expect
        assertThat(comparator.compare(new User("John", "Doe"), new User("John", "Doe"))).isEqualTo(0);
        assertThat(comparator.compare(new User("John", "Doe"), new User("John", "Some"))).isLessThan(0);
        assertThat(comparator.compare(new User("John", "Some"), new User("John", "Doe"))).isGreaterThan(0);
        assertThat(comparator.compare(new User("John", "Doe"), new User("Jane", "Doe"))).isGreaterThan(0);
        assertThat(comparator.compare(new User("Jane", "Doe"), new User("John", "Doe"))).isLessThan(0);
    }
}
