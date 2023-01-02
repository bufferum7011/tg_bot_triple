package groupid_buffeum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class BufferumKeyBoard {

    //setInline
    public static SendMessage listUpdate(Update update, String code) {
        System.out.print("[listUpdate]");
        InlineKeyboardButton in1 = new InlineKeyboardButton();
        InlineKeyboardButton in2 = new InlineKeyboardButton();
        InlineKeyboardButton in3 = new InlineKeyboardButton();
        InlineKeyboardButton in4 = new InlineKeyboardButton();
        InlineKeyboardButton in5 = new InlineKeyboardButton();
        in1.setText("⬇️Скачать");
        in1.setCallbackData("download");
        in2.setText("⬆️Загрузить");
        in2.setCallbackData("upload");
        in3.setText("◀️");
        in3.setCallbackData("back_1");
        in4.setText("🗑Удалить");
        in4.setCallbackData("delete");
        in5.setText("♻️Обновить");
        in5.setCallbackData("update");
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row1.add(in1);
        row1.add(in2);
        row2.add(in5);
        row2.add(in4);
        row3.add(in3);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        InlineKeyboardMarkup im = new InlineKeyboardMarkup();
        im.setKeyboard(rowList);
        
        SendMessage smInlineMenu = new SendMessage();
        smInlineMenu.setText(getListNameFiles());
        smInlineMenu.setReplyMarkup(im);
        if(code=="callback") { smInlineMenu.setChatId(update.getCallbackQuery().getMessage().getChatId().toString()); }
        if(code=="command") { smInlineMenu.setChatId(update.getMessage().getChatId().toString()); }
        return smInlineMenu;
    }

    //получение списка файлов без кнопок и предложения удалить
    private static String getListNameFiles() {
        System.out.println("[getListNameFiles]");
        String listFiles = "", buffer = "";
        try {
            //запуск процесса и чтение результата
            Process proc = Runtime.getRuntime().exec(new String[]{ "getListFiles.bat" });
            BufferedReader br = new BufferedReader (new FileReader("database\\resources\\list_files.txt"));
            for(int i = 1; (buffer = br.readLine()) != null; i++) {
                listFiles += i + ". " + buffer + "\n";
                System.out.println("\t" + listFiles);
                buffer = "";
            }
            br.close();
            
        } catch(IOException ex) { System.out.println("exListFiles["+listFiles+"]..."); }
        return listFiles;
    }

    //Получение имени файла
    private static String nameFile(int num) {
        String buffer = "", aux = "";
        try {
            //запуск процесса и чтение результата
            Process proc = Runtime.getRuntime().exec(new String[]{ "getListFiles.bat" });
            BufferedReader br = new BufferedReader (new FileReader("database\\resources\\list_files.txt"));
            boolean key = true;

            //поиск файла из списка
            for(int i = 1; (buffer = br.readLine()) != null && key; i++) {
                if(i == num) { aux = buffer; key = false; }
            }
            br.close();
        } catch(IOException e) {  }
        return aux;
    }

    //Удаление файла
    public static SendMessage DeleteFile(Update update, int num) {
        System.out.println("[DeleteFile]");
        File file = new File("database\\@bufferum\\" + nameFile(num));
        if(file.delete()) { System.out.println("Файл удален"); }
        else { System.out.println("Файл не обнаружен"); }
        return groupid_buffeum.BufferumKeyBoard.listUpdate(update, "command");
    }

    //Скачивание файла
    public static Object downloadFile(Update update, int num) {
        System.out.println("[downloadFile]");
        String chat_id = update.getMessage().getChatId().toString();
        InputFile inputFile = new InputFile(new File("database\\@bufferum\\" + nameFile(num)));
        String ex = FilenameUtils.getExtension("database\\@bufferum\\" + nameFile(num));

        if(ex.equals("txt") || ex.equals("doc") || ex.equals("docx") || ex.equals("pdf")) {
            SendDocument sd = new SendDocument();
            sd.setChatId(chat_id);
            sd.setDocument(inputFile);
            return sd;
        }
        if(ex == "mp3") {
            SendAudio sa = new SendAudio();
            sa.setChatId(chat_id);
            sa.setAudio(inputFile);
            return sa;
        }
        if(ex.equals("png") || ex.equals("jpg") || ex.equals("jpeg")) {
            SendPhoto sp = new SendPhoto();
            sp.setChatId(chat_id);
            sp.setPhoto(inputFile);
            return sp;
        }
        return null;
    }

    //replyKeyBoard
    public static SendMessage setStartKeyBoard(Update update) {
        System.out.print("[setStartKeyBoard]");
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("⬅"));
        row1.add(new KeyboardButton("Меню"));
        
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);

        ReplyKeyboardMarkup replyKeyBoard = new ReplyKeyboardMarkup();
        replyKeyBoard.setSelective(true);
        replyKeyBoard.setResizeKeyboard(true);
        replyKeyBoard.setOneTimeKeyboard(false);
        replyKeyBoard.setKeyboard(keyboard);
        
        SendMessage smStartKeyBoard = new SendMessage();
        smStartKeyBoard.enableMarkdown(true);
        smStartKeyBoard.setText("Меню:");
        smStartKeyBoard.setReplyMarkup(replyKeyBoard);
        smStartKeyBoard.setChatId(update.getMessage().getChatId().toString());
        return smStartKeyBoard;
    }

}