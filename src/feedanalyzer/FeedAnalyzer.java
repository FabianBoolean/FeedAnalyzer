package feedanalyzer;

import java.io.*;
import java.util.*;

/**
 * Diese Klasse liest Tweets aus einer CSV-Datei, analysiert deren Sentiment und berechnet den Durchschnitt.
 * @Fabian Steinhauser
 * @version 05-05-2025
 */
public class FeedAnalyzer {

    /**
     * Der Einstiegspunkt des Programms. Liest die Tweets und berechnet deren durchschnittlichen Sentiment-Score.
     * @param args Kommandozeilenargumente.
     */
    public static void main(String[] args) {
        Map<AnalyzedText, String> feedMap = new HashMap<>(); // Map für Tweets und zugehörige Quellen
        double totalSentiment = 0; // Gesamtsentiment aller Tweets
        int messageCount = 0; // Anzahl der verarbeiteten Tweets

        // Versucht, Tweets aus der CSV-Datei zu lesen und deren Sentiment zu berechnen
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/potus_tweets_2017_webarchive_publicaccess.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                double score = Utility.analyzeText(line); // Berechne Sentiment-Score des Tweets
                AnalyzedText at = new AnalyzedText(line, score); // Erstelle AnalyzedText-Objekt

                // DEBUG-AUSGABE:
                System.out.println("Tweet: " + line);
                System.out.println("Score: " + score);
                System.out.println("-------------");

                feedMap.put(at, "Potus"); // Speichere Tweet in der Map
                totalSentiment += score; // Addiere Sentiment zum Gesamtsentiment
                messageCount++; // Erhöhe die Anzahl der verarbeiteten Tweets
            }

            // Berechne den durchschnittlichen Sentiment-Score
            double averageSentiment = messageCount > 0 ? totalSentiment / messageCount : 0;
            System.out.println("Durchschnittlicher Sentiment-Score: " + averageSentiment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
