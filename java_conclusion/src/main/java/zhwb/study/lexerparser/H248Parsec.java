package zhwb.study.lexerparser;

public class H248Parsec {
    public static Parser alpha() {
        return new SAT(new IsAlpha(), new Item());
    }

    public static Parser digit() {
        return new SAT(new IsDigit(), new Item());
    }

    public static Parser underline() {
        return new SAT(new IsUnderline(), new Item());
    }

    public static Parser digit_or_alpha_or_underline() {
        return new OR(alpha(), new OR(digit(), underline()));
    }

    public static Parser zero_or_many(int max, Parser parser) {
        return new OR(new OneOrMany(max, parser), new Zero());
    }

    public static Parser name() {
        return new SEQ(alpha(),
                zero_or_many(64,
                        digit_or_alpha_or_underline()));
    }

    public static void main(String[] args) {
//        Result user001 = name().parse("USER001");
        Result user001 = name().parse("a0123456789012345678901234567890123456789012345678901234567890123456789");
        System.out.println(user001.get_remaining());
        System.out.println(user001.is_succeeded());
        System.out.println(user001.get_recognized());
    }
}