package ui;

import dao.BookDao;
import model.user.User;
import service.BorrowManager;

import java.util.Scanner;

public class UserUI {
    private static final Scanner scanner = new Scanner(System.in);
    private boolean isQuit = false;

    public void showUserUI(User loginUser) {
        String order = "";
        while (!isQuit) {
            System.out.println("##################user console##################");
            System.out.println("#########input 'list' to show all commands######");
            System.out.print("$: ");
            order = scanner.nextLine();
            switch (order) {
                case "updatePassword":
                    this.updatePassword(loginUser);
                    break;
                case "updateUsernameAndPassword":
                    this.updateUsernameAndPassword(loginUser);
                    break;
                case "updateUsername":
                    this.updateUsername(loginUser);
                    break;
                case "borrow":
                    this.borrow(loginUser);
                    break;
                case "readBook":
                    this.readBook();
                    break;
                case "list":
                    this.showAllCommand();
                    break;
                case "logout":
                    this.logout();
                    break;
                default:
                    System.out.println("wrong command!");
                    System.out.println("input list to get all commands");
                    break;

            }
        }
    }

    private void updatePassword(User loginUser) {
        System.out.print("Userid: ");
        String userid = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        loginUser.setPassword(userid,password);
    }

    private void updateUsernameAndPassword(User loginUser) {
        System.out.print("Userid: ");
        String userid = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        loginUser.setUsernameAndPassword(userid,password,username);
    }

    private void updateUsername(User loginUser) {
        System.out.print("Userid: ");
        String userid = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        loginUser.setUsername(userid,username);
    }

    private void borrow(User user) {
        BorrowManager borrowManager = new BorrowManager();
        BookDao bookDao = BookDao.getInstance();
        bookDao.showAllBooks();
        System.out.print("Which one you want to borrow(input bookId): ");
        String bookId = scanner.nextLine();

        borrowManager.borrow(user, bookId);
    }

    private void readBook() {
        BookDao bookDao = BookDao.getInstance();
        bookDao.showAllBooks();
        System.out.println("Which one you want to read(input bookId): ");

        String bookId = scanner.nextLine();
        bookDao.readBook(bookId);
    }

    private void showAllCommand() {
        System.out.println("#################all commands###############");
        System.out.println("updatePassword");
        System.out.println("updateUsernameAndPassword");
        System.out.println("updateUsername");
        System.out.println("borrow");
        System.out.println("readBook");
        System.out.println("list");
        System.out.println("logout");
        System.out.println("############################################");
    }

    private void logout() {
        isQuit = true;
    }
}
