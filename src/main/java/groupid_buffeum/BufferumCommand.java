package groupid_buffeum;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BufferumCommand {
    public static Object comand(Update update) {
        System.out.print("[comand]");
        //Для команд
        if(update.getMessage().hasText()) {
            boolean cmdBool = false;
            String cmd = update.getMessage().getText(), cmd2 = "";
            char[] charCmd = cmd.toCharArray();
            
            //для цифр
            // try {
            //     int i = Integer.parseInt(cmd);
            //     return groupid_buffeum.BufferumKeyBoard.DeleteFile(update, i);
            // } catch(NumberFormatException e) {  }

            //для цифр
            try {
                int i = Integer.parseInt(cmd);
                return groupid_buffeum.BufferumKeyBoard.downloadFile(update, i);
            } catch(NumberFormatException e) {  }

            //cmd
            if((String.valueOf(charCmd[0]) + String.valueOf(charCmd[1]) + String.valueOf(charCmd[2]) +
                String.valueOf(charCmd[3]) + " ").equals("/cmd ")) { cmd2 = "/cmd "; }
            if(cmd2.equals("/cmd ")) {
                System.out.println(cmd);
                cmdBool = true;
                String buffer = "";
                for(int i = 5; i < charCmd.length; i++) {
                    buffer += String.valueOf(charCmd[i]);
                    System.out.println("buffer ->" + buffer);
                }
                return groupid_buffeum.BufferumCmd.cmd(update, buffer);
            }
            
            if(cmd.equals("/start")) {
                System.out.print("<start>");
                cmdBool = true;
                return groupid_buffeum.BufferumKeyBoard.setStartKeyBoard(update);
            }
            
            if(cmd.equals("Меню")) {
                System.out.print("<Menu>");
                cmdBool = true;
                return groupid_buffeum.BufferumKeyBoard.listUpdate(update, "command");
            }
            
            //не команда а простой текст
            if(!cmdBool) { System.out.println(cmd); }
        }
        return null;
    }
}