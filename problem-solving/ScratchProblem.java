import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.*;
public class ScratchProblem {

    public static ArrayList<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes,
            List<String> quotes) {

        List<String> bestToys = new ArrayList<>();
        Map<String, Integer> toysCount = new HashMap<>();
        Map<String, Integer> sortedToysCount = new HashMap<>();
        ArrayList<String> sanitizedQuotes = sanitizeQuotes(quotes);
        if (topToys >= toys.size()) {
            sanitizedQuotes.stream().forEach(quote -> {
                toys.forEach(toy -> {
                    if (quote.toLowerCase().contains(toy.toLowerCase())) {
                        bestToys.add(toy);
                    }
                });
            });
            return (ArrayList<String>) bestToys.stream().distinct().collect(Collectors.toList());
        }
        sanitizedQuotes.stream().forEach(quote -> {
            String[] wordsFromQuote = quote.split(" ");
            Arrays.stream(wordsFromQuote).forEach(singleWord -> {
                if (toys.contains(singleWord.toLowerCase())) {
                    toysCount.put(singleWord.toLowerCase(), toysCount.getOrDefault(singleWord.toLowerCase(), 0) + 1);
                }
            });
        });
        toysCount.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedToysCount.put(x.getKey(), x.getValue()));

        sortedToysCount.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(toy -> bestToys.add(toy.getKey()));
        System.out.println(sortedToysCount);
        return new ArrayList<>(bestToys.subList(0, topToys));
    }

    public static ArrayList<String> popularNToysWithTrie(int numToys, int topToys, List<String> toys, int numQuotes,
            List<String> quotes) {

        List<String> bestToys = new ArrayList<>();
        Map<String, Integer> toysCount = new HashMap<>();
        Map<String, Integer> sortedToysCount = new HashMap<>();
        ArrayList<String> sanitizedQuotes = sanitizeQuotes(quotes);
        SuffixTrie trieToys = new SuffixTrie();
        toys.forEach(toy -> {
            trieToys.insertIntoTrie(toy.toLowerCase());
        });
        if (topToys >= toys.size()) {
            sanitizedQuotes.stream().forEach(quote -> {
                toys.forEach(toy -> {
                    if (quote.toLowerCase().contains(toy)) {
                        bestToys.add(toy);
                    }
                });
            });
            return (ArrayList<String>) bestToys.stream().distinct().collect(Collectors.toList());
        }
        sanitizedQuotes.stream().forEach(quote -> {
            String[] wordsFromQuote = quote.split(" ");
            Arrays.stream(wordsFromQuote).forEach(singleWord -> {
                if (trieToys.contains(singleWord.toLowerCase())) {
                    toysCount.put(singleWord.toLowerCase(), toysCount.getOrDefault(singleWord.toLowerCase(), 0) + 1);
                }
            });
        });
        toysCount.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedToysCount.put(x.getKey(), x.getValue()));
        sortedToysCount.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(toy -> bestToys.add(toy.getKey()));
        return new ArrayList<>(bestToys.subList(0, topToys));
    }

    public static ArrayList<String> sanitizeQuotes(List<String> quotes) {
        return (ArrayList<String>) quotes.stream().map(x -> x.replaceAll("[^a-zA-Z\\d\\s:]", ""))
                .collect(Collectors.toList());
    }

    public static Optional<String> createOptional(String str) {
        return Optional.ofNullable(str);
    }

    public static ArrayList<String> getHugeListOfQuotes() {
        ArrayList<String> quotes = new ArrayList<>(Arrays.asList("A liked the batman new toy!.",
        "I loved the new elsa toy", "Loved the new Elsa toy", "I love playing with my minions",
        "My prefered minions for sleeping", "Minions are my passion.", "I love my barco"));
       
        for (int i = 0; i < 1000000; i++) {
        quotes.add("I love playing with my minions");
        }
        return quotes;
    }

    public static ArrayList<String> getHugeListOfToys() {
        ArrayList<String> toys = new ArrayList<>();
        toys.add("elsa");
        toys.add("minions");
        toys.add("batman");
        toys.add("barco");
        for (int i = 0; i < 200; i++) {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        toys.add(generatedString);
        }
        return toys;
    }

    static int minDays(List<List<Integer> > grid) {

        Queue<Integer[]> queue = new LinkedList<>();
        int target = grid.size() * grid.get(0).size();
        int count = 0;
        int res = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if(grid.get(i).get(j) == 1){
                    queue.add(new Integer[]{i, j});
                    count++;
                }
            }
        }
        int dirs[][] = {{1,0}, {-1,0}, {0,-1}, {1,0}};
        while(!queue.isEmpty()){
            int size = queue.size();
            if(count == target) return res;
            for(int i = 0; i < size;i++){
                Integer[] curr = queue.remove();
                for(int[] dir : dirs){
                    int ci = curr[0] + dir[0];
                    int cj = curr[1] + dir[1];
                    if(ci >= 0 && ci < grid.size() && cj >= 0 && cj < grid.get(0).size() && grid.get(ci).get(cj) == 0){
                        count++;
                        queue.add(new Integer[]{ci, cj});
                        grid.get(ci).set(cj, 1);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    static int maxSubArraySum(int a[]) {
        int currentSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            currentSum = currentSum + a[i];
            if (currentSum > max)
                max = currentSum;
            if (currentSum < 0)
                currentSum = 0;
        }
        return max;
    }

    public static boolean balancedBrackets(String str) {
        List<Character> stack = new ArrayList<>();
        String openingBrackets = "{[(";
        String closingBrackets = "}])";
        Map<Character, Character> matchChars = new HashMap<>();
        matchChars.put('}', '{');
        matchChars.put(']', '[');
        matchChars.put(')', '(');
        for (int i = 0; i < str.length(); i++) {
            Character letter = str.charAt(i);
            if (openingBrackets.indexOf(letter) != -1) {
                stack.add(letter);
            } else if (closingBrackets.indexOf(letter) != -1) {
                if (stack.isEmpty())
                    return false;
                if (stack.get(stack.size() - 1) == matchChars.get(letter)) {
                    stack.remove(stack.size() - 1);
                } else {
                    return false;
                }

            }
        }
        return stack.isEmpty();
    }

    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                return new int[] { row, col };
            }
        }
        return new int[] { -1, -1 };
    }

    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        List<Integer[]> triplets = new ArrayList<>();
        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int sum = array[i] + array[left] + array[right];
                if (sum > targetSum) {
                    right--;
                } else if (sum < targetSum) {
                    left++;
                } else {
                    triplets.add(new Integer[] { array[i], array[left], array[right] });
                    left++;
                    right--;
                }
            }
        }
        return triplets;
    }

    public static int[] largestRange(int[] array) {
        int results [] = new int[2];
            Map<Integer, Boolean> map = new HashMap<>();
            for(int i = 0; i< array.length; i++){
                map.put(array[i], true);
            }
            int biggestLength = Integer.MIN_VALUE;
            for(int i = 0; i < array.length; i++){
                int currentLength = 0;
                int num = array[i];
                int left = num - 1;
                int right = num + 1;
                while(map.containsKey(left)){
                    map.put(left, false);
                    left--;
                    currentLength++;
                }
                while(map.containsKey(right)) {
                    map.put(right, false);
                    right++;
                    currentLength++;
                }
                if(currentLength > biggestLength) {
                    biggestLength = currentLength;
                    results = new int[]{left + 1 , right -1};
                }
            }
            return results;
      }

      public static int largestSquare(int[][] matrix){
        int result =0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i  == 0 || j ==0) { 
                } else if(matrix[i][j] == 1 ){
                    matrix[i][j] = Math.min(Math.min(matrix[i][j-1],matrix[i-1][j-1]),  matrix[i-1][j]) + 1;
                }
                if( matrix[i][j] > result){
                    result =  matrix[i][j];
                }
            }
        }
        return result;
    }

    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        int numOfCoins[] = new int[n + 1];
          Arrays.fill(numOfCoins, Integer.MAX_VALUE);
          numOfCoins[0] = 0; 
          for(int denom : denoms) {
                  for(int i = 0; i<n + 1; i++){
                      if(denom  <= i){
                          if(numOfCoins[i - denom] == Integer.MAX_VALUE){
                              numOfCoins[i] = Math.min(numOfCoins[i - denom], numOfCoins[i]);
                          } else {
                              numOfCoins[i] = Math.min(numOfCoins[i - denom] + 1, numOfCoins[i]);				
                          }
                      }
                  }
          }
      
      return numOfCoins[n] != Integer.MAX_VALUE ? numOfCoins[n]: -1;
    }


    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        int[] ways = new int[n + 1];
            ways[0] = 1;
            for(int denom: denoms) { 
                for(int i = 0; i < n + 1; i++) { 
                    if(denom <= i)
                        ways[i] += ways[ i - denom];
                }
            }
            return ways[n];
      }

      public static int[] minRewards(int[] scores) {
        int[] results = new int[scores.length];
            Arrays.fill(results,1);
            for(int i = 1; i < scores.length; i++){
                if(scores[i] > scores[i -1]) results[i] = results[i - 1] + 1;
            }
            for(int i = scores.length - 2; i >= 0; i--){
                if(scores[i] > scores[i + 1]) results[i] = Math.max(results[i], results[i + 1] + 1);
            }
        return results;
      }

   

    public static void getPermutations(List<Integer> array) { 
        getPermutations(array, new ArrayList<Integer>());
    }

    public static void getPermutations(List<Integer> array, List<Integer> currentPerm) { 
        if(array.size() == 0)
            System.out.println(currentPerm);
        for (int i = 0; i < array.size(); i++) {
          ArrayList<Integer> newArr = new ArrayList<>(array);
          newArr.remove(i);
          List<Integer> perm = new ArrayList<>(currentPerm);
          perm.add(array.get(i));
          getPermutations(newArr, perm);
        }
    }

    public static void main(String[] args) {
        // List<List<Integer>> servers = new ArrayList<>();
        // servers.add(Arrays.asList(0, 0, 1));
        // servers.add(Arrays.asList(0, 1, 1));
        // servers.add(Arrays.asList(0, 1, 1));
        // System.out.println(minDays(servers));

        // ArrayList<String> quotes = getHugeListOfQuotes();
        // ArrayList<String> toys = getHugeListOfToys();
        // long startTime = System.currentTimeMillis();
        // System.out.println(popularNToys(3, 3, toys, 5, quotes));
        // System.out.println(popularNToysWithTrie(3, 2, toys, 5, quotes));
        // long stopTime = System.currentTimeMillis();
        // long elapsedTime = stopTime - startTime;
        // System.out.println("time to perform operation: " + elapsedTime);
        ////////////////////

        // int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        // System.out.println("Maximum contiguous sum is " +
        // maxSubArraySum(a));
        //int[][] matrix = { { 1, 2 }, { 3, 4, 5 } };
       // System.out.println(balancedBrackets("(())"));
        //int[] search = searchInSortedMatrix(matrix, 4);
        //System.out.println("[" + search[0] + "," + search[1] + "]");
       // List<Integer[]> res = threeNumberSum(new int[]{4,5,6,20,3,2,4,1}, 8);
       // System.out.println(Arrays.toString(largestRange(new int[]{23,1,4,2,3,5,10,12})));
       //System.out.println(numberOfWaysToMakeChange(4, new int[]{2,1}));
       //System.out.println(Arrays.toString(minRewards(new int[]{5,2,3})));
       //getPermutations(new  ArrayList<Integer>(Arrays.asList(1,2,3)));
       getPermutations(new  ArrayList<Integer>(Arrays.asList(1,2,3)));
       
    }
}