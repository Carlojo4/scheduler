package co.com.carlos.scheduler.scheduler.dto;

public class Request {
    String cron;
    String url;

    public Request(final String cron, final String url) {
        this.cron = cron;
        this.url = url;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(final String cron) {
        this.cron = cron;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
