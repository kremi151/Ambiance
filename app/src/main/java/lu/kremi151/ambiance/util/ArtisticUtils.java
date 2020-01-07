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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import lu.kremi151.ambiance.Ambiance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Base64;

public class ArtisticUtils {

	public static Bitmap getCircularBitmap(Bitmap in){
		int targetWidth = in.getWidth();
	    int targetHeight = in.getHeight();
	    Bitmap targetBitmap = Bitmap.createBitmap(
	        targetWidth,
	        targetHeight,
	        Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(targetBitmap);
	    Path path = new Path();
	    path.addCircle(
	        ((float)targetWidth - 2) / 2,
	        ((float)targetHeight - 2) / 2,
	        (Math.min(((float)targetWidth), ((float)targetHeight)) / 2),
	        Path.Direction.CCW);
	    canvas.clipPath(path);
	    Bitmap sourceBitmap = Bitmap.createBitmap(in);
	    canvas.drawBitmap(
	        sourceBitmap,
	        new Rect(0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight()),
	        new Rect(0, 0, targetWidth, targetHeight),
	        null);
	    return targetBitmap;
	}
	
	public static File getAlbumArtPath(AlbumMeta album){
		return new File(Ambiance.getAlbumArtFolder(), album.getName().toLowerCase()+"_"+album.getArtist().toLowerCase() + ".png");
	}
	
	@Deprecated
	public static File getAlbumArtPath(String album, String artist){
		return new File(Ambiance.getAlbumArtFolder(), album.toLowerCase()+"_"+artist.toLowerCase() + ".png");
	}
	
	public static File getArtistArtPath(String artist){
		return new File(Ambiance.getArtistArtFolder(), artist.toLowerCase() + ".png");
	}
	
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
