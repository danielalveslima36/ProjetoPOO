package util;

import javafx.scene.control.TextField;

public class NomeTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[a-z A-Z]") || text.isEmpty()){
            super.replaceText(start, end, text );
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
