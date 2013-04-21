
package com.andrew.apollo;

import com.android.music.R;

/**
 * Listens for playback changes to send the the fragments bound to this activity
 */
public interface MusicStateListener {

    /**
     * Called when {@link MusicPlaybackService#REFRESH} is invoked
     */
    public void restartLoader();

    /**
     * Called when {@link MusicPlaybackService#META_CHANGED} is invoked
     */
    public void onMetaChanged();

}
