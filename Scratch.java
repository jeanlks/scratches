import java.lang.annotation.Target;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Scratch {

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
        Program.SuffixTrie trieToys = new Program.SuffixTrie();
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
        toysCount.entrySet().stream().sorted(Map.Entry.comparingByKey()).sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedToysCount.put(x.getKey(), x.getValue()));
        System.out.println(toysCount);
        sortedToysCount.entrySet().stream().sorted(Collections.reverseOrder())
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
        // ArrayList<String> quotes = new ArrayList<>(Arrays.asList("A liked the batman
        // new toy!.",
        // "I loved the new elsa toy", "Loved the new Elsa toy", "I love playing with my
        // minions",
        // "My prefered minions for sleeping", "Minions are my passion.", "I love my
        // barco"));
        ArrayList<String> quotes = new ArrayList<>(Arrays.asList("A liked the batman new toy!.",
                "Batman is my prefered!", "I love my barco", "I play barco everyday", "I love my elsa"));
        // for (int i = 0; i < 1000000; i++) {
        // quotes.add("I love playing with my minions");
        // }
        return quotes;
    }

    public static ArrayList<String> getHugeListOfToys() {
        ArrayList<String> toys = new ArrayList<>();
        toys.add("elsa");
        toys.add("minions");
        toys.add("batman");
        toys.add("barco");
        // for (int i = 0; i < 200; i++) {
        // byte[] array = new byte[7]; // length is bounded by 7
        // new Random().nextBytes(array);
        // String generatedString = new String(array, Charset.forName("UTF-8"));
        // toys.add(generatedString);
        // }
        return toys;
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

    public static void main(String[] args) {
        // List<List<Integer>> servers = new ArrayList<>();
        // servers.add(Arrays.asList(0, 1, 1));
        // servers.add(Arrays.asList(0, 1, 1));
        // servers.add(Arrays.asList(0, 1, 1));
        // System.out.println(minDays(servers));

        // ArrayList<String> quotes = getHugeListOfQuotes();
        // ArrayList<String> toys = getHugeListOfToys();
        // long startTime = System.currentTimeMillis();
        // System.out.println(popularNToys(3, 3, toys, 5, quotes));
        // //System.out.println(popularNToysWithTrie(3, 2, toys, 5, quotes));
        // long stopTime = System.currentTimeMillis();
        // long elapsedTime = stopTime - startTime;
        // System.out.println("time to perform operation: " + elapsedTime);
        // ////////////////////

        // int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        // System.out.println("Maximum contiguous sum is " +
        // maxSubArraySum(a));
        int[][] matrix = { { 1, 2 }, { 3, 4, 5 } };
       // System.out.println(balancedBrackets("(())"));
        //int[] search = searchInSortedMatrix(matrix, 4);
        //System.out.println("[" + search[0] + "," + search[1] + "]");
       // List<Integer[]> res = threeNumberSum(new int[]{4,5,6,20,3,2,4,1}, 8);
       

        System.out.println(Arrays.toString(largestRange(new int[]{23,1,4,2,3,5,10,12})));
    }
}