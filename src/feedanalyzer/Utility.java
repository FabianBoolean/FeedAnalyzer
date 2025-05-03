package feedanalyzer;

import java.io.*;
import java.util.*;

/**
 * Diese Klasse bietet Hilfsmethoden für die Analyse von Texten, insbesondere für die Berechnung von Sentiment-Scores.
 * @Fabian Steinhauser
 * @version 03-05-2025
 */
public class Utility {

    /**
     * Berechnet den Sentiment-Score eines gegebenen Textes anhand von Stop-Wörtern und einem Lexikon.
     * @param text Der zu analysierende Text.
     * @return Der berechnete Sentiment-Score des Textes.
     */
    public static double analyzeText(String text) {
        Set<String> stopWords = new HashSet<>();  // Menge von Stop-Wörtern
        Map<String, Double> vaderLexicon = new HashMap<>(); // Lexikon für Sentiment-Werte

        // Versucht, Dateien zu lesen und den Sentiment-Score zu berechnen
        try (
                BufferedReader stopReader = new BufferedReader(new FileReader("resources/SmartStoplist.txt"));
                BufferedReader lexReader = new BufferedReader(new FileReader("resources/vader_lexicon.txt"))
        ) {
            String line;

            // Stop-Wörter aus der Datei einlesen
            while ((line = stopReader.readLine()) != null) {
                stopWords.add(line.trim().toLowerCase());
            }

            // Vader Lexikon aus der Datei einlesen
            while ((line = lexReader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    vaderLexicon.put(parts[0].toLowerCase(), Double.parseDouble(parts[1]));
                }
            }

            // Sentiment-Score des Textes berechnen
            String[] words = text.toLowerCase().split("\\W+");
            double score = 0;
            int count = 0;

            for (String word : words) {
                if (!stopWords.contains(word) && vaderLexicon.containsKey(word)) {
                    score += vaderLexicon.get(word);
                    count++;
                }
            }

            return count == 0 ? 0 : score / count; // Falls keine relevanten Wörter vorhanden sind, gebe 0 zurück.

        } catch (IOException e) {
            e.printStackTrace();
            return 0; // Im Fehlerfall wird 0 als Wert zurückgegeben
        }
    }
}
