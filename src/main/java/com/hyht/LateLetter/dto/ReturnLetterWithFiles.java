package com.hyht.LateLetter.dto;

import com.hyht.LateLetter.entity.Letter;

import java.util.Map;

/**
 * 返回主体和文件
 */
public class ReturnLetterWithFiles {
    private Letter letter;
    private Map<String, Integer> files;

    public ReturnLetterWithFiles(Letter letter, Map<String, Integer> files) {
        this.letter = letter;
        this.files = files;
    }

    public ReturnLetterWithFiles() {
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public Map<String, Integer> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Integer> files) {
        this.files = files;
    }
}
