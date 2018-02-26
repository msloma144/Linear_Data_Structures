#Spell Checker (Michael Sloma)
###Extra Credit
* Table doubling with rehashing (when load factor reached)
* Nonstandard dictionary (can handle space and new line delineated)
* GUI (JavaFX)
* Check a misspelled word against a list of common misspellings.  
* "Real Time" spell checker when enter is pressed in output field
* Show table works when full or close to full (table doubling so it doesn't)
* Optimized input to use substantially less memory (uses on average half a line of memory per line on given dictionary)


###Usage
To use, specify a path to a dictionary and suggestion mapping list,
then specify the delineation for the dictionary file (" " or "\n").
Then press the ````Load```` button to load in the files to initialize
the spell checker.
At this point you can put text into the ````Output```` field, and when
````Enter```` is pressed, the spell checker will scan the input and
mark misspelled words with *'s. 
It will then suggest changes if it can match the misspelled word to a 
commonly misspelled word. These will be displayed in the ````Suggestions```` field.
####Example
Output Field
````
This is a test document.
How many dogs can fly?
Do you think this realy works?
````
````
This is a test document.
How many dogs can fly?
Do you think this *realy* works?
````
Suggestions Field
````
realy:
   really
   
````
####Notice
The ````Remaps```` value shown is the total number of collisions, including those
during the linear probing.
There is also code for reading from a document included in the Dictionary class. 