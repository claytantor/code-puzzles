package com.claytantor.codepuzzles;

import com.claytantor.codepuzzles.model.BinaryTreeNode;
import com.claytantor.codepuzzles.model.WordCount;
import com.claytantor.codepuzzles.services.BinaryTreeService;
import com.claytantor.codepuzzles.services.WordSearchService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by claytongraham on 6/16/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class WordSearchServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WordSearchService wordSearchService;

    private char[][] testGrid01 = {
            {'T', 'H', 'A', 'T', 'X'},
            {'H', 'A', 'T', 'X', 'X'},
            {'I', 'X', 'H', 'E', 'R'},
            {'S', 'X', 'X', 'E', 'E'},
            {'O', 'T', 'H', 'E', 'R'},
            {'R', 'E', 'H', 'X', 'A'},
    };

    private String[] dict01 = {"that","hat","her","other"};
    private String[] dict02 = {"this","are","ex"};

    @Test
    public void canFindWordsHorizontal() {
        Set<String> words = new HashSet<>();
        words.addAll(Arrays.asList(dict01));
        List<WordCount> foundWords =
                wordSearchService.findWordsHorizontal(testGrid01, words);
        assertThat(foundWords.size(), equalTo(4));


        //use for lookup
        Map<String,WordCount> testMap = new HashMap<String, WordCount>();
        for(WordCount word:foundWords)
            testMap.put(word.getWord(), word);
        assertThat(testMap.get("that").getCount(), equalTo(1));
        assertThat(testMap.get("hat").getCount(), equalTo(2));

        //includes backwards test
        assertThat(testMap.get("her").getCount(), equalTo(3));

    }


    @Test
    public void canTransposeVertical() {
        char[][] transposed = wordSearchService.transposeHorizontal(testGrid01);
    }

    public void canFindWordsVertical() {
        Set<String> words = new HashSet<>();
        words.addAll(Arrays.asList(dict02));
        List<WordCount> foundWords =
                wordSearchService.findWordsVertical(testGrid01, words);
        assertThat(foundWords.size(), equalTo(3));


        //use for lookup
        Map<String,WordCount> testMap = new HashMap<String, WordCount>();
        for(WordCount word:foundWords)
            testMap.put(word.getWord(), word);
        assertThat(testMap.get("this").getCount(), equalTo(1));
        assertThat(testMap.get("are").getCount(), equalTo(1));

        //includes backwards test
        assertThat(testMap.get("ex").getCount(), equalTo(2));

    }


}
