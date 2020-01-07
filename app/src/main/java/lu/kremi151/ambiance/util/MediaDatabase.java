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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lu.kremi151.ambiance.playlist.PlaylistDatabase;

import android.content.Context;
import android.provider.MediaStore;

public class MediaDatabase {

	private PlaylistDatabase pldb;
	private Context m;
	private Song[] pl;
	private Map<String, AlbumMeta> albums;
	private List<String> album_tokens;
	private List<String> artists;
	
	public MediaDatabase(Context m){
		this.m = m;

		rebuild();
	}
	
	public void rebuild(){
		albums = AudioUtils.fetchAlbums(m);
		album_tokens = new ArrayList<String>();
		for(AlbumMeta am : albums.values()){
			String token = am.getName().toLowerCase() + "_" + am.getArtist().toLowerCase();
			if(!album_tokens.contains(token))album_tokens.add(token);
		}
		artists = new ArrayList<String>();
		
		Object[] pll = AudioUtils.fetchMusic(m).toArray();
		pl = Arrays.copyOf(pll, pll.length, Song[].class);
		Arrays.sort(pl);
		for(Song p : pl){
			if(!artists.contains(p.getArtist()))artists.add(p.getArtist());
		}
	}
	
	public Song[] getListForAlbum(AlbumMeta album){
		ArrayList<Song> res = new ArrayList<Song>();
		for(Song p : pl){
			if(p.getAlbum().equals(album)){
				res.add(p);
			}
		}
		return Arrays.copyOf(res.toArray(), res.size(), Song[].class);
	}
	
	public Song[] getListForArtist(String artist){
		ArrayList<Song> res = new ArrayList<Song>();
		for(Song p : pl){
			if(p.getArtist().equals(artist)){
				res.add(p);
			}
		}
		return Arrays.copyOf(res.toArray(), res.size(), Song[].class);
	}
	
	public AlbumMeta[] getAlbums(){
		Object[] o = albums.values().toArray();
		return Arrays.copyOf(o, albums.size(), AlbumMeta[].class);
	}
	
	public AlbumMeta getAlbum(String key){
		return albums.get(key);
	}
	
	public String[] getArtists(){
		return Arrays.copyOf(artists.toArray(), artists.size(), String[].class);
	}
	
	public Song[] getPlayableList(){
		return pl;
	}
	
	public boolean hasAlbum(String album, String artist){
		return album_tokens.contains(album.toLowerCase() + "_" + artist.toLowerCase());
	}
	
	public boolean hasArtist(String artist){
		return artist.contains(artist.toLowerCase());
	}
	
	public PlaylistDatabase generatePlaylist(){
		if(pldb == null){
			pldb = new PlaylistDatabase();
		}
		return pldb;
	}
	
}
