package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
    private TopM() {
    }

    /**
     * @param args the command-line arguments
     */

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(m + 1);

        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);
            pq.insert(transaction);

            if (pq.size() > m) {
                pq.delMin();
            }
        }

        Stack<Transaction> stack = new Stack<Transaction>();
        for (Transaction transaction : pq)
            stack.push(transaction);
        for (Transaction transaction : stack)
            StdOut.println(transaction);
    }
}
