package com.example.android.miwok;

/**
 * Created by Marvi on 04.05.2017.
 */

public class Word {
    private final static int NO_IMG = -1;
    private String defaultWord;
    private String miwokWord;
    private int soundId;
    private int imageResourceId = NO_IMG;


    public Word(String mDefaultWord, String mMiwokWord, int mimageResourceId, int mSoundId) {
        defaultWord = mDefaultWord;
        miwokWord = mMiwokWord;
        imageResourceId = mimageResourceId;
        soundId = mSoundId;
    }

    public Word(String mDefaultWord, String mMiwokWord, int mSoundId) {
        defaultWord = mDefaultWord;
        miwokWord = mMiwokWord;
        imageResourceId = mSoundId;
    }

    public Word(String mDefaultWord, String mMiwokWord) {
        defaultWord = mDefaultWord;
        miwokWord = mMiwokWord;
    }

    public String getDefaultWord() {
        return defaultWord;
    }

    public void setDefaultWord(String defaultWord) {
        this.defaultWord = defaultWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setMiwokWord(String miwokWord) {
        this.miwokWord = miwokWord;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMG;
    }

    public int getSoundId() {
        return soundId;
    }
}
