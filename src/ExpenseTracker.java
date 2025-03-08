import java.util.*;

class Expense {
    String category;
    double amount;
    Date date;

    public Expense(String category, double amount, Date date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Category: " + category + " | Amount: ₹" + amount + " | Date: " + date;
    }
}

public class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();
    private Stack<Expense> undoStack = new Stack<>();
    private Scanner scanner = new Scanner(System.in);

    public void addExpense() {
        System.out.print("Enter category: ");
        String category = scanner.next();
        System.out.print("Enter amount: ₹");
        double amount = scanner.nextDouble();
        Date date = new Date(); // Current date

        Expense expense = new Expense(category, amount, date);
        expenses.add(expense);
        undoStack.push(expense); // Save for undo
        System.out.println("Expense added successfully!\n");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.\n");
            return;
        }
        expenses.forEach(System.out::println);
    }

    public void undoLastExpense() {
        if (undoStack.isEmpty()) {
            System.out.println("No expense to undo!\n");
            return;
        }
        Expense lastExpense = undoStack.pop();
        expenses.remove(lastExpense);
        System.out.println("Last expense removed: " + lastExpense + "\n");
    }

    public void sortExpensesByDate() {
        expenses.sort(Comparator.comparing(expense -> expense.date));
        System.out.println("Expenses sorted by date!\n");
    }

    public void sortExpensesByCategory() {
        expenses.sort(Comparator.comparing(expense -> expense.category));
        System.out.println("Expenses sorted by category!\n");
    }

    public void menu() {
        while (true) {
            System.out.println("=== Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Undo Last Expense");
            System.out.println("4. Sort by Date");
            System.out.println("5. Sort by Category");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> undoLastExpense();
                case 4 -> sortExpensesByDate();
                case 5 -> sortExpensesByCategory();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.menu();
    }
}
