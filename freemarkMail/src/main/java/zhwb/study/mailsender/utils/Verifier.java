package zhwb.study.mailsender.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Provides for the verification of one or more values. If any of the verification tests fail a
 * single exception is thrown. The exception message contains the combined verification failure
 * messages associated with each verification test.
 *
 * <p>The intended use is for verification of parameters passed to services. For example, in an
 * order service add order method, a verifier may be used to validate an order object that is passed
 * to the method.</p>
 *
 * <p>Example usage:</p>
 *
 * <pre>
   String string = null;
   Object object = null;
   List<String> list1 = null;
   List<String> list2 = new ArrayList<String>();
   Set<String> set1 = null;
   Set<String> set2 = new HashSet<String>();
   Map<String, String> map1 = null;
   Map<String, String> map2 = new HashMap<String, String>();

   int count = 0;

   Verifier verifier = new Verifier();
   verifier.isFalse(true, "Validation test %d, must be false.", ++count);
   verifier.isTrue(false, "Validation test %d, must be true.", ++count);
   verifier.isNotNull(object, "Validation test %d, must not be null.", ++count);
   verifier.isNotEmpty(string, "Validation test %d, must not be empty.", ++count);
   verifier.isNotEmpty(list1, "Validation test %d, must not be empty.", ++count);
   verifier.isNotEmpty(list2, "Validation test %d, must not be empty.", ++count);
   verifier.isNotEmpty(set1, "Validation test %d, must not be empty.", ++count);
   verifier.isNotEmpty(set2, "Validation test %d, must not be empty.", ++count);
   verifier.isNotEmpty(map1, "Validation test %d, must not be empty.", ++count);
   verifier.isNotEmpty(map2, "Validation test %d, must not be empty.", ++count);
   verifier.hasText(null, "Validation test %d, must have text.", ++count);
   verifier.hasText(string, "Validation test %d, must have text.", ++count);

   verifier.throwIfError("One or more validation tests have failed.");
 * </pre>
 *
 * <p>The above example throws an exception due to the numerous verification errors.</p>
 *
 * <pre>
   java.lang.IllegalArgumentException: One or more validation tests have failed. Validation test 1,
   must be false. Validation test 2, must be true. Validation test 3, must not be null. Validation
   test 4, must not be empty. Validation test 5, must not be empty. Validation test 6, must not be
   empty. Validation test 7, must not be empty. Validation test 8, must not be empty. Validation
   test 9, must not be empty. Validation test 10, must have text. Validation test 11, must have text.
      at com.hp.it.cas.xa.validate.Verifier.throwIfError(Verifier.java:209)
      at com.hp.it.cas.xa.validate.VerifierTest.testWithValidationFailures(VerifierTest.java:151)
      ...
 * </pre>
 *
 * <p>The verification methods may also be invoked using a fluent (chain) pattern:</p>
 *
 * <pre>
   new Verifier().isFalse(false, "Validation test 1, must be false.")
                 .isTrue(true, "Validation test 2, must be true.")
                 .isNotEmpty(string, "Validation test 3, must not be empty.")
                 .isNotEmpty(list, "Validation test 4, must not be empty.")
                 .isNotEmpty(set, "Validation test 5, must not be empty.")
                 .isNotEmpty(map, "Validation test 6, must not be empty.")
                 .isNotNull(object, "Validation test 7, must not be null.")
                 .hasText(string, "Validation test 8, must have text.")
                 .throwIfError("One or more validation tests have failed.");
 * </pre>
 *
 * <p>Each of the verification methods accepts 3 parameters: the object to be validated, the
 * validation error message in the form of a format string, and the optional arguments referenced by
 * the format specifiers in the format string. For example:</p>
 *
 * <pre>
   verifier.isTrue(n >= min && n <= max, "The value %d must be between %d and %d.", n, min, max);
 * </pre>
 *
 * <p>See {@link java.lang.String#format(String, Object...) String#format(String, Object...)} for
 * more details on the available format specifiers.</p>
 *
 * @author   Hugh McKee
 * @version  $Revision$, $Date$
 */
public class Verifier {
    private final List<String> messages = new ArrayList<String>();

    /**
     * Verifies that the specified {@link java.lang.String String} argument has non-blank text. If
     * the string is null or does not have any non-blank text the message is added to the list of
     * verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   string  the string to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier hasText(final String string, final String format, final Object... args)
    {
        boolean condition = (string != null) && (string.trim().length() > 0);
        isTrue(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified {@link java.util.Map Map} argument is empty. A map is empty if it
     * is null or if it has no entries. If the argument is not empty the message is added to the
     * list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   map     the map to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isEmpty(final Map<?, ?> map, final String format, final Object... args)
    {
        boolean condition = (map == null) || (map.isEmpty());
        isTrue(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified {@link java.util.Collection Collection} argument is empty. A
     * collection is empty if it is not null or if it has no entries. If the argument is not empty
     * the message is added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   collection  the collection to be validated
     * @param   format      verification failure message format
     * @param   args        verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isEmpty(final Collection<?> collection, final String format, final Object... args)
    {
        boolean condition = (collection == null) || collection.isEmpty();
        isTrue(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified {@link java.lang.String String} argument is empty. A string is
     * empty if it is null or if it has a length of zero. If the argument is not empty the message
     * is added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   string  the string to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isEmpty(final String string, final String format, final Object... args)
    {
        boolean condition = (string != null) && (string.length() > 0);
        isFalse(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified condition is false. If the condition is not false the message is
     * added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   condition  the condition to be validated
     * @param   format     verification failure message format
     * @param   args       verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isFalse(final boolean condition, final String format, final Object... args)
    {
        if (condition) {
            messages.add(String.format(format, args));
        }

        return this;
    }

    /**
     * Verifies that the specified {@link java.lang.String String} argument is not empty. A string
     * is not empty if it is not null and it has a length greater than zero. If the argument is
     * empty the message is added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   string  the string to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isNotEmpty(final String string, final String format, final Object... args)
    {
        boolean condition = (string != null) && (string.length() > 0);
        isTrue(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified {@link java.util.Collection Collection} argument is not empty. A
     * collection is not empty if it is not null and has one or more entries. If the argument is
     * empty the message is added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   collection  the collection to be validated
     * @param   format      verification failure message format
     * @param   args        verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isNotEmpty(final Collection<?> collection, final String format, final Object... args)
    {
        boolean condition = (collection == null) || collection.isEmpty();
        isFalse(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified {@link java.util.Map Map} argument is not empty. A map is not
     * empty if it is not null and has one or more entries. If the argument is empty the message is
     * added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   map     the map to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isNotEmpty(final Map<?, ?> map, final String format, final Object... args)
    {
        boolean condition = (map == null) || (map.isEmpty());
        isFalse(condition, format, args);

        return this;
    }

    /**
     * Verifies that the specified object is not null. If the object is null the message is added to
     * the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   object  the object to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isNotNull(final Object object, final String format, final Object... args)
    {
        isFalse((object == null), format, args);

        return this;
    }

    /**
     * Verifies that the specified object is null. If the object is not null the message is added to
     * the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   object  the object to be validated
     * @param   format  verification failure message format
     * @param   args    verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isNull(final Object object, final String format, final Object... args)
    {
        isTrue((object == null), format, args);

        return this;
    }

    /**
     * Verifies that the specified condition is true. If the condition is not true the message is
     * added to the list of verification failure messages.
     *
     * <p>The validation error message is formatted using {@link java.lang.String#format(String,
     * Object...) String.format(String, Object...)}</p>
     *
     * @param   condition  the condition to be validated
     * @param   format     verification failure message format
     * @param   args       verification failure message arguments
     *
     * @return  this object to facilitate fluent verification
     */
    public Verifier isTrue(final boolean condition, final String format, final Object... args)
    {
        if (! condition) {
            messages.add(String.format(format, args));
        }

        return this;
    }

    /**
     * Throw a verification exception when one or more verification tests had verification failures.
     * The exception message is the concatenated list of all of the verification failure messages.
     *
     * <p>Invokes {@link #throwIfError(String)} with an empty prefix message.</p>
     */
    public Verifier throwIfError() {
        return throwIfError("");
    }

    /**
     * Throw a verification exception when one or more verification tests had verification failures.
     * The specified message is prefixed to the messages associated with each verification failure
     * message. The exception message is the concatenated list of all of the verification failure
     * messages.
     *
     * @param   prefixMessage  message is prefixed to the verification failure messages
     *
     * @throws  IllegalArgumentException  thrown if any verification tests have failed
     */
    public Verifier throwIfError(final String prefixMessage)
    {
        if (messages.size() > 0) {
            StringBuilder exceptionMessage = new StringBuilder((prefixMessage == null)
                    ? "" : prefixMessage);
            String sp = (prefixMessage == null) ? "" : " ";

            for (String msg : messages) {
                exceptionMessage.append(sp)
                                .append(msg);
                sp = " ";
            }

            throw new IllegalArgumentException(exceptionMessage.toString());
        }
        
        return this;
    }
}
