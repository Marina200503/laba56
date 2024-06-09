package commands;
import managers.*;
import models.*;
import utility.*;

import java.util.LinkedList;

/**
 * выводит первый элемент коллекции
 */
public class Head extends Command {
    private final Console console;
    private final TCPManager tcpManager;

    private LabWork head;

    public Head(Console console, TCPManager tcpManager) {
        super("head", "вывести первый элемент коллекции");
        this.console = console;
        this.tcpManager = tcpManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        console.println(tcpManager.sendAndGetMassage("head"));
        return true;
    }
}
