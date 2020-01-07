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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lu.kremi151.ambiance.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

public class AudioUtils {

	public static Bitmap readAudioCover(Context c, File f){
		return ((BitmapDrawable)c.getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
	}
	
	public static Map<String, AlbumMeta> fetchAlbums(Context c){
		//Some audio may be explicitly marked as not being music
//		String selection = MediaStore.Audio.Albums. + " != 0";

		String[] projection = {
				MediaStore.Audio.Media._ID,
				MediaStore.Audio.Albums.ALBUM,
				MediaStore.Audio.Albums.ALBUM_ART,
				MediaStore.Audio.Albums.ARTIST,
				MediaStore.Audio.Albums.ALBUM_KEY
		};
		
		Cursor cursor = c.getContentResolver().query(
				MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
				projection,
				null,
				null,
				null);

		Map<String, AlbumMeta> albums = new HashMap<String, AlbumMeta>();
		while(cursor.moveToNext()){
//			songs.add(cursor.getString(0) + "||" + cursor.getString(1) + "||" +   cursor.getString(2) + "||" +   cursor.getString(3) + "||" +  cursor.getString(4) + "||" +  cursor.getString(5));
			albums.put(cursor.getString(4),
					new AlbumMeta(
							cursor.getString(1),
							cursor.getString(2),
							cursor.getString(3),
							cursor.getString(4)
							));
		}
		cursor.close();
		return Collections.unmodifiableMap(albums);
	}
	
	public static List<Song> fetchMusic(Context c){
		//Some audio may be explicitly marked as not being music
		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

		String[] projection = {
		        MediaStore.Audio.Media._ID,
		        MediaStore.Audio.Media.ARTIST,
		        MediaStore.Audio.Media.TITLE,
		        MediaStore.Audio.Media.DATA,
		        MediaStore.Audio.Media.DISPLAY_NAME,
		        MediaStore.Audio.Media.DURATION,
		        MediaStore.Audio.Media.ALBUM,
		        MediaStore.Audio.Media.ALBUM_KEY
		};

		Cursor cursor = c.getContentResolver().query(
		        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
		        projection,
		        selection,
		        null,
		        null);

		List<Song> songs = new ArrayList<Song>();
		while(cursor.moveToNext()){
//		        songs.add(cursor.getString(0) + "||" + cursor.getString(1) + "||" +   cursor.getString(2) + "||" +   cursor.getString(3) + "||" +  cursor.getString(4) + "||" +  cursor.getString(5));
			songs.add(new Song(cursor.getString(3), cursor.getString(2), cursor.getString(1), cursor.getString(7), Integer.parseInt(cursor.getString(5))));
		}
		cursor.close();
		return Collections.unmodifiableList(songs);
	}
}
