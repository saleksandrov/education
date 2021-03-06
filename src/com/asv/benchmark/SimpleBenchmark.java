/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.asv.benchmark;

import com.asv.benchmark.cache.FastCache;
import com.asv.benchmark.cache.NewFastCache;
import com.asv.benchmark.cache.SynchronizedCache;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.ThreadLocalRandom;

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
//@OutputTimeUnit(TimeUnit.SECONDS)
public class SimpleBenchmark {

    static SynchronizedCache<Integer, Integer> cache1 = new SynchronizedCache<>();
    static FastCache<Integer, Integer> cache2 = new FastCache<>();
    static NewFastCache<Integer, Integer> cache3 = new NewFastCache<>();

    @Setup
    public static void setup() {
        for (int i = 0; i <= 10000; i++) {
            cache1.put(i, i);
            cache2.put(i, i);
            cache3.put(i, i);
        }
    }

    @Benchmark
    public void testReadWriteLockGet(Blackhole bh) {
        int toGet = ThreadLocalRandom.current().nextInt(100);
        int toPut = ThreadLocalRandom.current().nextInt(50000);
        //cache2.put(toPut, toPut);
        Integer integer = cache2.get(toGet, 5_000);
        bh.consume(integer);

    }

    @Benchmark
    public void testSynchMapGet(Blackhole bh) {
        int toGet = ThreadLocalRandom.current().nextInt(100);
        int toPut = ThreadLocalRandom.current().nextInt(50000);
        //cache1.put(toPut, toPut);
        Integer integer = cache1.get(toGet, 5_000);
        bh.consume(integer);
    }

    @Benchmark
    public void testStampedLockGet(Blackhole bh) {
        int toGet = ThreadLocalRandom.current().nextInt(100);
        int toPut = ThreadLocalRandom.current().nextInt(50000);
        //cache1.put(toPut, toPut);
        Integer integer = cache3.get2(toGet, 5_000);
        bh.consume(integer);
    }

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(SimpleBenchmark.class.getSimpleName())
                .forks(1)
                .threads(4)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .warmupIterations(5)
                .measurementIterations(20)
                .build();

        new Runner(options).run();

    }

}
