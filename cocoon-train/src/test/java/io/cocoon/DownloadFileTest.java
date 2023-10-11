package io.cocoon;

import org.apache.commons.io.FileUtils;
import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
public class DownloadFileTest {

    private String fileUrl;

    @Setup
    public void setup() {
        fileUrl = "https://risk-test-1252751510.cos.ap-guangzhou.myqcloud.com/perm/risk/zip/risk_netcheck/%E8%81%94%E6%98%93%E8%9E%8D%E6%95%B0%E5%AD%97%E7%A7%91%E6%8A%80%E9%9B%86%E5%9B%A2%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8_BH1588491766133170266_1693905698587.zip?q-sign-algorithm=sha1&q-ak=AKIDyxu24xiMsHXimjXjMy21tIZemlYDfw40&q-sign-time=1696749895;1696922695&q-key-time=1696749895;1696922695&q-header-list=&q-url-param-list=&q-signature=e7c6f340377ec57645346985d46c2e88f40fb0e0";
    }

    @Param({"1", "2", "3"})
    public String index;

    @Benchmark
    public void testHttp() {

        int next = ThreadLocalRandom.current().nextInt();
        String name = "A" + index + next + ".zip";
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());

             FileOutputStream fileOutputStream = new FileOutputStream(name)) {

            byte dataBuffer[] = new byte[1024];

            int bytesRead;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {

                fileOutputStream.write(dataBuffer, 0, bytesRead);

            }

        } catch (IOException e) {

            // handle exception

        }
    }

    @Benchmark
    public void testURL(){

        try {
            URL url = new URL(fileUrl);
            int next = ThreadLocalRandom.current().nextInt();
            String pathname = "B" + index + next + ".zip";
            FileUtils.copyURLToFile(url, new File(pathname));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Benchmark
    public void testNIO() throws IOException {

        int next = ThreadLocalRandom.current().nextInt();
        String name = "C" + index + next + ".zip";
        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(fileUrl).openStream());

//        从ReadableByteChannel 读取字节将被传输至FileChannel:
        try (FileOutputStream fileOutputStream = new FileOutputStream(name)) {
//        FileChannel fileChannel = fileOutputStream.getChannel();

//        然后使用transferFrom方法，从ReadableByteChannel 类下载来自URL的字节传输到FileChannel：
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }

//        transferTo() 和 transferFrom() 方法比简单使用缓存从流中读更有效。
//        依据不同的底层操作系统，数据可以直接从文件系统缓存传输到我们的文件，而不必将任何字节复制到应用程序内存中。
//        在linux和UNIX系统上，这些方法使用零拷贝技术，减少了内核模式和用户模式之间的上下文切换次数。
    }
}
