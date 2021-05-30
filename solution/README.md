# Kay solution

## Project setup
This **PexaChallenge** Java project was built in **Apache Netbeans 12.3**.

It is recommended to run the project in **Apache Netbeans 12.3**.

The project folder **PexaChallenge** can be found under `solution` directory.

## Output 
Output file name: `output.csv`. Output file will be generated in `graduate_coding/data` folder after the project is run. 

The output will look like this:
```CSV
SA, 2
QLD, 1
```
There is a `sample-output.csv` file under `graduate_coding/data` to show how the output file looks like.

## Thoughts on the problem
Firstly, the program reads the JSON data. Then store them in a JSON array. For each object in the JSON array, I need to extract the address value.
In the address field, the State is always in the second position after the first comma. So, I need to split the `Address` string to get the second value (State).  After that, I store all State values (includes duplicated values) into a new array. This `stateArray` will look like this: `{SA, SA, QLD}`.

Next, there are two possible solutions that I thought of:

1. The first solution is creating a separate boolean array that has the same length as the `stateArray` to check whether each element in the `stateArray` is visited or not.

Pseudo-code:
```java
boolean visitedArray[] = new boolean[stareArray.length]
assign all elements in visitedArray = false
for(i = 0, i < stateArray.length, i++){
	//if the element is already visited (visitedArray[i]=true) then skip
	if (visitedArray[i] = false){
		int count = 1
		for (j = i+1, j < stateArray.lengh, j++){
			if(array[i] = array[j]){	
				//everytime the element appears again, the corresponding visited element will be set to true and its occurrence increases by 1
				visitedArray[j] = true
				count = count + 1
			}
		}
		//write to csv file 
		writer.append(stateArray[i] + "," + count + "\n")
	}	
}
```

2. The second solution is using Hashmap. **This is the chosen solution to build my Java project.**

Each element in the hashmap will be stored in pair: key (state) and value (occurrence). 
Since each hashmap key is unique, it will make the update easier. If the program finds a match key, it can update the hashmap element using the same key and override value (increase by 1): `(key, occurrence + 1)`.
After being updated, the hashmap will look like this `{SA=2, QLD=1}`.

The reason I chose the second solution is that it has fewer lines of code. 
Also, there's a HashMap method called `containskey()` that can be used to check whether a key is being mapped or not. Thanks to this method, the program only needs to loop through the `stateArray` once. 

## References
[How can I read comma separated values from a text file in Java?](https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java)

[Iterator Funtions in Java](https://www.tutorialspoint.com/iterator-functions-in-java)

[Interface JsonParser](https://docs.oracle.com/javaee/7/api/javax/json/stream/JsonParser.html)

[Java HashMap](https://www.w3schools.com/java/java_hashmap.asp)


