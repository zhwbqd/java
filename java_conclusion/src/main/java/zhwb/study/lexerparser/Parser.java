package zhwb.study.lexerparser;

interface Parser
{ 
    Result parse(String target);
}

class SEQ implements Parser
{
    private Parser p1;
    private Parser p2;

    public SEQ(Parser p1, Parser p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public Result parse(String target) {
        Result r1 = p1.parse(target);
        if(r1.is_succeeded()) {
            Result r2 = p2.parse(r1.get_remaining());
            if(r2.is_succeeded()) {
                return Result.concat(r1,r2);
            }
        }
        return Result.fail();
    }
}

class SAT implements Parser
{
    private Predicate pre;
    private Parser    parser;

    public SAT(Predicate predicate, Parser parser) {
        this.pre = predicate;
        this.parser = parser;
    }

    public Result parse(String target) {
        Result r = parser.parse(target);
        if(r.is_succeeded() && pre.satisfy(r.get_recognized())) {
            return r;
        }
        return Result.fail();
    }
}

class OR implements Parser
{
    private Parser p1;
    private Parser p2;

    public OR(Parser p1, Parser p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Result parse(String target) {
        Result r = p1.parse(target);
        return r.is_succeeded() ? r : p2.parse(target);
    }
}

class OneOrMany implements Parser
{
    private int max;
    private Parser parser;

    public OneOrMany(int max, Parser parser) {
        this.max = max;
        this.parser = parser;
    }

    public Result parse(String target) {
        Result r = parser.parse(target);
        return r.is_succeeded() ? parse2(r,1) : Result.fail();
    }

    private Result parse2(Result pre, int count) {
        if(count >= max) return pre;
        Result r = parser.parse(pre.get_remaining());
        return r.is_succeeded() ?
                parse2(Result.concat(pre,r),count+1) : pre;
    }
}

class Item implements Parser
{
    public Result parse(String target) {
        if(target.length() > 0) {
            return Result.succeed(target.substring(0,1),
                    target.substring(1));
        }
        return Result.fail();
    }
}