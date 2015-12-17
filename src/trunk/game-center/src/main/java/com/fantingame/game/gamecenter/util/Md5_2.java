/*******************************************************************************
 * Copyright (c) 2013 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.fantingame.game.gamecenter.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5_2 {

	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	public static String encode(String string) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			return bytesToHexString(digest.digest(string.getBytes()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encode(File file) {
		FileInputStream in = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(file);
			byte[] buff = new byte[1024];
			while (in.read(buff) > 0) {
				out.write(buff);
			}
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] b = out.toByteArray();
			return bytesToHexString(digest.digest(b));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			in = null;
		}
		return null;
	}

	private static String bytesToHexString(byte[] bytes) {
		final char[] buf = new char[bytes.length * 2];

		byte b;
		int c = 0;
		for (int i = 0, z = bytes.length; i < z; i++) {
			b = bytes[i];
			buf[c++] = DIGITS[(b >> 4) & 0xf];
			buf[c++] = DIGITS[b & 0xf];
		}

		return new String(buf);
	}

}
