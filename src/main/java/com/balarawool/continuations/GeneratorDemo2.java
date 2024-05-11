package com.balarawool.continuations;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.util.function.Consumer;

/**
 * This class is slight improvement over GeneratorDemo.
 * There the Generator proactively calculates the next value and stores it.
 * Here Generator only calculates the next value when it is asked for.
 */
public class GeneratorDemo2 {
    public static void main(String[] args) {
        // Define a Generator
        var gen = new Generator<String>(source -> {
            System.out.println("Complex calculation for A");
            source.yield("A");
            System.out.println("Complex calculation for B");
            source.yield("B");
            System.out.println("Complex calculation for C");
            source.yieldAndReturn("C");
        });

        // Use Generator
        while(gen.hasNext()) {
            System.out.println("Getting next value...");
            System.out.println(gen.next());
        }
    }

    public static class Generator<T> {
        private ContinuationScope scope;
        private Continuation cont;
        private Source source;

        public boolean hasNext() {
            return !cont.isDone();
        }

        public T next() {
            cont.run();
            return source.getValue();
        }

        public class Source {
            private T value;

            public void yield(T t) {
                value = t;
                Continuation.yield(scope);
            }

            public T getValue() {
                return value;
            }

            public void yieldAndReturn(T t) {
                value = t;
            }
        }

        public Generator(Consumer<Source> consumer) {
            scope = new ContinuationScope("Generator");
            source = new Source();
            cont = new Continuation(scope, () -> { consumer.accept(source);});
        }
    }
}
