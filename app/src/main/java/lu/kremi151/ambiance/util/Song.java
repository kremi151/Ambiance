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
import java.security.NoSuchAlgorithmException;

import lu.kremi151.ambiance.Ambiance;
import lu.kremi151.ambiance.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Song implements Comparable<Song>{

	private String title;
	private String artist;
	private Bitmap cover;
	private String path;
	private int duration;
	private String id;
	private String album_key;
	
	public Song(String path, String title, String artist, String album_key, int duration){
		this.title=title;
		this.artist=artist;
		this.path = path;
		this.duration = duration;
		this.album_key = album_key;
		try {
			id = StringUtils.SHA256(path);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String getTitle(){
		return title;
	}
	
	public AlbumMeta getAlbum(){
		return Ambiance.getMediaDatabase().getAlbum(album_key);
	}
	
	public String getArtist(){
		return artist;
	}
	
	public String getPath(){
		return path;
	}
	
	public String getAlbumKey(){
		return album_key;
	}
	
	public Bitmap getCover(Context c){
		if(cover == null){
			File path = ArtisticUtils.getAlbumArtPath(getAlbum());
			boolean load = false;
			if(path.exists()){
				try{
					cover = BitmapFactory.decodeFile(path.getAbsolutePath());
					load = true;
				}catch(Throwable t){}
			}
			if(!load)cover = ((BitmapDrawable)c.getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
		}
		return cover;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public String getID(){
		return id;
	}

	@Override
	public int compareTo(Song arg0) {
		return title.compareTo(arg0.title);
	}
	
}
