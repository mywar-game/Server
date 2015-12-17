package com.fantingame.game.gamecenter.partener.fantin.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

/**
 * Common utilities so that we don't need to include Commons Lang.
 * 
 * @author Scott Battaglia
 * @version $Revision: 11729 $ $Date: 2007-09-26 14:22:30 -0400 (Tue, 26 Sep
 *          2007) $
 * @since 3.0
 */
public final class CommonUtils {

	private CommonUtils() {
		// nothing to do
	}

	public static String formatForUtcTime(final Date date) {
		final DateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	}

	/**
	 * Check whether the object is null or not. If it is, throw an exception and
	 * display the message.
	 * 
	 * @param object
	 *            the object to check.
	 * @param message
	 *            the message to display if the object is null.
	 */
	public static void assertNotNull(final Object object, final String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Check whether the collection is null or empty. If it is, throw an
	 * exception and display the message.
	 * 
	 * @param c
	 *            the collecion to check.
	 * @param message
	 *            the message to display if the object is null.
	 */
	public static void assertNotEmpty(final Collection<?> c,
			final String message) {
		assertNotNull(c, message);
		if (c.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the statement is true, otherwise throw an exception with the
	 * provided message.
	 * 
	 * @param cond
	 *            the codition to assert is true.
	 * @param message
	 *            the message to display if the condition is not true.
	 */
	public static void assertTrue(final boolean cond, final String message) {
		if (!cond) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Determines whether the String is null or of length 0.
	 * 
	 * @param string
	 *            the string to check
	 * @return true if its null or length of 0, false otherwise.
	 */
	public static boolean isEmpty(final String string) {
		return string == null || string.length() == 0;
	}

	/**
	 * Determines if the String is not empty. A string is not empty if it is not
	 * null and has a length > 0.
	 * 
	 * @param string
	 *            the string to check
	 * @return true if it is not empty, false otherwise.
	 */
	public static boolean isNotEmpty(final String string) {
		return !isEmpty(string);
	}

	/**
	 * Determines if a String is blank or not. A String is blank if its empty or
	 * if it only contains spaces.
	 * 
	 * @param string
	 *            the string to check
	 * @return true if its blank, false otherwise.
	 */
	public static boolean isBlank(final String string) {
		return isEmpty(string) || string.trim().length() == 0;
	}

	/**
	 * Determines if a string is not blank. A string is not blank if it contains
	 * at least one non-whitespace character.
	 * 
	 * @param string
	 *            the string to check.
	 * @return true if its not blank, false otherwise.
	 */
	public static boolean isNotBlank(final String string) {
		return !isBlank(string);
	}

	/**
	 * Constructs the URL to use to redirect to the CAS server.
	 * 
	 * @param casServerLoginUrl
	 *            the CAS Server login url.
	 * @param serviceParameterName
	 *            the name of the parameter that defines the service.
	 * @param serviceUrl
	 *            the actual service's url.
	 * @param renew
	 *            whether we should send renew or not.
	 * @param gateway
	 *            where we should send gateway or not.
	 * @return the fully constructed redirect url.
	 */
	public static String constructRedirectUrl(final String casServerLoginUrl,
			final String serviceParameterName, final String serviceUrl,
			final boolean renew, final boolean gateway) {
		try {
			return casServerLoginUrl
					+ (casServerLoginUrl.indexOf("?") != -1 ? "&" : "?")
					+ serviceParameterName + "="
					+ URLEncoder.encode(serviceUrl, "UTF-8")
					+ (renew ? "&renew=true" : "")
					+ (gateway ? "&gateway=true" : "");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Contacts the remote URL and returns the response.
	 * 
	 * @param constructedUrl
	 *            the url to contact.
	 * @param encoding
	 *            the encoding to use.
	 * @return the response.
	 */
	public static String getResponseFromServer(final URL constructedUrl,
			final String encoding) {
		return getResponseFromServer(constructedUrl, HttpsURLConnection
				.getDefaultHostnameVerifier(), encoding);
	}

	/**
	 * Contacts the remote URL and returns the response.
	 * 
	 * @param constructedUrl
	 *            the url to contact.
	 * @param hostnameVerifier
	 *            Host name verifier to use for HTTPS connections.
	 * @param encoding
	 *            the encoding to use.
	 * @return the response.
	 */
	public static String getResponseFromServer(final URL constructedUrl,
			final HostnameVerifier hostnameVerifier, final String encoding) {
		URLConnection conn = null;
		try {
			conn = constructedUrl.openConnection();
			if (conn instanceof HttpsURLConnection) {
				((HttpsURLConnection) conn)
						.setHostnameVerifier(hostnameVerifier);
			}
			final BufferedReader in;

			if (CommonUtils.isEmpty(encoding)) {
				in = new BufferedReader(new InputStreamReader(conn
						.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn
						.getInputStream(), encoding));
			}

			String line;
			final StringBuilder stringBuffer = new StringBuilder(255);

			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			return stringBuffer.toString();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null && conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
		}

	}

	/**
	 * Contacts the remote URL and returns the response.
	 * 
	 * @param url
	 *            the url to contact.
	 * @param encoding
	 *            the encoding to use.
	 * @return the response.
	 */
	public static String getResponseFromServer(final String url, String encoding) {
		try {
			return getResponseFromServer(new URL(url), encoding);
		} catch (final MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
