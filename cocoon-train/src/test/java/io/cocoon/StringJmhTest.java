package io.cocoon;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class StringJmhTest {
    private String str;
    private char c;
    private String s;

    @Param({"1", "10", "50"})
    private int param;

    @Setup
    public void setup() {
        str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        c = 'y';
        s = "y";
    }


    @Benchmark
    public int indexOfCharForeach() {
        int index = 0;
        for (int i = 0; i < param; i++) {
            index = str.indexOf('z');
        }
        return index;
    }

    @Benchmark
    public int indexOfStringForeach() {
        int index = 0;
        for (int i = 0; i < param; i++) {
            index = str.indexOf("z");
        }
        return index;
    }

    /**
     * 运行main方法，即可在target目录下生成jmh-result.json文件
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringJmhTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}