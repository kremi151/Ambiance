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

package lu.kremi151.ambiance.playlist;

import lu.kremi151.ambiance.util.Song;

public abstract class BasePlaylist {

	/**
	 * Points the index to the next position
	 * @return Returns true if the end of the playlist has been reached
	 */
	public abstract boolean next();
	
	/**
	 * Points the index to the previous position
	 * @return Returns true if the beginning of the playlist has been reached
	 */
	public abstract boolean rewind();
	
	/**
	 * Points the index to the specified value
	 * @param idx The new index
	 */
	public abstract void setIndex(int idx);
	
	/**
	 * Returns the current index
	 * @return
	 */
	public abstract int getIndex();
	
	public abstract int getSize();
	
	/**
	 * Gets the song of for the current index
	 * @return The song for the current index
	 */
	public abstract Song getCurrentSong();
}
