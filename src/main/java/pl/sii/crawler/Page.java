package pl.sii.crawler;


public class Page {
    private final Integer page;
    private final String content;

    public Page( Integer page, String content ) {
        this.page = page;
        this.content = content;
    }

    public Integer getNumber() {
        return page;
    }

    public String getContent() {
        return content;
    }
}
