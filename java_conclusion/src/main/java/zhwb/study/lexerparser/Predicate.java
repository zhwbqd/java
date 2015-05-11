package zhwb.study.lexerparser;

interface Predicate
{ 
    public boolean satisfy(String value); 
} 

class IsDigit implements Predicate
{
    public boolean satisfy(String value) {
        char c = value.charAt(0);
        return c>='0' && c<='9';
    }
}

class IsAlpha implements Predicate
{
    public boolean satisfy(String value) {
        char c = value.charAt(0);
        return (c>='a' && c<='z') || (c>='A' && c<='Z');
    }
}

class IsUnderline implements Predicate
{
    public boolean satisfy(String value) {
        char c = value.charAt(0);
        return c=='_';
    }
}

