package me.chanjar.oas.server.validator.core.encoder;

import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;

/**
 * <a href="https://tools.ietf.org/html/rfc3986#section-2">RFC3986</a> percent-encoder.
 * <br/>
 * Code copy from {@link java.net.URLEncoder}
 */
public class PercentEncoder {

  /**
   * Don't allow <a href="https://tools.ietf.org/html/rfc3986#section-2.2">Reserved Characters</a> encoder
   */
  public static final PercentEncoder DONT_ALLOW_RESERVED;

  /**
   * Allow <a href="https://tools.ietf.org/html/rfc3986#section-2.2">Reserved Characters</a> encoder
   */
  public static final PercentEncoder ALLOW_RESERVED;

  static final int caseDiff = ('a' - 'A');
  static final String dfltEncName = "UTF-8";

  private final BitSet dontNeedEncoding;

  private PercentEncoder(BitSet dontNeedEncoding) {
    this.dontNeedEncoding = dontNeedEncoding;
  }

  static {

    /* Reserved characters:
     * see: https://tools.ietf.org/html/rfc3986#section-2.2
     */
    BitSet reserved = new BitSet(256);
    reserved.set(':');
    reserved.set('/');
    reserved.set('?');
    reserved.set('#');
    reserved.set('[');
    reserved.set(']');
    reserved.set('@');
    reserved.set('!');
    reserved.set('$');
    reserved.set('&');
    reserved.set('\'');
    reserved.set('(');
    reserved.set(')');
    reserved.set('*');
    reserved.set('+');
    reserved.set(',');
    reserved.set(';');
    reserved.set('=');

    /* Unreserved characters
     * see: https://tools.ietf.org/html/rfc3986#section-2.3
     */
    BitSet unreserved = new BitSet(256);
    int i;
    for (i = 'a'; i <= 'z'; i++) {
      unreserved.set(i);
    }
    for (i = 'A'; i <= 'Z'; i++) {
      unreserved.set(i);
    }
    for (i = '0'; i <= '9'; i++) {
      unreserved.set(i);
    }
    unreserved.set('-');
    unreserved.set('_');
    unreserved.set('.');
    unreserved.set('~');

    BitSet dontNeedEncoding1 = new BitSet(256);
    dontNeedEncoding1.or(reserved);
    dontNeedEncoding1.or(unreserved);
    ALLOW_RESERVED = new PercentEncoder(dontNeedEncoding1);

    BitSet dontNeedEncoding2 = new BitSet(256);
    dontNeedEncoding2.or(unreserved);
    DONT_ALLOW_RESERVED = new PercentEncoder(dontNeedEncoding2);

  }

  public String encode(String s) {

    String str = null;

    try {
      str = encode(s, dfltEncName);
    } catch (UnsupportedEncodingException e) {
      // The system should always have the platform default
    }

    return str;
  }

  private String encode(String s, String enc)
      throws UnsupportedEncodingException {

    boolean needToChange = false;
    StringBuffer out = new StringBuffer(s.length());
    Charset charset;
    CharArrayWriter charArrayWriter = new CharArrayWriter();

    if (enc == null)
      throw new NullPointerException("charsetName");

    try {
      charset = Charset.forName(enc);
    } catch (IllegalCharsetNameException e) {
      throw new UnsupportedEncodingException(enc);
    } catch (UnsupportedCharsetException e) {
      throw new UnsupportedEncodingException(enc);
    }

    for (int i = 0; i < s.length(); ) {
      int c = (int) s.charAt(i);
      //System.out.println("Examining character: " + c);
      if (dontNeedEncoding.get(c)) {
        //System.out.println("Storing: " + c);
        out.append((char) c);
        i++;
      } else {
        // convert to external encoding before hex conversion
        do {
          charArrayWriter.write(c);
          /*
           * If this character represents the start of a Unicode
           * surrogate pair, then pass in two characters. It's not
           * clear what should be done if a bytes reserved in the
           * surrogate pairs range occurs outside of a legal
           * surrogate pair. For now, just treat it as if it were
           * any other character.
           */
          if (c >= 0xD800 && c <= 0xDBFF) {
                        /*
                          System.out.println(Integer.toHexString(c)
                          + " is high surrogate");
                        */
            if ((i + 1) < s.length()) {
              int d = (int) s.charAt(i + 1);
                            /*
                              System.out.println("\tExamining "
                              + Integer.toHexString(d));
                            */
              if (d >= 0xDC00 && d <= 0xDFFF) {
                                /*
                                  System.out.println("\t"
                                  + Integer.toHexString(d)
                                  + " is low surrogate");
                                */
                charArrayWriter.write(d);
                i++;
              }
            }
          }
          i++;
        } while (i < s.length() && !dontNeedEncoding.get((c = (int) s.charAt(i))));

        charArrayWriter.flush();
        String str = new String(charArrayWriter.toCharArray());
        byte[] ba = str.getBytes(charset);
        for (int j = 0; j < ba.length; j++) {
          out.append('%');
          char ch = Character.forDigit((ba[j] >> 4) & 0xF, 16);
          // converting to use uppercase letter as part of
          // the hex value if ch is a letter.
          if (Character.isLetter(ch)) {
            ch -= caseDiff;
          }
          out.append(ch);
          ch = Character.forDigit(ba[j] & 0xF, 16);
          if (Character.isLetter(ch)) {
            ch -= caseDiff;
          }
          out.append(ch);
        }
        charArrayWriter.reset();
        needToChange = true;
      }
    }

    return (needToChange ? out.toString() : s);
  }

}
