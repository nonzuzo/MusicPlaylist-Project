import java.util.Random;
import java.util.Scanner;


class EnhancedPlaylist {
    private class Node {
        Song song;
        Node next;
        Node prev;

        Node(Song song) {    //double
            this.song = song;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private Node current; // Pointer to the currently playing song
    private int size;

    public EnhancedPlaylist() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }

    // Add a song to the end of the playlist
    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (tail == null) {
            head = tail = newNode;
        } 
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Add a song at a specific position
    public void addSongAtPosition(Song song, int position) {
        Node newNode = new Node(song);
        if (position == 1) {    //beigining
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            size++;
        } 
        else if (position == (size + 1)) {    //at the end
            Node h = head;
            while (h.next != null) {
                h = h.next;
            }
            h.next = newNode;
            newNode.prev = h;
            tail = newNode;
            size++;
        } 
        else {
            int count = 1;  // inserting elsewhere
            Node h = head;
            while (count < (position - 1)) {
                h = h.next;
                count++;
            }
            newNode.next = h.next;
            if (h.next != null) {
                h.next.prev = newNode;
            }
            h.next = newNode;
            newNode.prev = h;
            size++;
        }
    }

    // Remove a song by position
    public void removeSongByPosition(int position) {
        if (position <= 0 || position > size) {
            return;
        }
        if (position == 1) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        } else if (position == size) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
        } else {
            Node h = head;
            int count = 1;
            while (count < (position - 1)) {
                h = h.next;
                count++;
            }
            h.next = h.next.next;
            if (h.next != null) {
                h.next.prev = h;
            }
            size--;
        }
    }


    // Play the next song
    public void playNextSong() {
        if (current == null) {
            current = head;
        } 
        else {
            current = current.next;
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        } 
        else {
            System.out.println("No more songs in the playlist.");
        }
    }

    // Play the previous song
    public void playPreviousSong() {
        if (current == null) {
            current = tail;
        } 
        else {
            current = current.prev;
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        } 
        else {
            System.out.println("No previous songs in the playlist.");
        }
    }

    // Shuffle the playlist
    public void shufflePlaylist() {
        if (size > 1) {
            Random rand = new Random(); // creating a random object

            for (int i = 0; i < size; i++) {
                Node currentNode = head;

                for (int j = 0; j < i; j++) {
                    currentNode = currentNode.next;
                }
                int swapIndex = rand.nextInt(size);
                Node swapNode = head;
                for (int j = 0; j < swapIndex; j++) {
                    swapNode = swapNode.next;
                }
                Song temp = currentNode.song;
                currentNode.song = swapNode.song;
                swapNode.song = temp;
            }
        }
    }
// Display the playlist
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

public int getNumberOfSongs() {
    return size;
}
}

public class EnhancedPlaylist_ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnhancedPlaylist playlist = new EnhancedPlaylist();

        // Create some sample songs
        Song song1 = new Song("Song 1", "Artist 1", 180);
        Song song2 = new Song("Song 2", "Artist 2", 240);
        Song song3 = new Song("Song 3", "Artist 3", 200);
         

        // Add initial songs to the playlist
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display Playlist");
            System.out.println("2. Add Song");
            System.out.println("3. Add Song at Position");
            System.out.println("4. Remove Song by Position");
            System.out.println("5. Play Next Song");
            System.out.println("6. Play Previous Song");
            System.out.println("7. Shuffle Playlist");
            System.out.println("8. Calculate Total Duration");
            System.out.println("9. Get Number of Songs");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    playlist.displayPlaylist();
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Song newSong = new Song(title, artist, duration);
                    playlist.addSong(newSong);
                    break;
                case 3:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    artist = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    duration = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter position: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    newSong = new Song(title, artist, duration);
                    playlist.addSongAtPosition(newSong, position);
                    break;
                case 4:
                    System.out.print("Enter position to remove: ");
                    position = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    playlist.removeSongByPosition(position);
                    break;
                case 5:
                    playlist.playNextSong();
                    break;
                case 6:
                    playlist.playPreviousSong();
                    break;
                case 7:
                    playlist.shufflePlaylist();
                    break;
                case 8:
                    int totalDuration = playlist.calculateTotalDuration();
                    System.out.println("Total Duration of Playlist: " + totalDuration + " seconds");
                    break;
                case 9:
                    int numberOfSongs = playlist.getNumberOfSongs();
                    System.out.println("Number of Songs in Playlist: " + numberOfSongs);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);

        scanner.close();
    }
}
        

    

