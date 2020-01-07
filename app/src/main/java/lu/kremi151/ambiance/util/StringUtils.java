/*
 * Copyright 2014 Michel Kremer (kremi151)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lu.kremi151.ambiance.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import android.util.Base64;

public class StringUtils {

	public static String doubleZero(int in, boolean chk){
		if(chk){
			if(in >= 0 && in < 10){
				return "0" + in;
			}else if(in <= 0 && in > -10){
				return "-0" + (in * (-1));
			}
		}
		return "" + in;
	}
	
	public static String showTime(long millis){
		long hr = TimeUnit.MILLISECONDS.toHours(millis);
		long min = TimeUnit.MILLISECONDS.toMinutes(millis - (hr * 3600000));
		long s = TimeUnit.MILLISECONDS.toSeconds(millis - (hr * 3600000) - (min * 60000));
		StringBuilder sb = new StringBuilder("");
		boolean c_chk = false;
		if(hr > 0){
			sb.append(hr + ":");
			c_chk = true;
		}
		sb.append(StringUtils.doubleZero((int)min, c_chk) + ":" + StringUtils.doubleZero((int)s, true));
		return sb.toString();
	}
	
	public static String SHA256 (String text) throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("SHA-256");

	    md.update(text.getBytes());
	    byte[] digest = md.digest();

	    return Base64.encodeToString(digest, Base64.DEFAULT);
	}
}
