package problems;

import java.util.concurrent.atomic.AtomicInteger;

public class ProblemsIllustrate {
    public static int variable = 0;
    public static AtomicInteger atomicVariable = new AtomicInteger(0);
    public static int variableTwo = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread() {
            @Override
            public void start() {
                for (int i = 0; i < 500; i++) {
                    variable++;
                    atomicVariable.incrementAndGet();
                }

                System.out.println("variableTwo");
                System.out.println(variableTwo);
                System.out.println("Finished threadOne");
            }
        };

        Thread threadTwo = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    variable++;
                    atomicVariable.incrementAndGet();
                }
                System.out.println("assign variableTwo!");
                variableTwo = 10;
                System.out.println("Finished threadTwo!");
            }
        };

        Thread threadThree = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    variable++;
                    atomicVariable.incrementAndGet();
                }
                System.out.println("Finished threadThree!");
            }
        };

        threadTwo.start();
        threadOne.start();
        threadThree.start();

        threadOne.join();
        threadTwo.join();
        threadThree.join();


        System.out.println(variable);
        System.out.println(atomicVariable.get());

    }

}
