# loops-jmh-playground with GC profiling enabled

Software versions are the latest available at present (2017.04.05, YYYY.MM.DD). Also Oracle JDK - not OpenJDK. Also Linux OS.

## JMH 1.18 (released 23 days ago)
## VM version: JDK 1.8.0_121, VM 25.121-b13
## VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java

The results summary:

```
# Run complete. Total time: 00:04:45

Benchmark                                                        Mode  Cnt     Score      Error   Units
LoopBenchmarkMain.forEachLambdaMaxInteger                        avgt   10     0.534 ±    0.017   ms/op
LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.alloc.rate         avgt   10    ≈ 10⁻⁴             MB/sec
LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.alloc.rate.norm    avgt   10     0.230 ±    0.007    B/op
LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.count              avgt   10       ≈ 0             counts
LoopBenchmarkMain.forEachLoopMaxInteger                          avgt   10     0.602 ±    0.019   ms/op
LoopBenchmarkMain.forEachLoopMaxInteger:·gc.alloc.rate           avgt   10     0.034 ±    0.001  MB/sec
LoopBenchmarkMain.forEachLoopMaxInteger:·gc.alloc.rate.norm      avgt   10    32.258 ±    0.007    B/op
LoopBenchmarkMain.forEachLoopMaxInteger:·gc.count                avgt   10       ≈ 0             counts
LoopBenchmarkMain.forMax2Integer                                 avgt   10     0.540 ±    0.024   ms/op
LoopBenchmarkMain.forMax2Integer:·gc.alloc.rate                  avgt   10    ≈ 10⁻⁴             MB/sec
LoopBenchmarkMain.forMax2Integer:·gc.alloc.rate.norm             avgt   10     0.232 ±    0.011    B/op
LoopBenchmarkMain.forMax2Integer:·gc.count                       avgt   10       ≈ 0             counts
LoopBenchmarkMain.forMaxInteger                                  avgt   10     0.522 ±    0.018   ms/op
LoopBenchmarkMain.forMaxInteger:·gc.alloc.rate                   avgt   10    ≈ 10⁻⁴             MB/sec
LoopBenchmarkMain.forMaxInteger:·gc.alloc.rate.norm              avgt   10     0.224 ±    0.008    B/op
LoopBenchmarkMain.forMaxInteger:·gc.count                        avgt   10       ≈ 0             counts
LoopBenchmarkMain.iteratorMaxInteger                             avgt   10     0.603 ±    0.013   ms/op
LoopBenchmarkMain.iteratorMaxInteger:·gc.alloc.rate              avgt   10     0.034 ±    0.001  MB/sec
LoopBenchmarkMain.iteratorMaxInteger:·gc.alloc.rate.norm         avgt   10    32.260 ±    0.005    B/op
LoopBenchmarkMain.iteratorMaxInteger:·gc.count                   avgt   10       ≈ 0             counts
LoopBenchmarkMain.lambdaMaxInteger                               avgt   10     2.373 ±    0.106   ms/op
LoopBenchmarkMain.lambdaMaxInteger:·gc.alloc.rate                avgt   10     0.058 ±    0.003  MB/sec
LoopBenchmarkMain.lambdaMaxInteger:·gc.alloc.rate.norm           avgt   10   217.064 ±    0.199    B/op
LoopBenchmarkMain.lambdaMaxInteger:·gc.count                     avgt   10       ≈ 0             counts
LoopBenchmarkMain.parallelStreamMaxInteger                       avgt   10     1.716 ±    0.179   ms/op
LoopBenchmarkMain.parallelStreamMaxInteger:·gc.alloc.rate        avgt   10     0.324 ±    0.034  MB/sec
LoopBenchmarkMain.parallelStreamMaxInteger:·gc.alloc.rate.norm   avgt   10   872.813 ±    0.283    B/op
LoopBenchmarkMain.parallelStreamMaxInteger:·gc.count             avgt   10       ≈ 0             counts
LoopBenchmarkMain.streamMaxInteger                               avgt   10     0.621 ±    0.058   ms/op
LoopBenchmarkMain.streamMaxInteger:·gc.alloc.rate                avgt   10     0.222 ±    0.020  MB/sec
LoopBenchmarkMain.streamMaxInteger:·gc.alloc.rate.norm           avgt   10   216.267 ±    0.025    B/op
LoopBenchmarkMain.streamMaxInteger:·gc.churn.PS_Eden_Space       avgt   10     1.590 ±    5.068  MB/sec
LoopBenchmarkMain.streamMaxInteger:·gc.churn.PS_Eden_Space.norm  avgt   10  1636.037 ± 5227.739    B/op
LoopBenchmarkMain.streamMaxInteger:·gc.count                     avgt   10     2.000             counts
LoopBenchmarkMain.streamMaxInteger:·gc.time                      avgt   10    40.000                 ms
```

The metrics without `:` is the base. `milliseconds per operation` - the operation is finding max value in ArrayList. The metrics with `:gc.alloc.rate` give the total allocation rate in MB/sec for the run, `:gc.alloc.rate.norm` gives the normalised per operation, `:gc.churn.*` is the total and normalised GC effort, `gc.count` how many GC times during the run and `gc.time` how much time took the GC.

Good introduction for this here: http://hg.openjdk.java.net/code-tools/jmh/file/36a2ee9a075e/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_35_Profilers.java#l169 Read carefully the table and the comments below.

# The full console log with GC profiling enabled

```
vagrant@tfb-all:~/loops-jmh-playground$ java -jar target/benchmarks.jar -prof gc
# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLambdaMaxInteger

# Run progress: 0.00% complete, ETA 00:02:40
# Fork: 1 of 2
# Warmup Iteration   1: 0.634 ms/op
# Warmup Iteration   2: 0.602 ms/op
# Warmup Iteration   3: 0.586 ms/op
# Warmup Iteration   4: 0.574 ms/op
# Warmup Iteration   5: 0.548 ms/op
Iteration   1: 0.525 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.225 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.523 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.225 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.520 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.224 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.536 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.230 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.525 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.227 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 6.25% complete, ETA 00:04:26
# Fork: 2 of 2
# Warmup Iteration   1: 0.652 ms/op
# Warmup Iteration   2: 0.609 ms/op
# Warmup Iteration   3: 0.592 ms/op
# Warmup Iteration   4: 0.562 ms/op
# Warmup Iteration   5: 0.555 ms/op
Iteration   1: 0.553 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.236 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.543 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.234 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.549 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.237 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.534 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.229 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.534 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.229 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLambdaMaxInteger":
  0.534 ±(99.9%) 0.017 ms/op [Average]
  (min, avg, max) = (0.520, 0.534, 0.553), stdev = 0.011
  CI (99.9%): [0.517, 0.551] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.alloc.rate":
  ≈ 10⁻⁴ MB/sec

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.alloc.rate.norm":
  0.230 ±(99.9%) 0.007 B/op [Average]
  (min, avg, max) = (0.224, 0.230, 0.237), stdev = 0.005
  CI (99.9%): [0.223, 0.237] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLoopMaxInteger

# Run progress: 12.50% complete, ETA 00:04:07
# Fork: 1 of 2
# Warmup Iteration   1: 0.625 ms/op
# Warmup Iteration   2: 0.597 ms/op
# Warmup Iteration   3: 0.589 ms/op
# Warmup Iteration   4: 0.601 ms/op
# Warmup Iteration   5: 0.605 ms/op
Iteration   1: 0.616 ms/op
                 ·gc.alloc.rate:      0.033 MB/sec
                 ·gc.alloc.rate.norm: 32.264 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.591 ms/op
                 ·gc.alloc.rate:      0.035 MB/sec
                 ·gc.alloc.rate.norm: 32.253 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.588 ms/op
                 ·gc.alloc.rate:      0.035 MB/sec
                 ·gc.alloc.rate.norm: 32.253 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.597 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.257 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.587 ms/op
                 ·gc.alloc.rate:      0.035 MB/sec
                 ·gc.alloc.rate.norm: 32.253 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 18.75% complete, ETA 00:03:53
# Fork: 2 of 2
# Warmup Iteration   1: 0.630 ms/op
# Warmup Iteration   2: 0.607 ms/op
# Warmup Iteration   3: 0.633 ms/op
# Warmup Iteration   4: 0.616 ms/op
# Warmup Iteration   5: 0.637 ms/op
Iteration   1: 0.600 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.256 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.597 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.258 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.625 ms/op
                 ·gc.alloc.rate:      0.033 MB/sec
                 ·gc.alloc.rate.norm: 32.267 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.604 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.259 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.610 ms/op
                 ·gc.alloc.rate:      0.033 MB/sec
                 ·gc.alloc.rate.norm: 32.261 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLoopMaxInteger":
  0.602 ±(99.9%) 0.019 ms/op [Average]
  (min, avg, max) = (0.587, 0.602, 0.625), stdev = 0.012
  CI (99.9%): [0.583, 0.620] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLoopMaxInteger:·gc.alloc.rate":
  0.034 ±(99.9%) 0.001 MB/sec [Average]
  (min, avg, max) = (0.033, 0.034, 0.035), stdev = 0.001
  CI (99.9%): [0.033, 0.035] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLoopMaxInteger:·gc.alloc.rate.norm":
  32.258 ±(99.9%) 0.007 B/op [Average]
  (min, avg, max) = (32.253, 32.258, 32.267), stdev = 0.005
  CI (99.9%): [32.251, 32.266] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forEachLoopMaxInteger:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMax2Integer

# Run progress: 25.00% complete, ETA 00:03:33
# Fork: 1 of 2
# Warmup Iteration   1: 0.531 ms/op
# Warmup Iteration   2: 0.539 ms/op
# Warmup Iteration   3: 0.527 ms/op
# Warmup Iteration   4: 0.518 ms/op
# Warmup Iteration   5: 0.528 ms/op
Iteration   1: 0.509 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.219 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.534 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.229 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.545 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.235 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.538 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.231 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.525 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.224 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 31.25% complete, ETA 00:03:16
# Fork: 2 of 2
# Warmup Iteration   1: 0.576 ms/op
# Warmup Iteration   2: 0.568 ms/op
# Warmup Iteration   3: 0.523 ms/op
# Warmup Iteration   4: 0.522 ms/op
# Warmup Iteration   5: 0.524 ms/op
Iteration   1: 0.543 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.231 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.561 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.241 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.557 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.240 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.553 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.238 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.531 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.229 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMax2Integer":
  0.540 ±(99.9%) 0.024 ms/op [Average]
  (min, avg, max) = (0.509, 0.540, 0.561), stdev = 0.016
  CI (99.9%): [0.516, 0.563] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMax2Integer:·gc.alloc.rate":
  ≈ 10⁻⁴ MB/sec

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMax2Integer:·gc.alloc.rate.norm":
  0.232 ±(99.9%) 0.011 B/op [Average]
  (min, avg, max) = (0.219, 0.232, 0.241), stdev = 0.007
  CI (99.9%): [0.221, 0.242] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMax2Integer:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMaxInteger

# Run progress: 37.50% complete, ETA 00:02:58
# Fork: 1 of 2
# Warmup Iteration   1: 0.556 ms/op
# Warmup Iteration   2: 0.532 ms/op
# Warmup Iteration   3: 0.524 ms/op
# Warmup Iteration   4: 0.532 ms/op
# Warmup Iteration   5: 0.525 ms/op
Iteration   1: 0.544 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.233 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.527 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.227 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.536 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.231 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.515 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.221 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.511 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.220 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 43.75% complete, ETA 00:02:41
# Fork: 2 of 2
# Warmup Iteration   1: 0.556 ms/op
# Warmup Iteration   2: 0.539 ms/op
# Warmup Iteration   3: 0.540 ms/op
# Warmup Iteration   4: 0.531 ms/op
# Warmup Iteration   5: 0.511 ms/op
Iteration   1: 0.507 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.218 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.514 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.220 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.525 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.226 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.513 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.220 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.524 ms/op
                 ·gc.alloc.rate:      ≈ 10⁻⁴ MB/sec
                 ·gc.alloc.rate.norm: 0.224 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMaxInteger":
  0.522 ±(99.9%) 0.018 ms/op [Average]
  (min, avg, max) = (0.507, 0.522, 0.544), stdev = 0.012
  CI (99.9%): [0.504, 0.539] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMaxInteger:·gc.alloc.rate":
  ≈ 10⁻⁴ MB/sec

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMaxInteger:·gc.alloc.rate.norm":
  0.224 ±(99.9%) 0.008 B/op [Average]
  (min, avg, max) = (0.218, 0.224, 0.233), stdev = 0.005
  CI (99.9%): [0.216, 0.232] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.forMaxInteger:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.iteratorMaxInteger

# Run progress: 50.00% complete, ETA 00:02:22
# Fork: 1 of 2
# Warmup Iteration   1: 0.682 ms/op
# Warmup Iteration   2: 0.623 ms/op
# Warmup Iteration   3: 0.608 ms/op
# Warmup Iteration   4: 0.610 ms/op
# Warmup Iteration   5: 0.609 ms/op
Iteration   1: 0.612 ms/op
                 ·gc.alloc.rate:      0.033 MB/sec
                 ·gc.alloc.rate.norm: 32.263 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.609 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.261 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.605 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.260 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.603 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.259 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.605 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.261 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 56.25% complete, ETA 00:02:05
# Fork: 2 of 2
# Warmup Iteration   1: 0.646 ms/op
# Warmup Iteration   2: 0.609 ms/op
# Warmup Iteration   3: 0.610 ms/op
# Warmup Iteration   4: 0.610 ms/op
# Warmup Iteration   5: 0.602 ms/op
Iteration   1: 0.590 ms/op
                 ·gc.alloc.rate:      0.035 MB/sec
                 ·gc.alloc.rate.norm: 32.255 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.588 ms/op
                 ·gc.alloc.rate:      0.035 MB/sec
                 ·gc.alloc.rate.norm: 32.254 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.600 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.257 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.616 ms/op
                 ·gc.alloc.rate:      0.033 MB/sec
                 ·gc.alloc.rate.norm: 32.265 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.604 ms/op
                 ·gc.alloc.rate:      0.034 MB/sec
                 ·gc.alloc.rate.norm: 32.260 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.iteratorMaxInteger":
  0.603 ±(99.9%) 0.013 ms/op [Average]
  (min, avg, max) = (0.588, 0.603, 0.616), stdev = 0.009
  CI (99.9%): [0.590, 0.617] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.iteratorMaxInteger:·gc.alloc.rate":
  0.034 ±(99.9%) 0.001 MB/sec [Average]
  (min, avg, max) = (0.033, 0.034, 0.035), stdev = 0.001
  CI (99.9%): [0.033, 0.035] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.iteratorMaxInteger:·gc.alloc.rate.norm":
  32.260 ±(99.9%) 0.005 B/op [Average]
  (min, avg, max) = (32.254, 32.260, 32.265), stdev = 0.004
  CI (99.9%): [32.254, 32.265] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.iteratorMaxInteger:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.lambdaMaxInteger

# Run progress: 62.50% complete, ETA 00:01:47
# Fork: 1 of 2
# Warmup Iteration   1: 0.700 ms/op
# Warmup Iteration   2: 0.671 ms/op
# Warmup Iteration   3: 0.591 ms/op
# Warmup Iteration   4: 0.946 ms/op
# Warmup Iteration   5: 2.451 ms/op
Iteration   1: 2.458 ms/op
                 ·gc.alloc.rate:      0.056 MB/sec
                 ·gc.alloc.rate.norm: 217.056 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 2.470 ms/op
                 ·gc.alloc.rate:      0.056 MB/sec
                 ·gc.alloc.rate.norm: 217.064 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 2.418 ms/op
                 ·gc.alloc.rate:      0.057 MB/sec
                 ·gc.alloc.rate.norm: 217.041 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 2.422 ms/op
                 ·gc.alloc.rate:      0.057 MB/sec
                 ·gc.alloc.rate.norm: 217.043 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 2.398 ms/op
                 ·gc.alloc.rate:      0.057 MB/sec
                 ·gc.alloc.rate.norm: 217.031 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 68.75% complete, ETA 00:01:29
# Fork: 2 of 2
# Warmup Iteration   1: 0.707 ms/op
# Warmup Iteration   2: 0.625 ms/op
# Warmup Iteration   3: 0.604 ms/op
# Warmup Iteration   4: 0.956 ms/op
# Warmup Iteration   5: 2.298 ms/op
Iteration   1: 2.280 ms/op
                 ·gc.alloc.rate:      0.061 MB/sec
                 ·gc.alloc.rate.norm: 217.430 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 2.278 ms/op
                 ·gc.alloc.rate:      0.061 MB/sec
                 ·gc.alloc.rate.norm: 216.975 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 2.343 ms/op
                 ·gc.alloc.rate:      0.059 MB/sec
                 ·gc.alloc.rate.norm: 217.005 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 2.339 ms/op
                 ·gc.alloc.rate:      0.059 MB/sec
                 ·gc.alloc.rate.norm: 217.000 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 2.319 ms/op
                 ·gc.alloc.rate:      0.059 MB/sec
                 ·gc.alloc.rate.norm: 216.993 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.lambdaMaxInteger":
  2.373 ±(99.9%) 0.106 ms/op [Average]
  (min, avg, max) = (2.278, 2.373, 2.470), stdev = 0.070
  CI (99.9%): [2.266, 2.479] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.lambdaMaxInteger:·gc.alloc.rate":
  0.058 ±(99.9%) 0.003 MB/sec [Average]
  (min, avg, max) = (0.056, 0.058, 0.061), stdev = 0.002
  CI (99.9%): [0.055, 0.061] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.lambdaMaxInteger:·gc.alloc.rate.norm":
  217.064 ±(99.9%) 0.199 B/op [Average]
  (min, avg, max) = (216.975, 217.064, 217.430), stdev = 0.132
  CI (99.9%): [216.864, 217.263] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.lambdaMaxInteger:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.parallelStreamMaxInteger

# Run progress: 75.00% complete, ETA 00:01:11
# Fork: 1 of 2
# Warmup Iteration   1: 1.105 ms/op
# Warmup Iteration   2: 1.121 ms/op
# Warmup Iteration   3: 1.657 ms/op
# Warmup Iteration   4: 1.799 ms/op
# Warmup Iteration   5: 1.688 ms/op
Iteration   1: 1.692 ms/op
                 ·gc.alloc.rate:      0.328 MB/sec
                 ·gc.alloc.rate.norm: 872.727 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 1.621 ms/op
                 ·gc.alloc.rate:      0.342 MB/sec
                 ·gc.alloc.rate.norm: 872.699 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 1.758 ms/op
                 ·gc.alloc.rate:      0.315 MB/sec
                 ·gc.alloc.rate.norm: 872.759 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 1.886 ms/op
                 ·gc.alloc.rate:      0.293 MB/sec
                 ·gc.alloc.rate.norm: 873.190 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 1.733 ms/op
                 ·gc.alloc.rate:      0.318 MB/sec
                 ·gc.alloc.rate.norm: 872.745 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 81.25% complete, ETA 00:00:53
# Fork: 2 of 2
# Warmup Iteration   1: 1.064 ms/op
# Warmup Iteration   2: 1.111 ms/op
# Warmup Iteration   3: 1.539 ms/op
# Warmup Iteration   4: 1.526 ms/op
# Warmup Iteration   5: 1.639 ms/op
Iteration   1: 1.888 ms/op
                 ·gc.alloc.rate:      0.294 MB/sec
                 ·gc.alloc.rate.norm: 872.812 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 1.576 ms/op
                 ·gc.alloc.rate:      0.351 MB/sec
                 ·gc.alloc.rate.norm: 872.678 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 1.681 ms/op
                 ·gc.alloc.rate:      0.330 MB/sec
                 ·gc.alloc.rate.norm: 872.725 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 1.784 ms/op
                 ·gc.alloc.rate:      0.309 MB/sec
                 ·gc.alloc.rate.norm: 873.127 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 1.540 ms/op
                 ·gc.alloc.rate:      0.360 MB/sec
                 ·gc.alloc.rate.norm: 872.665 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.parallelStreamMaxInteger":
  1.716 ±(99.9%) 0.179 ms/op [Average]
  (min, avg, max) = (1.540, 1.716, 1.888), stdev = 0.118
  CI (99.9%): [1.537, 1.895] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.parallelStreamMaxInteger:·gc.alloc.rate":
  0.324 ±(99.9%) 0.034 MB/sec [Average]
  (min, avg, max) = (0.293, 0.324, 0.360), stdev = 0.022
  CI (99.9%): [0.290, 0.358] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.parallelStreamMaxInteger:·gc.alloc.rate.norm":
  872.813 ±(99.9%) 0.283 B/op [Average]
  (min, avg, max) = (872.665, 872.813, 873.190), stdev = 0.188
  CI (99.9%): [872.529, 873.096] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.parallelStreamMaxInteger:·gc.count":
  ≈ 0 counts


# JMH 1.18 (released 23 days ago)
# VM version: JDK 1.8.0_121, VM 25.121-b13
# VM invoker: /usr/lib/jvm/java-8-oracle/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger

# Run progress: 87.50% complete, ETA 00:00:35
# Fork: 1 of 2
# Warmup Iteration   1: 0.733 ms/op
# Warmup Iteration   2: 0.605 ms/op
# Warmup Iteration   3: 0.609 ms/op
# Warmup Iteration   4: 0.598 ms/op
# Warmup Iteration   5: 0.577 ms/op
Iteration   1: 0.584 ms/op
                 ·gc.alloc.rate:      0.235 MB/sec
                 ·gc.alloc.rate.norm: 216.251 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.618 ms/op
                 ·gc.alloc.rate:      0.223 MB/sec
                 ·gc.alloc.rate.norm: 216.265 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.614 ms/op
                 ·gc.alloc.rate:      0.224 MB/sec
                 ·gc.alloc.rate.norm: 216.263 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   4: 0.693 ms/op
                 ·gc.alloc.rate:               0.197 MB/sec
                 ·gc.alloc.rate.norm:          216.299 B/op
                 ·gc.churn.PS_Eden_Space:      7.936 MB/sec
                 ·gc.churn.PS_Eden_Space.norm: 8701.876 B/op
                 ·gc.count:                    1.000 counts
                 ·gc.time:                     20.000 ms

Iteration   5: 0.627 ms/op
                 ·gc.alloc.rate:      0.220 MB/sec
                 ·gc.alloc.rate.norm: 216.268 B/op
                 ·gc.count:           ≈ 0 counts


# Run progress: 93.75% complete, ETA 00:00:17
# Fork: 2 of 2
# Warmup Iteration   1: 0.699 ms/op
# Warmup Iteration   2: 0.608 ms/op
# Warmup Iteration   3: 0.604 ms/op
# Warmup Iteration   4: 0.594 ms/op
# Warmup Iteration   5: 0.580 ms/op
Iteration   1: 0.589 ms/op
                 ·gc.alloc.rate:      0.234 MB/sec
                 ·gc.alloc.rate.norm: 216.252 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   2: 0.568 ms/op
                 ·gc.alloc.rate:      0.242 MB/sec
                 ·gc.alloc.rate.norm: 216.245 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   3: 0.610 ms/op
                 ·gc.alloc.rate:               0.225 MB/sec
                 ·gc.alloc.rate.norm:          216.263 B/op
                 ·gc.churn.PS_Eden_Space:      7.965 MB/sec
                 ·gc.churn.PS_Eden_Space.norm: 7658.498 B/op
                 ·gc.count:                    1.000 counts
                 ·gc.time:                     20.000 ms

Iteration   4: 0.663 ms/op
                 ·gc.alloc.rate:      0.208 MB/sec
                 ·gc.alloc.rate.norm: 216.285 B/op
                 ·gc.count:           ≈ 0 counts

Iteration   5: 0.650 ms/op
                 ·gc.alloc.rate:      0.212 MB/sec
                 ·gc.alloc.rate.norm: 216.279 B/op
                 ·gc.count:           ≈ 0 counts



Result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger":
  0.621 ±(99.9%) 0.058 ms/op [Average]
  (min, avg, max) = (0.568, 0.621, 0.693), stdev = 0.038
  CI (99.9%): [0.564, 0.679] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger:·gc.alloc.rate":
  0.222 ±(99.9%) 0.020 MB/sec [Average]
  (min, avg, max) = (0.197, 0.222, 0.242), stdev = 0.014
  CI (99.9%): [0.201, 0.242] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger:·gc.alloc.rate.norm":
  216.267 ±(99.9%) 0.025 B/op [Average]
  (min, avg, max) = (216.245, 216.267, 216.299), stdev = 0.017
  CI (99.9%): [216.242, 216.292] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger:·gc.churn.PS_Eden_Space":
  1.590 ±(99.9%) 5.068 MB/sec [Average]
  (min, avg, max) = (≈ 0, 1.590, 7.965), stdev = 3.352
  CI (99.9%): [≈ 0, 6.658] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger:·gc.churn.PS_Eden_Space.norm":
  1636.037 ±(99.9%) 5227.739 B/op [Average]
  (min, avg, max) = (≈ 0, 1636.037, 8701.876), stdev = 3457.826
  CI (99.9%): [≈ 0, 6863.777] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger:·gc.count":
  2.000 ±(99.9%) 0.001 counts [Sum]
  (min, avg, max) = (≈ 0, 0.200, 1.000), stdev = 0.422
  CI (99.9%): [2.000, 2.000] (assumes normal distribution)

Secondary result "com.takipi.oss.benchmarks.jmh.loops.LoopBenchmarkMain.streamMaxInteger:·gc.time":
  40.000 ±(99.9%) 0.001 ms [Sum]
  (min, avg, max) = (≈ 0, 4.000, 20.000), stdev = 8.433
  CI (99.9%): [40.000, 40.000] (assumes normal distribution)


# Run complete. Total time: 00:04:45

Benchmark                                                        Mode  Cnt     Score      Error   Units
LoopBenchmarkMain.forEachLambdaMaxInteger                        avgt   10     0.534 ±    0.017   ms/op
LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.alloc.rate         avgt   10    ≈ 10⁻⁴             MB/sec
LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.alloc.rate.norm    avgt   10     0.230 ±    0.007    B/op
LoopBenchmarkMain.forEachLambdaMaxInteger:·gc.count              avgt   10       ≈ 0             counts
LoopBenchmarkMain.forEachLoopMaxInteger                          avgt   10     0.602 ±    0.019   ms/op
LoopBenchmarkMain.forEachLoopMaxInteger:·gc.alloc.rate           avgt   10     0.034 ±    0.001  MB/sec
LoopBenchmarkMain.forEachLoopMaxInteger:·gc.alloc.rate.norm      avgt   10    32.258 ±    0.007    B/op
LoopBenchmarkMain.forEachLoopMaxInteger:·gc.count                avgt   10       ≈ 0             counts
LoopBenchmarkMain.forMax2Integer                                 avgt   10     0.540 ±    0.024   ms/op
LoopBenchmarkMain.forMax2Integer:·gc.alloc.rate                  avgt   10    ≈ 10⁻⁴             MB/sec
LoopBenchmarkMain.forMax2Integer:·gc.alloc.rate.norm             avgt   10     0.232 ±    0.011    B/op
LoopBenchmarkMain.forMax2Integer:·gc.count                       avgt   10       ≈ 0             counts
LoopBenchmarkMain.forMaxInteger                                  avgt   10     0.522 ±    0.018   ms/op
LoopBenchmarkMain.forMaxInteger:·gc.alloc.rate                   avgt   10    ≈ 10⁻⁴             MB/sec
LoopBenchmarkMain.forMaxInteger:·gc.alloc.rate.norm              avgt   10     0.224 ±    0.008    B/op
LoopBenchmarkMain.forMaxInteger:·gc.count                        avgt   10       ≈ 0             counts
LoopBenchmarkMain.iteratorMaxInteger                             avgt   10     0.603 ±    0.013   ms/op
LoopBenchmarkMain.iteratorMaxInteger:·gc.alloc.rate              avgt   10     0.034 ±    0.001  MB/sec
LoopBenchmarkMain.iteratorMaxInteger:·gc.alloc.rate.norm         avgt   10    32.260 ±    0.005    B/op
LoopBenchmarkMain.iteratorMaxInteger:·gc.count                   avgt   10       ≈ 0             counts
LoopBenchmarkMain.lambdaMaxInteger                               avgt   10     2.373 ±    0.106   ms/op
LoopBenchmarkMain.lambdaMaxInteger:·gc.alloc.rate                avgt   10     0.058 ±    0.003  MB/sec
LoopBenchmarkMain.lambdaMaxInteger:·gc.alloc.rate.norm           avgt   10   217.064 ±    0.199    B/op
LoopBenchmarkMain.lambdaMaxInteger:·gc.count                     avgt   10       ≈ 0             counts
LoopBenchmarkMain.parallelStreamMaxInteger                       avgt   10     1.716 ±    0.179   ms/op
LoopBenchmarkMain.parallelStreamMaxInteger:·gc.alloc.rate        avgt   10     0.324 ±    0.034  MB/sec
LoopBenchmarkMain.parallelStreamMaxInteger:·gc.alloc.rate.norm   avgt   10   872.813 ±    0.283    B/op
LoopBenchmarkMain.parallelStreamMaxInteger:·gc.count             avgt   10       ≈ 0             counts
LoopBenchmarkMain.streamMaxInteger                               avgt   10     0.621 ±    0.058   ms/op
LoopBenchmarkMain.streamMaxInteger:·gc.alloc.rate                avgt   10     0.222 ±    0.020  MB/sec
LoopBenchmarkMain.streamMaxInteger:·gc.alloc.rate.norm           avgt   10   216.267 ±    0.025    B/op
LoopBenchmarkMain.streamMaxInteger:·gc.churn.PS_Eden_Space       avgt   10     1.590 ±    5.068  MB/sec
LoopBenchmarkMain.streamMaxInteger:·gc.churn.PS_Eden_Space.norm  avgt   10  1636.037 ± 5227.739    B/op
LoopBenchmarkMain.streamMaxInteger:·gc.count                     avgt   10     2.000             counts
LoopBenchmarkMain.streamMaxInteger:·gc.time                      avgt   10    40.000                 ms
vagrant@tfb-all:~/loops-jmh-playground$
```
