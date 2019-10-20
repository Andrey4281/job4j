package user;

import com.sun.management.VMOption;

public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        for (int i = 0 ; i <100000; i++) {
            System.out.println("Used memory " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
            User user = new User("a");
            user = null;
        }
    }
}
