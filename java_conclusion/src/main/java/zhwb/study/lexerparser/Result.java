package zhwb.study.lexerparser;

public class Result {
    private String recognized;
    private String remaining;
    private boolean succeeded;

    private Result(String recognized, String remaining,
                   boolean succeeded) {
        this.recognized = recognized;
        this.remaining = remaining;
        this.succeeded = succeeded;
    }

    public boolean is_succeeded() {
        return succeeded;
    }

    public String get_recognized() {
        return recognized;
    }

    public String get_remaining() {
        return remaining;
    }

    public static Result succeed(String recognized,
                                 String remaining) {
        return new Result(recognized, remaining, true);
    }

    public static Result concat(Result r1, Result r2) {
        return new Result(
                r1.get_recognized().concat(r2.get_recognized()),
                r2.get_remaining(), true);
    }

    public static Result fail() {
        return new Result("", "", false);
    }
}