package io.cocoon.jdk;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("future ---- start");
//            return "haha";
//        });
//
//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("future1 ---- start");
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
//            System.out.println("future1 = " + "Hello" + "---- end");
//            return "Hello";
//        });

        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            return "first";
        });
//        CompletableFuture<String> second = first.thenApplyAsync((s) -> {
//            return s + " second";
//        });
//        CompletableFuture<String> third = first.thenApplyAsync((s) -> {
//            return s + " third";
//        });
//        CompletableFuture<String> fourth = first.thenApplyAsync((s) -> {
//            return s + " fourth";
//        });
//
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(first, second, third, fourth);

        System.out.println("first = " + first.get());


//
//        CompletableFuture<String> future2 = future1.thenApply((s) -> {
//            System.out.println("start----future2 = " + s);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            String x = s + "World";
//            System.out.println("end----future2 = " + x);
//            return x;
//        });
//        CompletableFuture<String> future3 = future2.thenApply((s) -> {
//            String x = s + "!";
//            System.out.println("future3 = " + x);
//            return x;
//        });



//        boolean complete = future1.complete("111111");
//        System.out.println("complete = " + complete);
//        System.out.println("=================================");
//        Thread.sleep(1000);
//        future1.obtrudeValue("111111");
//        System.out.println(future1.get());
//        System.out.println(voidCompletableFuture.get());
//        System.out.println("=================================");
//        System.out.println(future2.get());
//        System.out.println("=================================");
//        System.out.println(future3.get());
//        System.out.println("=================================");

    }

}
