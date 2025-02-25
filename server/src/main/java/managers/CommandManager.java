package managers;

import commands.Command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    /**
     * добавляет команду
     * @param commandName - название команды.
     * @param command - команда.
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * @return словарь команд.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * @return история команд.
     */
    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * добавляет команду в историю.
     * @param command - команда.
     */

}
