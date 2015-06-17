# README

## Graph System

Find the least common ancestors of 2 nodes in a binary tree. 
The goal here is to identify the fewest similar nodes that are in 
common above the nodes chosen.

    1
    |
    |---2(L)
    |   |
    |   |---4(L)
    |   |
    |   |---5(R)
    |
    |---3(R)
        |
        |---6(L)
        |
        |---7(R)
  
As a JSON data structure:
        
    { 
        "id":1, 
        "children": [
            {
                "id":2,
                "children":[
                    {
                        "id":4
                    },
                    {
                        "id":5
                    }                                        
                ]
            },
            {
                "id":3,
                "children":[
                    {
                        "id":6
                    },
                    {
                        "id":7
                    }                                        
                ]
            }            
        ]
    }
   

## Code Puzzle: Series of numbers… 1, 2, 3, 4
 
Numbers match up to associated indexes in the alphabet… 1 is A and 2 is B
Figure out all the different letters represented by the input, the digit string
Different combinations of that digit string
Using any language you want… NOT pseudocode
 
 
## Code Puzzle: TeleWord
 
This one’s a variation on TeleWord puzzles. Recall that a Teleword is something like this:

    T H A T X
    H A T X X
    I X H X X
    S X X E X
    O T H E R
 
You’re given a list of words, say: this, that, hat, other, her. The task is to find the number of occurrences of each word in the matrix, horizontally, vertically, and diagonally. So in the answer, “that” occurs only once, while “hat” and “her” both occur a couple of times.
 