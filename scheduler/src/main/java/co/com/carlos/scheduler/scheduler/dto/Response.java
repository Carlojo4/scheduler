package co.com.carlos.scheduler.scheduler.dto;

public class Response {

    private String pingResponse;
    private String headersContent;

    public Response(final String pingResponse, final String headersContent) {
        this.pingResponse = pingResponse;
        this.headersContent = headersContent;
    }

    public String getPingResponse() {
        return pingResponse;
    }

    public void setPingResponse(final String pingResponse) {
        this.pingResponse = pingResponse;
    }

    public String getHeadersContent() {
        return headersContent;
    }

    public void setHeadersContent(final String headersContent) {
        this.headersContent = headersContent;
    }
}
