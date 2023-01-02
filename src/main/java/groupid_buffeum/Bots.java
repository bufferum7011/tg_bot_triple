package groupid_buffeum;
import java.sql.SQLException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bots extends TelegramLongPollingBot {
    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException  {
        System.out.println("==Start init Triple SLS");
        if(new ConnectionServer().getConnServer()) {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new Bots());
            System.out.print("==Finish init Triple SLS");
        } else { System.out.println("!!!!!!Faled init Triple SLS"); }
    }
    

    @Override public void onUpdateReceived(Update update) {
        System.out.print("\n[UPDATE]");
        //Обработка кнопокd
        if(update.hasCallbackQuery()) {
            try {
                System.out.print("[hasCallback]");
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData();
                String chat_idCallback = callbackQuery.getMessage().getChatId().toString();
                int message_idCallback = callbackQuery.getMessage().getMessageId();
                
                if(data.equals("download")) {
                    execute(SendMessage.builder().chatId(chat_idCallback).text("Введите номер файла для скачивания:\n").build());
                }
                if(data.equals("upload")) {
                    //execute(SendMessage.builder().chatId(chat_idCallback).text("Введите номер файла для скачивания:\n").build());
                }
                if(data.equals("back_1")) {
                    execute(DeleteMessage.builder().chatId(chat_idCallback).messageId(message_idCallback-1).build());
                    execute(DeleteMessage.builder().chatId(chat_idCallback).messageId(message_idCallback).build());
                }
                if(data.equals("delete")) {
                    execute(SendMessage.builder().chatId(chat_idCallback).text("Введите номер файла для удаления:\n").build());
                }
                if(data.equals("update")) {
                    execute(DeleteMessage.builder().chatId(chat_idCallback).messageId(message_idCallback-1).build());
                    execute(DeleteMessage.builder().chatId(chat_idCallback).messageId(message_idCallback).build());
                    execute((SendMessage) groupid_buffeum.BufferumKeyBoard.listUpdate(update, "callback"));
                }
            } catch(TelegramApiException e) { e.printStackTrace(); }
        }
        //command
        if(update.getMessage().hasText()) {
            try {
                Integer code = groupid_buffeum.BufferumHandler.code(BufferumCommand.comand(update));
                if(code.equals(1)) { execute((SendMessage) BufferumCommand.comand(update)); }
                if(code.equals(2)) { execute((SendPhoto)   BufferumCommand.comand(update)); }
                if(code.equals(3)) { execute((SendDocument) BufferumCommand.comand(update)); }
                if(code.equals(4)) { execute((SendAudio) BufferumCommand.comand(update)); }
                if(code.equals(5)) { execute((SendLocation) BufferumCommand.comand(update)); }
                if(code.equals(6)) { execute((SendVideo) BufferumCommand.comand(update)); }
                if(code.equals(7)) { execute((SendSticker) BufferumCommand.comand(update)); }
                if(code.equals(8)) { execute((DeleteMessage) BufferumCommand.comand(update)); }
                if(code==null||code==0) { System.out.println("exeBost_code()->"+code); }
            } catch (TelegramApiException e) { e.printStackTrace(); }
        }
    }
    @Override public String getBotToken() { return "5508859652:AAHHd93dnw9UIzTE2CK4v2Q_XbzS28Y042Y"; }
    @Override public String getBotUsername() { return "@triple_sls_bot"; }
}