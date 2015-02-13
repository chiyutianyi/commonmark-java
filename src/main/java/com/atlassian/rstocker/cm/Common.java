package com.atlassian.rstocker.cm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.regex.Pattern;

public class Common {

    private static final String ENTITY = "&(?:#x[a-f0-9]{1,8}|#[0-9]{1,8}|[a-z][a-z0-9]{1,31});";

    // foo: not sure about [ in [
    static final String ESCAPABLE = "[!\"#$%&\'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]";


    // foo: not sure about the backslashes here
    private static final Pattern reBackslashOrAmp = Pattern.compile("[\\\\&]");

    // foo: had flags 'gi' before
    private static final Pattern  reEntityOrEscapedChar = Pattern.compile("\\\\" + ESCAPABLE + '|' + ENTITY, Pattern.CASE_INSENSITIVE);

    static char unescapeChar(String s) {
        if (s.charAt(0) == '\\') {
            return s.charAt(1);
        } else {
            return s.charAt(1); // foo: entityToChar(s);
        }
    }

    // Replace entities and backslash escapes with literal characters.
    public static String unescapeString(String s) {
        // foo:
//        if (reBackslashOrAmp.matcher(s).find()) {
//            return s.replace(reEntityOrEscapedChar, unescapeChar);
//        } else {
            return s;
//        }
    }
    
    public static String normalizeURI(String uri) {
        try {
        	// foo: equivalent to encodeURI(decodeURI(uri))?
        	return new URI(uri).normalize().toString();
        } catch(URISyntaxException e) {
            return uri;
        }
    }
    
    private static Pattern whitespace = Pattern.compile("[ \t\r\n]+");

    public static String normalizeReference(String input) {
    	// foo: is this the same as JS?
    	return whitespace.matcher(input.toLowerCase(Locale.ROOT)).replaceAll(" ");
    }

}
