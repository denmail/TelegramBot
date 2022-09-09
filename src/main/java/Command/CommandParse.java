package Test.Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.HashMap;

public class CommandParse {
    private HashMap<String, Command> commands;

    public CommandParse() {
        commands = new HashMap<>();
    }

    public boolean register(Command command){
        if(commands.containsKey(command)) {
            return false;
        } else {
            commands.put(command.getCommandText(), command);
            return true;
        }
    }

    public Boolean checkCommand(String msg, SendMessage outMsg) {
        if(commands.containsKey(msg)) {
            commands.get(msg).vDoCommand(outMsg);
            return true;
        } else {
            outMsg.setText("unknown command");
            return false;
        }
    }


}
