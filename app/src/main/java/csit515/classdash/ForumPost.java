package csit515.classdash;

/**
 * Created by Rixoro on 4/11/2017.
 */

public class ForumPost {
    private int pid;
    private int fid;
    private String body;
    private String author;
    private int order;

    public ForumPost() {
    }

    public ForumPost(int pid, int fid, String body, String author, int order) {
        this.pid = pid;
        this.fid = fid;
        this.body = body;
        this.author = author;
        this.order = order;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
