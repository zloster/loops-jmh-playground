package com.takipi.oss.benchmarks.jmh.loops;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class LoopBenchmarkMain {
	final int size = 100000;
	List<Integer> integers = null;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(LoopBenchmarkMain.class.getSimpleName())
			.addProfiler(GCProfiler.class)
			.detectJvmArgs()
      			.build();

    		new Runner(opt).run();
	}
	
	@Setup
	public void setup() {
		integers = new ArrayList<Integer>(size);
		populate(integers);
	}

	public void populate(List<Integer> list) {
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			list.add(Integer.valueOf(random.nextInt(1000000)));
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int iteratorMaxInteger() {
		int max = Integer.MIN_VALUE;
		for (Iterator<Integer> it = integers.iterator(); it.hasNext(); ) {
			max = Integer.max(max, it.next().intValue());
		}
		return max;
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forEachLoopMaxInteger() {
		int max = Integer.MIN_VALUE;
		for (Integer n : integers) {
			max = Integer.max(max, n.intValue());
		}
		return max;
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forEachLambdaMaxInteger() {
		final Wrapper wrapper = new Wrapper();
		wrapper.inner = Integer.MIN_VALUE;
		
		integers.forEach(i -> wrapper.inner = Integer.max(i.intValue(), wrapper.inner));
		return wrapper.inner;
	}
	
	public static class Wrapper {
		public int inner; 
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forMaxInteger() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			max = Integer.max(max, integers.get(i).intValue());
		}
		return max;
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forMax2Integer() {
		int max = Integer.MIN_VALUE;
		List<Integer> integersLocal = integers;
		for (int i = 0; i < size; i++) {
			max = Integer.max(max, integersLocal.get(i).intValue());
		}
		return max;
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int parallelStreamMaxInteger() {
		return integers.parallelStream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, Integer::max);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int streamMaxInteger() {
		return integers.stream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, Integer::max);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int lambdaMaxInteger() {
		return integers.stream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
	}
}
