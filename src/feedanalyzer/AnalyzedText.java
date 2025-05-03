package feedanalyzer;

/**
 * Diese Klasse repräsentiert einen analysierten Text mit einem zugehörigen Sentiment-Wert.
 * Sie implementiert das Comparable-Interface, um die Objekte nach dem Sentiment-Wert zu vergleichen.
 * @Fabian Steinhauser
 * @version 01-05-2025
 */
public class AnalyzedText implements Comparable<AnalyzedText> {
    private String text;      // Der analysierte Text
    private double sentiment; // Der Sentiment-Wert des Textes

    /**
     * Konstruktor zum Erstellen eines AnalyzedText-Objekts.
     * @param text Der analysierte Text.
     * @param sentiment Der Sentiment-Wert des Textes.
     */
    public AnalyzedText(String text, double sentiment) {
        this.text = text;
        this.sentiment = sentiment;
    }

    /**
     * Gibt den Text des AnalyzedText-Objekts zurück.
     * @return Der Text des Objekts.
     */
    public String getText() {
        return text;
    }

    /**
     * Gibt den Sentiment-Wert des AnalyzedText-Objekts zurück.
     * @return Der Sentiment-Wert des Objekts.
     */
    public double getSentiment() {
        return sentiment;
    }

    /**
     * Vergleicht das aktuelle AnalyzedText-Objekt mit einem anderen AnalyzedText-Objekt basierend auf dem Sentiment-Wert.
     * @param o Das zu vergleichende AnalyzedText-Objekt.
     * @return Ein negativer Wert, null oder ein positiver Wert, abhängig davon, ob das aktuelle Objekt
     *         weniger, gleich oder mehr Sentiment hat als das zu vergleichende Objekt.
     */
    @Override
    public int compareTo(AnalyzedText o) {
        return Double.compare(this.sentiment, o.sentiment);
    }

    /**
     * Vergleicht zwei AnalyzedText-Objekte auf Gleichheit.
     * @param o Das Objekt, mit dem verglichen werden soll.
     * @return true, wenn die Objekte gleich sind, andernfalls false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzedText other = (AnalyzedText) o;
        return Double.compare(other.sentiment, sentiment) == 0 && text.equals(other.text);
    }

    /**
     * Berechnet den Hashcode für das AnalyzedText-Objekt.
     * @return Der berechnete Hashcode.
     */
    @Override
    public int hashCode() {
        return text.hashCode() + Double.valueOf(sentiment).hashCode();
    }

    /**
     * Gibt eine String-Repräsentation des AnalyzedText-Objekts zurück.
     * @return Die String-Repräsentation des Objekts.
     */
    @Override
    public String toString() {
        return "Text: \"" + text + "\", Sentiment: " + sentiment;
    }
}
