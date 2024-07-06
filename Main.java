import java.io.*;
import java.util.*;

class HistoryNode{
//difining the history node class
    String url;
    String  timestamp;
    HistoryNode prev;
    HistoryNode next;


    public HistoryNode(String url, String timestamp) {
        this.url = url;
        this.timestamp = timestamp;
        this.prev = null;
        this.next = null;
    }
}

class BrowserHistory{
    private HistoryNode head;
    private HistoryNode tail;

    public BrowserHistory() {
        this.head = null;
        this.tail = null;
    }

    public void addPage( String url,String timestamp){

        HistoryNode newNode = new HistoryNode(url, timestamp);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    public void removePage(String timestamp) {
        HistoryNode h = head;   // creating a temporary variable
        while (h!= null) {
            if (h.timestamp.equals(timestamp)) {
                if (h.prev != null) {
                    h.prev.next = h.next;
                } 
                else {
                    head = h.next;
                }
                if (h.next != null) {
                    h.next.prev = h.prev;
                } 
                else {
                    tail = h.prev;
                }
                break;
            }
            h = h.next;
        }
    }
    public void displayHistoryForward() {
        HistoryNode h= head;
        while (h != null) {
            System.out.println(h.timestamp + " - " + h.url);
            h = h.next;
        }
    }

    public void displayHistoryBackward() {
        HistoryNode h= tail;
        while (h != null) {
            System.out.println(h.timestamp + " - " + h.url);
            h = h.prev;
        }
    }

    public void saveHistory(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            HistoryNode current = head;
            while (current != null) {
                writer.write(current.timestamp + "," + current.url);
                writer.newLine();
                current = current.next;
            }
        }
    }
    public void loadHistory(String filename) throws IOException {
        try (Scanner scanner = new Scanner(new File(filename))) {  /// to read the given file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    addPage(parts[1], parts[0]);
                }
            }
        }
    }


}

public class Main {
    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();

        // Adding pages to the history
        history.addPage("www.ashesi.com", "09:00 AM");
        history.addPage("www.google.com", "10:40 AM");
        history.addPage("www.alphabet.com", "10:05 AM");

        // Remove the page visited at 10:05 AM
        history.removePage("10:05 AM");

        // Display the browsing history in forward order
        System.out.println("Browsing history displayed forward:");
        history.displayHistoryForward();

        // Display the browsing history in backward order
        System.out.println("\n Browsing history disaplayed backward:");
        history.displayHistoryBackward();


        // saving the history above
        try {
            history.saveHistory("history.txt");
        } catch (IOException e) {
            System.err.println("Failed to save history: " + e.getMessage());
        }

        // to avoif repeation
        history = new BrowserHistory();
        // loading the history
        try {
            history.loadHistory("history.txt");
        } catch (IOException e) {
            System.err.println("Failed to load history: " + e.getMessage());
        }

        System.out.println("Browsing history (forward):");
        history.displayHistoryForward();

        history.removePage("09:00 AM");

        System.out.println("Browsing history (forward):");
        history.displayHistoryForward();
    }
}

