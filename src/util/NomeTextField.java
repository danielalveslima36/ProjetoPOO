package util;

import javafx.scene.control.TextField;

/**
 * A classe <b>NomeTextField</b> formata campos TextField.
 * Dessa forma, o usuario só pode informar letras.
 * @author Maria Kelcilene
 * @author Daniel alves
 * @version 1.0
 * @since 04-04-19
 */

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
