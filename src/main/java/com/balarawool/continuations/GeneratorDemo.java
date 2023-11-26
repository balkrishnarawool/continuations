package com.balarawool.continuations;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.util.function.Consumer;

public class GeneratorDemo {
    public static void main(String[] args) {
        record Data(String msg) { }

        var gen = new Generator<Data>(source -> {
            source.yield(new Data("A"));
            source.yield(new Data("B"));
            source.yield(new Data("C"));
            source.yield(new Data("D"));
        });

        while (gen.hasNext()) {
            var t = gen.next();
            System.out.println(t);
        }
    }

    public static final class Generator<T> {
        private final ContinuationScope scope;
        private final Continuation cont;
        private final Source source;

        public boolean hasNext() {
            return !cont.isDone();
        }

        public T next() {
            var t = source.get();
            cont.run();
            return t;
        }

        public class Source {
            private T value;

            private Source() { }

            public void yield(T t) {
                value = t;
                Continuation.yield(scope);
            }

            public T get() { return value; }
        }

        public Generator(Consumer<Source> consumer) {
            scope = new ContinuationScope("Generator");
            source = new Source();
            cont = new Continuation(scope, () -> { consumer.accept(source); });
            cont.run();
        }
    }
}
