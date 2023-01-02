package groupid_buffeum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BufferumCmd {
    public static SendMessage cmd(Update update, String buffer) {
        System.out.print("[CMD]");
        String cmd = "";
        String chat_id = update.getMessage().getChatId().toString();
        try {
            
            //записть cmd в файл
            PrintWriter pw = new PrintWriter("auxiliary.bat");
            pw.println(buffer);
            pw.close();
            buffer = "";
            //запуск процесса и чтение результата
            Process process = Runtime.getRuntime().exec(new String[]{ "auxiliary.bat" });
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            while((buffer = br.readLine()) != null) { cmd += buffer; buffer = ""; }
        } catch(IOException e) { System.out.println("exCmd["+cmd+"]..."); }
        return (cmd == "" || cmd == null) ? new SendMessage(chat_id, "EMPTY!") : new SendMessage(chat_id, cmd);
    }
}