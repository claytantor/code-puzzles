package com.claytantor.codepuzzles.services;

import com.claytantor.codepuzzles.model.WordCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by claytongraham on 6/17/15.
 */

@Component
public class WordSearchService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Set<String> getWordsFromStream(InputStream inputStream){
        return null;
    }

    public Set<String> getWordsFromString(String dict){
        return null;
    }

    public char[][] getGridFromStream(InputStream inputStream){
        return null;
    }

    public char[][] getGridFromString(String gridString){
        return null;
    }

    public List<WordCount> findWordCounts(char[][] grid, Set<String> words){

        //create the word counts
        List<WordCount> allWordCounts = new ArrayList<>();

        //get all the words vertical
        allWordCounts.addAll(findWordsVertical(grid, words));

        //get all the words horiz
        allWordCounts.addAll(findWordsHorizontal(grid, words));

        //get all the words diag
        allWordCounts.addAll(findWordsDiagonal(grid, words));


        return allWordCounts;
    }


    /**
     * The map allows us to do a little majik where we can build up the counts for
     * Words on each line and it will act like a set
     *
     * @param grid
     * @param dict
     * @return
     */
    public List<WordCount> findWordsHorizontal(char[][] grid, Set<String> dict){

        Map<String, WordCount> verticalMap = new HashMap<>();

        // go both directions over the rows
        for(int col = 0; col < grid.length ; col++){
            char[] row = grid[col];
            //go left to right
            String l2r = "";
            for (int i = 0; i < row.length ; i++) {
                l2r = l2r.concat(Character.toString(row[i]));
                //log.debug(MessageFormat.format("dict {0} word:{1}", dict.toString(), l2r));
                for(String item:dict){
                    //log.debug(MessageFormat.format("dict {0} word:{1} item:{2}", dict.toString(), l2r, item));
                    if(l2r.toLowerCase().contains(item)){
                        //log.debug(MessageFormat.format("word {0} contains item:{1}", l2r, item));
                        addWordcount(verticalMap, item, col, "l2r");
                    }
                }
            }

            String r2l = "";
            //go right to left
            for (int i = row.length-1; i >= 0 ; i--) {
                r2l = r2l.concat(Character.toString(row[i]));
                for(String item:dict){
                    //log.debug(MessageFormat.format("dict {0} word:{1} item:{2}", dict.toString(), l2r, item));
                    if(r2l.toLowerCase().contains(item)){
                        //log.debug(MessageFormat.format("word {0} contains item:{1}", r2l, item));
                        addWordcount(verticalMap, item, col, "r2l");
                    }
                }
            }
        }

        Map<String, WordCount> consoldated = new HashMap<>();
        consolidateList(new ArrayList<WordCount>(verticalMap.values()), consoldated);
        return new ArrayList<WordCount>(consoldated.values());
    }

    /**
     * the reason we use a key is because we only want to read the word one time on a specific
     * line and direction ie. HER, HERX, and HERXX should only register HER once.
     *
     * @param verticalMap
     * @param matchWord
     * @param line
     * @param direction
     */
    public void addWordcount(Map<String, WordCount> verticalMap, String matchWord, int line, String direction){

        String key = MessageFormat.format("{0}_{1}_{2}", matchWord, line, direction);
        WordCount item = verticalMap.get(key);

        //only allow one per direction and line
        if(item == null) {
            //log.debug(key);
            item = new WordCount(1,matchWord.toLowerCase());
            verticalMap.put(key,item);
        }
    }

    /**
     * All the counters return a list of words, this is used to consolodate those to
     * a target map. This is so the vertical, horizontal and diagonal counters can
     * all consolodate using the same method.
     *
     * @TODO consider using a lambda function instead
     *
     * @param wordCounts - Linear counts from each type of word counter
     * @param wordCountMap - The target map to consolodate into
     */
    public void consolidateList(List<WordCount> wordCounts, Map<String, WordCount> wordCountMap){

        for(WordCount wordCount:wordCounts){
            //log.debug(wordCount.getWord());
            WordCount item = wordCountMap.get(wordCount.getWord());
            if(item != null)
                item.increment();
            else
                wordCountMap.put(wordCount.getWord(),wordCount);
        }

    }


    public List<WordCount> findWordsVertical(char[][] grid, Set<String> words){

        return null;
    }

    /**
     * transpose horizontal will turn columns into rows and
     * rows into columns.
     *
     * @param grid
     * @return
     */
    public char[][] transposeHorizontal(char[][] grid){

        List<List<Character>> transposedRows = new ArrayList<>();
        if (grid.length > 0) {
            for (int i = 0; i < grid[0].length; i++) {
                List<Character> transposedRow = new ArrayList<>();
                for (int j = 0; j < grid.length; j++) {
                    //reverse order
                    transposedRow.add(new Character(grid[j][i]));
                }
                transposedRows.add(transposedRow);
            }
        }

        //get the first row to se
        List<Character> row = transposedRows.get(0);
        char[][] newgrid = new char[transposedRows.size()][row.size()];
        return newgrid;
    }



    public List<WordCount> findWordsDiagonal(char[][] grid, Set<String> words){
        return null;
    }

    public char[][] transposeDiagonal(char[][] grid){
        return null;
    }



}
