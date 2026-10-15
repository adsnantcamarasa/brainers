public class NoteRequest{
    private String title;
    private String content;

    public NoteRequest(String title, String content){
        this.title   = title;
        this.content = content; 
    }

    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }
}