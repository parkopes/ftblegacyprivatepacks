package net.jezevcik.ftblegacyprivatepacks;

public class ParseUtils {

    static String formatHtml(String htmlString) {
        final StringBuilder stringBuilder = new StringBuilder();
        final char[] chars = htmlString.toCharArray();

        int index = 0;
        for (char c : chars) {
            stringBuilder.append(c);

            if (c == '>' || (index != chars.length - 1 && chars[index + 1] == '<'))
                stringBuilder.append("\n");

            index++;
        }

        return stringBuilder.toString();
    }

}
