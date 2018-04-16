package inci.loader.parser.cartas;

import inci.entities.User;

public final class LetterGenerator {

    public static void generateTxtLetter(User Usuario) {
	new TxtLetter().createLetter(Usuario);
    }

    public static void generateWordLetter(User Usuario) {
	new WordLetter().createLetter(Usuario);
    }

    public static void generatePdfLetter(User Usuario) {
	new PdfLetter().createLetter(Usuario);
    }

}
