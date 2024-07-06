import java.util.Scanner;
class Song {
    private String title;
    private String artist;
    private int duration; // duration in seconds

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String toString() {
        int minutes = duration / 60;     // get the duration in minutes
        int seconds = duration % 60;     // get the remaining seconds
        return String.format("%s by %s (%d:%02d)", title, artist, minutes, seconds); 
}


// the singly linkedlist musicplaylist class
class BasicPlaylist {

    private class Node {
        Song song;
        Node next;

        Node(Song song) {    // Constructor initializing the node with the song object
            this.song = song;
            this.next = null;
        }
    }

    private Node head;   // Pointer to the head of the playlist
    private Node tail;   // Pointer to the tail of the playlist
    private int size;    // Track the number of nodes in the playlist

    public BasicPlaylist() {   // Constructor to initialize an empty playlist
        this.head = null;
        this.tail = null;
        this.size = 0;   // Initialize size to 0
    }

    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++; // Increment size after adding a node
    }

    public void addSongAtPosition(Song song, int position) {
        Node newNode = new Node(song);
        if (position <= 1) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else {
            Node current = head;
            for (int i = 1; i < position-1 && current != null; i++) {
                current = current.next;
            }
            if (current == null) {
                if (tail != null) {
                    tail.next = newNode;
                    tail = newNode;
                } else {
                    head = tail = newNode;
                }
            } else {
                newNode.next = current.next;
                current.next = newNode;
                if (newNode.next == null) {
                    tail = newNode;
                }
            }
        }
        size++; // Increment size after adding a node
    }

    public void removeSongByTitle(String title) {
        Node current = head;
        Node previous = null;

        while (current != null && ! current.song.getTitle().equals(title)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (previous == null) {
                head = current.next;
            } else {
                previous.next = current.next;
            }
            if (current == tail) {
                tail = previous;
                tail.next =null;
            }
            size--; // Decrement size after removing a node
        }
    }

    public void removeSongByPosition(int position) {
        if (position < 1 || position > size) {
            return; // Invalid position, do nothing
        }
        Node current = head;
        Node previous = null;

        for (int i = 0; i < position-1; i++) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            head = head.next;
        } else {
            previous.next = current.next;
        }

        if (current == tail) {
            tail = previous;
            tail.next =null;
        }

        size--; // Decrement size after removing a node
    }

    public void displayPlaylist() {
        Node current = head;
        while (current != null) {
            System.out.println(current.song);
            current = current.next;
        }
    }

    public int calculateTotalDuration() {
        int totalDuration = 0;
        Node current = head;
        while (current != null) {
            totalDuration += current.song.getDuration();
            current = current.next;
        }
        return totalDuration;
    }
}
 // the main class to test the no
public class Basic {
    public static void main(String[] args) {
            // Create some sample songs that will initially be in the playlist
            Song song1 = new Song("Song 1", "Artist 1", 180);  // Duration in seconds
            Song song2 = new Song("Song 2", "Artist 2", 240);
            Song song3 = new Song("Song 3", "Artist 3", 200);

            

            Scanner scanner = new Scanner(System.in);
             EnhancedPlaylist playlist = new EnhancedPlaylist();

            // adding the initial songs
             playlist.addSong(song1);
             playlist.addSong(song2);
             playlist.addSong(song3);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Add a song");
                System.out.println("2. Add a song at a specific position");
                System.out.println("3. Remove a song by position");
                System.out.println("4. Play the next song");
                System.out.println("5. Play the previous song");
                System.out.println("6. Shuffle the playlist");
                System.out.println("7. Display the playlist");
                System.out.println("8. Calculate the total duration of the playlist");
                System.out.println("9. Get the number of songs in the playlist");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter song artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter song duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.addSong(new Song(title, artist, duration));
                    break;
                case 2:
                    System.out.print("Enter song title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter song artist: ");
                    artist = scanner.nextLine();
                    System.out.print("Enter song duration (in seconds): ");
                    duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter position to add the song: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.addSongAtPosition(new Song(title, artist, duration), position);
                    break;
                case 3:
                    System.out.print("Enter position to remove the song: ");
                    position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.removeSongByPosition(position);
                    break;
                case 4:
                    playlist.playNextSong();
                    break;
                case 5:
                    playlist.playPreviousSong();
                    break;
                case 6:
                    playlist.shufflePlaylist();
                    System.out.println("Playlist shuffled.");
                    break;
                case 7:
                    System.out.println("Current Playlist:");
                    playlist.displayPlaylist();
                    break;
                case 8:
                    int totalDuration = playlist.calculateTotalDuration();
                    System.out.println("Total Duration of Playlist: " + totalDuration + " seconds");
                    break;
                case 9:
                    int numberOfSongs = playlist.getNumberOfSongs();
                    System.out.println("Number of Songs in Playlist: " + numberOfSongs);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
}



 
