package com.balarawool.continuations;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.util.function.Consumer;

public class GeneratorDemo {
    public static void main(String[] args) {
        var generator = new Generator<String>(source -> {
            source.yield("A");
            source.yield("B");
            source.yield("C");
        });

        while (generator.hasNext()) {
            System.out.println(generator.next());
            System.out.println("Do something");
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
            var t = source.getValue();
            cont.run();
            return t;
        }

        public class Source {
            private T value;

            public void yield(T t) {
                value = t;
                Continuation.yield(scope);
            }

            private T getValue() {
                return value;
            }
        }

        public Generator(Consumer<Source> consumer) {
            scope = new ContinuationScope("Generator");
            source = new Source();
            cont = new Continuation(scope, () -> { consumer.accept(source); });
            cont.run();
        }
    }
}
