package util.concurrent;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RecursiveTask;

public class Test {
private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        Comparator<String> compareTo = Comparator.comparingInt(Integer::valueOf);
        Comparator<String> compareTo1 = String::compareTo;
        Comparator<String> compareTo2 = (a,b) -> a.compareTo(b);
        Comparator<String> compareTo4 = (a,b) -> 1;
        Comparator<MyForkTask> compareTo3 = MyForkTask::tryCompare;


        TreeSet<String> strings = new TreeSet<>(compareTo);
        strings.add("1");
        strings.add("2");
        System.out.println("strings = " + strings);

        while (true) {
            for (int i = 0; i < 100; i++) {
                vector.add(i);
            }
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                synchronized(vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println("remove = " + i);
                        Integer remove = vector.remove(i);
                    }
                }
            });
    //        executorService.execute(() -> {
    //            synchronized(vector) {
    //                for (int i = 0; i < vector.size(); i++) {
    //                    System.out.println("get = " + i);
    //                    vector.get(i);
    //                }
    //            }
    //        });
            executorService.shutdown();
        }

    }

    public static <T> List<T> getList(){
        List<T> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        return (List<T>)map.get("12");
    }

//    public static void main(String[] args) throws Exception {
//
//        MyForkTask myForkTask = new MyForkTask(0, 100);
//        ForkJoinTask submit = ForkJoinPool.commonPool().submit(myForkTask);
//
//        System.out.println("submit.get() = " + submit.get());
//
//    }
}

class MyForkTask extends RecursiveTask{

    private int begin;
    private int end;

    MyForkTask(int begin, int end){
        super();
        this.begin = begin;
        this.end = end;
    }

    public int tryCompare(MyForkTask myForkTask){
        return 1;
    }

    @Override
    protected Object compute() {
        
        if(end - begin  >= 100){
            int mid = (begin + end) / 2;
            MyForkTask left = new MyForkTask(begin, mid);
            MyForkTask right = new MyForkTask(mid + 1, end);
            left.fork();
            right.fork();
            return (int)left.join() + (int)right.join();
        } else {
            int sum = 0;
            for(int i = begin; i <= end; i++){
                sum += i;
            }
            return sum;
        }
        
    }
}