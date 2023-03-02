public class Pair
{
    private final String key;
    private final Integer value;

    public Pair(String aKey, Integer aValue)
    {
        key   = aKey;
        value = aValue;
    }

    public String key()   { return key; }
    public Integer value() { return value; }
}