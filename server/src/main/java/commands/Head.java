package commands;

import managers.CollectionManager;
import utility.Response;

/**
 * выводит первый элемент коллекции.
 */
public class Head extends Command {
    private final CollectionManager collectionManager;

    public Head(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * выполняет команду
     * @return успешность выполнения команды.
     */
    @Override
    public Response execute(String[] arguments, Object obj) {
        if (!arguments[1].isEmpty()) {
            return new Response(400, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }
        return new Response("OK",collectionManager.getCollection().getFirst());
    }
}
