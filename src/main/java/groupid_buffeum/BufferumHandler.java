package groupid_buffeum;
import java.io.File;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class BufferumHandler {
    // public static SendMessage handler() {
    //     System.out.print("[hendler]");
    //     SendMessage sendMessage = new SendMessage();
    //     sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());

    //     if(update.getCallbackQuery().getMessage().getChatId().toString().equals("Download")) {
    //         System.out.println("Не правильный ответ");
    //         sendMessage.setText("---Download");
    //         return sendMessage;
    //     }
    //     return null;
    // }


    public static Integer code(Object o) {
        System.out.print("[code]");
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.send.SendMessage.class))  { System.out.println("<code=" + 1 + ">"); return 1; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.send.SendPhoto.class))    { System.out.println("<code=" + 2 + ">"); return 2; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.send.SendDocument.class)) { System.out.println("<code=" + 3 + ">"); return 3; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.send.SendAudio.class))    { System.out.println("<code=" + 4 + ">"); return 4; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.send.SendLocation.class)) { System.out.println("<code=" + 5 + ">"); return 5; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.send.SendVideo.class))    { System.out.println("<code=" + 6 + ">"); return 6; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.objects.stickers.Sticker.class))  { System.out.println("<code=" + 7 + ">"); return 7; }
        if(o.getClass().equals(org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage.class))  { return 8; }
        
        return null;
    }
    
    // public static SendPhoto nullTag() {
    //     System.out.print("[nullTag]");
    //     if(update.getMessage().getFrom().getUserName() == null) {
    //         SendPhoto sp = new SendPhoto();
    //         sp.setChatId(update.getMessage().getChatId());
    //         sp.setPhoto(new InputFile(new File("database\\resources\\nullTag.png")));
    //         sp.setCaption("Необходим @tag для аутентификации, иначе облако будет не доступным👀");
    //         return sp;
    //     }
    //     else { return null; }
    // }
}