package mail;

import java.util.Scanner;

public class MailServiceStarter {
    private final MailService mailService;

    public MailServiceStarter(MailService mailService) {
        this.mailService = mailService;
    }

    public void start() {
        mailService.readData();
        mailService.writeData(mailService.calculate());
    }

    public static void main(String[] args) {
        MailServiceStarter starter = new MailServiceStarter(new MailServiceBFSConsole
                (new SimpleConsole(new Scanner(System.in), System.out)));
        starter.start();
    }
}