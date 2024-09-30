# CSC 420

Class projects for CSC420 - Data Structures and Algorithms

## Session 4

### Assignment 4

Create a randomized linked list that is able to get, insert, and remove elements at random.

```sh
.\gradlew.bat assignment4 # Windows
./gradlew assignment4 # Mac/Linux
```

## Session 3

### Assignment 3

Load a list of strings and sort them using buckets. Every string will be loaded into the first bucket, we loop through every bucket and Loop through 20 characters starting at the end, working backwards, putting them into a new bucket depending on the character. 'A/a' = 0, 'B/b' = 1 ... 'Z/z' = 25, ' ' = 26

By the end each bucket will be sorted, and you can loop through the buckets printing all their strings to get a fully sorted list of strings.

```sh
.\gradlew.bat assignment3 # Windows
./gradlew assignment3 # Mac/Linux
```

## Session 2

### Assignment 2 - Band Coordinator

Load a list of bands with the times their sets will take. Store them in a way that we can search band by name in O(log n) or better, and by set time in O(n) or better. We must also sort our collection by both band name and set time in O(n ^ 2) or better.

Sorting algorithm run down
```pseudo
sort(bands: list of bands)

    lowerList: list
    upperList: list

    # Add to upper or lower list based on comparison to first value in unsorted list
    middle: band = bands[0]
    for b in bands:
        if b.compareTo(middle) < 0: # Can compare either name or set length
            add b to lowerList
        else:
            add b to upperList

    if length of lowerList > 1:
        lowerList = sort(lowerList)
    if length of upperList > 1:
        upperList = sort(upperList)

    return lowerList + middle + upperList
```

I found it difficult to determine the time complexity of this function. I figured that it had to be O(n^2), but I wasn't sure. I verified this by counting every time a band is referenced in a loop, with 50 band names listed in bandinfo.txt, shuffling the list and sorting sub lists from length 1 to 50, plotting points as (x, y) = (length of list, total number of times 'for b in bands' loops). It matched x^2 * 1/3 almost perfectly, verifying that the function (likely) has the time complexity O(n^2).

This function is not very efficient, it is just simple, and done in a recursive and a sort of creative way. Every call, it allocates a list, likely taking up a majority of the operation time.

And upon more research, I've found that this is remarkably similar to QuickSort, with a few differences, as QuickSort works within the original list, only swapping elements.

```sh
.\gradlew.bat assignment2 # Windows
./gradlew assignment2 # Mac/Linux
```

## Session 1

### Assignment 1 - Random Name Generator

Load a list of first names and last names, and use them to generate full names and to sort namse by either first or last, and to group names by first.

```sh
.\gradlew.bat assignment1 # Windows
./gradlew assignment1 # Mac/Linux
```

Comments on what the code is doing are found in the System.out.print calls.

I can't help but add unicode to make my projects prettier. Apologies in advance.
