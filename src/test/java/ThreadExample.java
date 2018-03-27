public class ThreadExample implements Runnable {
    private int a;
    public ThreadExample(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        System.out.println(JavaTest.have$something$done(a));
    }
}
