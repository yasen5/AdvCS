package Aug21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classwork2 {
    public static void main(String[] args) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        ArrayList<Language> languages = new ArrayList(List.of(new Chinese(), new English(), new French(), new Japanese()));
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Select a language");
            String input = sc.next();
            for (Language language : languages) {
                if (input.equals(language.getLanguage())) {
                    System.out.println(language.getLanguage() + "\n" + language.getAuthor() + "\n" + language.getHello() + "\n" + language.getGoodbye() + "\n");
                }
            }
            if (input.toLowerCase().equals("quit")) {
                return;
            }
        }
    }
}

interface Language {
    String author = "MV Students";

    public abstract String getLanguage();

    public abstract String getAuthor();

    public abstract String getHello();

    public abstract String getGoodbye();
}

class Japanese implements Language {
    @Override
    public String getLanguage() {
        return "Japanese";
    }

    @Override
    public String  getAuthor() {
        return author;
    }

    @Override
    public String getHello() {
        return "今日は";
    }

    @Override
    public String getGoodbye() {
        return "じゃ、また";
    }
}

class Chinese implements Language {
    @Override
    public String getLanguage() {
        return "Chinese";
    }

    @Override
    public String  getAuthor() {
        return author;
    }

    @Override
    public String getHello() {
        return "你好";
    }

    @Override
    public String getGoodbye() {
        return "再见";
    }
}

class English implements Language {
    @Override
    public String getLanguage() {
        return "English";
    }

    @Override
    public String  getAuthor() {
        return author;
    }

    @Override
    public String getHello() {
        return "Hello";
    }

    @Override
    public String getGoodbye() {
        return "Goodbye";
    }
}

class French implements Language {
    @Override
    public String getLanguage() {
        return "French";
    }

    @Override
    public String  getAuthor() {
        return author;
    }

    @Override
    public String getHello() {
        return "Bonjour";
    }

    @Override
    public String getGoodbye() {
        return "Au revoir";
    }
}
