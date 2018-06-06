package rsirest2.demo;

public class SearchParam {
    String author;
    String contentEnded;

    public SearchParam() {
    }

    public SearchParam(String author, String contentEnded) {
        this.author = author;
        this.contentEnded = contentEnded;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentEnded() {
        return contentEnded;
    }

    public void setContentEnded(String contentEnded) {
        this.contentEnded = contentEnded;
    }
}
