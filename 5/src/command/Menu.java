package command;

import java.util.HashMap;
import java.util.Map;

import command.AddObligationCommand;
import command.RemoveObligationCommand;
import command.ViewObligationsCommand;
import command.SortObligationsCommand;
import command.CalculateTotalValueCommand;
import Derivative;
import utils.Utils;

public class Menu implements Command {
    private final Derivative derivative;
    private final Map<Integer, command.Command> commandMap = new HashMap<>();

    public Menu(Derivative derivative) {
        this.derivative = derivative;
        init(); // Ініціалізуємо команди
    }

    private void init() {
        // Створюємо команди для меню
        commandMap.put(1, new AddObligationCommand(derivative, Utils.getScanner()));
        commandMap.put(2, new RemoveObligationCommand(derivative, Utils.getScanner()));
        commandMap.put(3, new ViewObligationsCommand(derivative));
        commandMap.put(4, new SortObligationsCommand(derivative));
        commandMap.put(5, new CalculateTotalValueCommand(derivative));
    }

    @Override
    public void execute() {
        while (true) {
            displayMenu(); // Відображаємо меню
            int choice = Utils.enterIntValue("Select an option: ", 0, commandMap.size());
            if (choice == 0) {
                System.out.println("Exiting...");
                return; // Завершуємо роботу
            }
            Command command = commandMap.get(choice);
            if (command != null) {
                command.execute(); // Викликаємо вибрану команду
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public String getDesc() {
        return "Main menu";
    }

    private void displayMenu() {
        System.out.println("MENU:");
        for (var entry : commandMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getDesc());
        }
        System.out.println("0. Exit");
    }
}
