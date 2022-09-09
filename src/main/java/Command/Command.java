package Test.Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Command {
    private final String Indentifier;
    private final String CommandText;

    public Command(String indentifier, String commandText) {
        Indentifier = indentifier;
        CommandText = commandText;
    }

    public String getIndentifier() {
        return Indentifier;
    }

    public String getCommandText() {
        return CommandText;
    }

    public void vDoCommand(SendMessage msg) {
        msg.setText("smt");
        execute(msg);
    }

}
