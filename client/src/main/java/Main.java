import commands.*;
import managers.CommandManager;
import managers.TCPClient;
import managers.TCPManager;
import utility.Runner;
import utility.StandardConsole;

public class Main {
    private static final int PORT = 14151;
    private static final String SERVER_ADRESS = "127.0.0.1";
    public static void main(String[] args) {
        var console = new StandardConsole();
        var tcpclient = new TCPClient(SERVER_ADRESS, PORT);
        tcpclient.start();
        var tcpmanager = new TCPManager(tcpclient);

        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("info", new Info(console, tcpmanager));
            register("max_by_id", new MaxById(console, tcpmanager));
            register("show", new Show(console, tcpmanager));
            register("add", new Add(console, tcpmanager));
            register("update_id", new UpdateId(console, tcpmanager));
            register("remove_by_id", new RemoveById(console, tcpmanager));
            register("clear", new Clear(console, tcpmanager));
            register("execute_script", new ExecuteScriptFileName(console));
            register("exit", new Exit(console));
            register("add_if_max", new AddIfIdMax(console, tcpmanager));
            register("remove_greater", new RemoveGreater(console, tcpmanager));
            register("print_field_descending_difficulty", new PrintFieldDescendingDifficulty(console, tcpmanager));
            register("count_greater_than_difficulty", new CountGreaterThanDifficulty(console, tcpmanager));
            register("head", new Head(console, tcpmanager));
        }};

        new Runner(console, commandManager, tcpmanager).interactiveMode();
    }
}

