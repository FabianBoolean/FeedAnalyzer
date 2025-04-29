package feedanalyzer;

public class AnalyzedText implements Comparable<AnalyzedText> {
    private String text;
    private double sentiment;

    public AnalyzedText(String text, double sentiment) {
        this.text = text;
        this.sentiment = sentiment;
    }

    public String getText() {
        return text;
    }

    public double getSentiment() {
        return sentiment;
    }

    @Override
    public int compareTo(AnalyzedText o) {
        return Double.compare(this.sentiment, o.sentiment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzedText other = (AnalyzedText) o;
        return Double.compare(other.sentiment, sentiment) == 0 && text.equals(other.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode() + Double.valueOf(sentiment).hashCode();
    }

    @Override
    public String toString() {
        return "Text: \"" + text + "\", Sentiment: " + sentiment;
    }
}
