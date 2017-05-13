package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private AudioManager am;
    private MediaPlayer mP;
    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mP.pause();
                        mP.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                        am.abandonAudioFocus(afChangeListener);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mP.start();
                    }
                }
            };
    private MediaPlayer.OnCompletionListener mpcl = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }

    };

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mP != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mP.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mP = null;
            am.abandonAudioFocus(afChangeListener);
        }
    }
    public PhrasesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */
        final ArrayList<Word> words = new ArrayList<Word>();
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        words.add(new Word("Where are you going?", "minto wuksus",-1,R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",-1,R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",-1,R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",-1,R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",-1,R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",-1,R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",-1,R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",-1,R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",-1,R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",-1,R.raw.phrase_come_here));
WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Word word = words.get(i);
        releaseMediaPlayer();

        int result = am.requestAudioFocus(afChangeListener,
        AudioManager.STREAM_MUSIC,
        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
        mP = MediaPlayer.create(getActivity(), word.getSoundId());
        mP.start();
        mP.setOnCompletionListener(mpcl);
        }
        });

        return rootView;
        }

        }