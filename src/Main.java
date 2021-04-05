import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        FileInputStream fIn=new FileInputStream(args[0]);
        try {
            reader = new BufferedReader(new InputStreamReader(fIn));

            int lines = -1;
            while (reader.readLine() != null) lines++;

            MyHashTable sepChainTable = new MyHashTable(lines, Double.parseDouble(args[1]));
            MyHashTable linearProbTable = new MyHashTable(lines, Double.parseDouble(args[2]));
            MyHashTable doubleProbTable = new MyHashTable(lines, Double.parseDouble(args[2]));

            fIn.getChannel().position(0);
            reader = new BufferedReader(new InputStreamReader(fIn));
            // to omit first line
            reader.readLine();
            String line = reader.readLine();

            while (line != null) {
                String[] arrOfStr = line.split(" ", 0);
                Employee newEmployee = new Employee(arrOfStr[0], arrOfStr[1], Integer.parseInt(arrOfStr[2]));
                sepChainTable.separateChaining(newEmployee);
                linearProbTable.linearProbing(newEmployee);
                doubleProbTable.doubleHashing(newEmployee);
                // read next line
                line = reader.readLine();
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(args[0].substring(0, args[0].length() - 4) + ",LF1=" + args[1] + ",LF2=" + args[2] + "," + args[3]);
            writer.newLine();

            writer.write("PART1");
            writer.newLine();


            for (int i = 0; i < sepChainTable.getTableSize(); i++) {
                writer.write(String.format("[Chain %d]: ", i) + sepChainTable.getHead(i).printLinkedList(true));
                writer.newLine();
            }

            writer.write("PART2");
            writer.newLine();

            writer.write("Hashtable for Linear Probing");
            writer.newLine();

            for (int i = 0; i < linearProbTable.getTableSize(); i++) {
                writer.write(String.format("[%d]--->", i) + linearProbTable.getHead(i).printLinkedList(false));
                writer.newLine();
            }

            writer.write("Hashtable for Double Hashing");
            writer.newLine();

            for (int i = 0; i < doubleProbTable.getTableSize(); i++) {
                writer.write(String.format("[%d]--->", i) + doubleProbTable.getHead(i).printLinkedList(false));
                writer.newLine();
            }

            writer.write("SEPARATE CHAINING:\n");

            long startTime = System.nanoTime();
            int c = sepChainTable.sepGet(Integer.parseInt(args[3]));
            long elapsedTime = System.nanoTime() - startTime;
            writer.write(String.format("Key found with %d comparisons\n", c));
            writer.write(String.format("CPU time taken to search = %.1f ns\n", (double) elapsedTime));

            writer.write("LINEAR PROBING:\n");

            startTime = System.nanoTime();
            c = linearProbTable.linGet(Integer.parseInt(args[3]));
            elapsedTime = System.nanoTime() - startTime;
            writer.write(String.format("Key found with %d comparisons\n", c));
            writer.write(String.format("CPU time taken to search = %.1f ns\n", (double) elapsedTime));

            startTime = System.nanoTime();
            c = doubleProbTable.doubleGet(Integer.parseInt(args[3]));
            elapsedTime = System.nanoTime() - startTime;
            writer.write(String.format("Key found with %d comparisons\n", c));
            writer.write(String.format("CPU time taken to search = %.1f ns", (double) elapsedTime));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
