package com.example.demo.thread;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author leiningbo
 * 2020-04-01 13点49分
 */
public class MyRunnable implements Runnable {

    private int num = 10;

    ReentrantLock lock = new ReentrantLock();

    /**
     * synchronied 和 ReentrantLock
     * 1) synchronized:可以用来同步方法和同步代码块。
     * 同步方法:给一个方法增加synchronized关键字，可以是静态方法(锁住整个类)也可以是非静态方法(不能是抽象方法)
     * 同步代码块:通过锁定一个指定的对象，来对同步代码块进行同步。
     * 同步是高开销操作，尽量少用同步方法，同步关键代码的代码块即可
     * 2) ReentrantLock:可重入锁， 代码通过lock()方法获取锁，但必须调用unlock()方法释放锁
     *
     * @throws Exception
     */
    @Override
    public void run() {
//        synchronized(this) {
        lock.lock();
        try {
            if (num >= 1) {
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < 1000; i++) {
                        String bundleNo = generateBundleNo("")+i;
                        System.out.println(super.getClass().getName()+":::"+bundleNo);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num--;
                System.out.println(Thread.currentThread().getName() + ":" + num);
            } else {
                System.out.println("库存不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

//        }
    }

    /**
     * 自定义捆包号
     * @return KB
     */
    public static String generateBundleNo(String lastBundle) {
        StringBuilder result = new StringBuilder();
        result.append("KB");
        String date = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        result.append(date);
        String time = System.currentTimeMillis()+"";
        result.append(time.substring(time.length() - 4));
//        if (!StringUtils.isEmpty(lastBundle)) {
//            Pattern pattern = Pattern.compile("" + "(\\d{12})(\\d{4})$");
//            Matcher matcher = pattern.matcher(lastBundle);
//            String lastFlowNoNum = "";
//            String lastFlowNoDate = "";
//            if (matcher.find()) {
//                lastFlowNoDate = matcher.group(1);
//                lastFlowNoNum = matcher.group(2);
//            }
//            if (lastFlowNoDate.equals(date)) {
//                Integer curFlowNoNumDig = Integer.parseInt(lastFlowNoNum) + 1;
//                result.append(String.format("%0" + 4 + "d", curFlowNoNumDig));
//            } else {
//                result.append(String.format("%0" + 4 + "d", 1));
//            }
//        } else {
//            result.toString();
//        }
        return result.toString();
    }
}
