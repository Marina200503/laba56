import commands.*;
import managers.CollectionManager;
import managers.CommandManager;

import managers.JsonManager;
import managers.TCPServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.Runner;
import utility.StandardConsole;

import java.util.Scanner;

public class Main {
    private static final int PORT=14151;
    private static final Logger LOGGER = (Logger) LogManager.getLogger("Main");

    public static void main(String[] args) {
        var console = new StandardConsole();

        var collectionManager = new CollectionManager();
        if (!collectionManager.loadCollection()) { System.out.println("Коллекция не загружена!"); }

        var commandManager = new CommandManager() {{
            register("info", new Info(collectionManager));
            register("max_by_id", new MaxById(collectionManager));
            register("show", new Show(collectionManager));
            register("add", new Add(collectionManager));
            register("update_id", new UpdateId(collectionManager));
            register("remove_by_id", new RemoveById(collectionManager));
            register("clear", new Clear(collectionManager));
            register("add_if_max", new AddIfIdMax(collectionManager));
            register("remove_greater", new RemoveGreater(collectionManager));
            register("print_field_descending_difficulty", new PrintFieldDescendingDifficulty(collectionManager));
            register("count_greater_than_difficulty", new CountGreaterThanDifficulty(collectionManager));
            register("save", new Save(collectionManager));
            register("head", new Head(collectionManager));
        }};

        var runner = new Runner(console, commandManager);

        var tcpserver = new TCPServer(PORT, runner::executeCommand);
        new Thread(() -> {
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.print("$ ");
                var t = in.nextLine();

                commandManager.getCommands().get("save").execute(new String[]{"save", ""}, null);
                if (t.equals("exit"))
                    System.exit(0);
            }
        }).start();
        tcpserver.start();


    }
}


