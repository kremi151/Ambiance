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

public class AlbumMeta {
	
	String album_art_path;
	String name;
	String artist;
	String album_key;

	public AlbumMeta(String name, String album_art_path, String artist, String album_key){
		this.album_art_path = album_art_path;
		this.name = name;
		this.artist = artist;
		this.album_key = album_key;
	}
	
	public boolean hasCoverArtOnSystem(){
		return album_art_path != null;
	}
	
	public String getCoverPath(){
		return album_art_path;
	}
	
	public String getName(){
		return name;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public String getAlbumKey(){
		return album_key;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof AlbumMeta){
			AlbumMeta a = (AlbumMeta)o;
			return a.artist.equals(artist) && a.name.equals(name);
		}
		return false;
	}
}
