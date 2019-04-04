package app.todo;

public enum TodoStatus {
    INCOMPLETE("INCOMPLETE"),
    COMPLETE("COMPLETE"),
    EXPIRED("EXPIRED");


    private final String status;

    TodoStatus(String status){
        this.status = status;
    }

    public String getStatusCode(){
        return this.status;
    }


}
