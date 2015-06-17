# Clay's Code Puzzles
I find that during interviews programmers like to have "white board" coding sessions around 
problems and puzzles to "test your thinking process". I think its a really bad way to understand
how people code. Generally I think if the best way to see how people code is to look at their code.
Give them enough time to think through the problem and create tests that act as a scaffold for 
assumptions, instead of expecting them to figure it all out stream of consciousness. Basically if 
you want someone who does that then you want someone who codes differently than I do, and IMHO 
not the best way to evaluate if someone can solve problems in code. Don't trust that they wrote 
the code? Check the commit list, if they commit regularly that's really hard to fake.

In a nut shell: Please stop giving me "white board" coding quizzes at interviews. I suck at them, 
even though I know I am a pretty good coder.

I wrote this project to accomplish a few things:

* Show as a portfolio the type of technologies I am good at and how I structure projects.
* Show that I can think through problems and build tests that will test that thinking process.
* Show that I can properly compartmentalize and document code.

Hopefully, this will be sufficient to make an assessment of my capabilities.

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
        "left":{
            "id":2,
            "left":{
                "id":4
            },
            "right":{
                "id":5
            }
        },
        "right":{
            "id":3,
            "left":{
                "id":6
            },
            "right":{
                "id":7
            }
        }
    }
   

