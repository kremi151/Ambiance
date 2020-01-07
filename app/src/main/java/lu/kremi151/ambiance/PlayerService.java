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

package lu.kremi151.ambiance;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.ArrayList;

import lu.kremi151.ambiance.playlist.BasePlaylist;
import lu.kremi151.ambiance.playlist.PlaylistDatabase;
import lu.kremi151.ambiance.util.Song;
import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.PowerManager;
import android.util.Log;

public class PlayerService extends Service implements
MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
MediaPlayer.OnCompletionListener{
	
	//http://code.tutsplus.com/tutorials/create-a-music-player-on-android-song-playback--mobile-22778
	
	private final IBinder musicBind = new PlayerBinder();
	
	//media player
	private MediaPlayer player;
	//song list
	private BasePlaylist songs;

	@Override
	public IBinder onBind(Intent arg0) {
		return musicBind;
	}
	
	@Override
	public boolean onUnbind(Intent intent){
		player.stop();
		player.release();
		return false;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		songs = new PlaylistDatabase();
		//create player
		player = new MediaPlayer();
		initMusicPlayer();
	}
	
	public void initMusicPlayer(){
		player.setWakeMode(getApplicationContext(),
		PowerManager.PARTIAL_WAKE_LOCK);
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		player.setOnPreparedListener(this);
		player.setOnCompletionListener(this);
		player.setOnErrorListener(this);
	}
	
	public void setList(BasePlaylist theSongs){
		 songs=theSongs;
	}
	
	public void playSong(){
		player.reset();
		try{
			player.setDataSource(songs.getCurrentSong().getPath());
		}catch(Exception e){
			Log.e("MUSIC SERVICE", "Error setting data source", e);
		}
		player.prepareAsync();
	}
	
	public void setSongFromPlaylist(BasePlaylist pl, int songIndex){
		songs = pl;
		songs.setIndex(songIndex);
	}
	
	@Deprecated
	public void setSong(int songIndex){
		songs.setIndex(songIndex);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}
	
	public class PlayerBinder extends Binder {
		public PlayerService getService() {
			return PlayerService.this;
		}
	}

}
