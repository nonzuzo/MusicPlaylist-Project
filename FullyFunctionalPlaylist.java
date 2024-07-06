import java.util.Random;
import java.util.Scanner;


class Song {
    private String title;
    private String artist;
    private int duration; // in seconds

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
        return "Song [title=" + title + ", artist=" + artist + ", duration=" + duration + "]";
    }
}

class FullyFunctionalPlaylist_ {
    private class Node {
        Song song;
        Node next;
        Node prev;

        Node(Song song) {
            this.song = song;
            this.next = this;
            this.prev = this;
        }
    }

    private Node head;
    private Node tail;
    private Node current; // Pointer to the currently playing song
    private int size;
    private boolean continuousPlay;

    public FullyFunctionalPlaylist_() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
        this.continuousPlay = true; // Default to continuous play mode
    }

    // Add a song to the end of the playlist
    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = tail = newNode;
        } 
        else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
        }
        size++;
    }

    // Add a song at a specific position
    public void addSongAtPosition(Song song, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position");
            return;
        }
        Node newNode = new Node(song);
        if (position == 1) {
            if (head == null) {
                head = tail = newNode;
            } 
            else {
                newNode.next = head;
                newNode.prev = tail;
                head.prev = newNode;
                tail.next = newNode;
                head = newNode;
            }
        } else if (position == size + 1) {
            addSong(song);
            return;
        } else {
            Node current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    }

    // Remove a song by position
    public void removeSongByPosition(int position) {
        if (position < 1 || position > size) {
            System.out.println("Invalid position");
            return;
        }
        if (size == 1) {
            head = tail = null;
        } else if (position == 1) {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        } else if (position == size) {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        } else {
            Node current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            current.next.prev = current;
        }
        size--;
    }

    // Play the next song
    public void playNextSong() {
        if (current == null) {
            current = head;
        } else {
            current = current.next;
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        } else {
            System.out.println("No more songs in the playlist.");
        }
    }

    // Play the previous song
    public void playPreviousSong() {
        if (current == null) {
            current = tail;
        } else {
            current = current.prev;
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        } else {
            System.out.println("No previous songs in the playlist.");
        }
    }

    // Shuffle the playlist
    public void shufflePlaylist() {
        if (size > 1) {
            Random rand = new Random();
            Node[] nodes = new Node[size];
            Node currentNode = head;
            for (int i = 0; i < size; i++) {
                nodes[i] = currentNode;
                currentNode = currentNode.next;
            }
            for (int i = 0; i < size; i++) {
                int swapIndex = rand.nextInt(size);
                Song temp = nodes[i].song;
                nodes[i].song = nodes[swapIndex].song;
                nodes[swapIndex].song = temp;
            }
        }
    }

    // Display the playlist
    public void displayPlaylist() {
        Node current = head;
        if (current != null) {
            do {
                System.out.println(current.song);
                current = current.next;
            } while (current != head);
        }
    }

    // Calculate the total duration of the playlist
    public int calculateTotalDuration() {
        int totalDuration = 0;
        Node current = head;
        if (current != null) {
            do {
                totalDuration += current.song.getDuration();
                current = current.next;
            } while (current != head);
        }
        return totalDuration;
    }

    // Get the number of songs in the playlist
    public int getNumberOfSongs() {
        return size;
    }

    // Toggle continuous play mode

    public void toggleContinuousPlay() {
        if (continuousPlay) {
            continuousPlay = false;
            System.out.println("Continuous play mode is now disabled");
        } else {
            continuousPlay = true;
            System.out.println("Continuous play mode is now enabled");
        }
    }
     

    public void playCurrentSong() {
        if (current != null) {
            System.out.println("Playing: " + current.song);
        } else {
            System.out.println("No song is currently selected.");
        }
    }

    // Start playing from the first song
    public void startPlaying() {
        current = head;
        playCurrentSong();
    }
}

public class FullyFunctionalPlaylist{
    public static void main(String[] args) {
// Create some sample songs that will initially be in the playlist
Song song1 = new Song("Song 1", "Artist 1", 180);
Song song2 = new Song("Song 2", "Artist 2", 240);
Song song3 = new Song("Song 3", "Artist 3", 200);
Song song4 = new Song("Song 4", "Artist 4", 220);

// Create a FullyFunctionalPlaylist instance
FullyFunctionalPlaylist_ playlist = new FullyFunctionalPlaylist_();

// Add songs to the playlist
playlist.addSong(song1);
playlist.addSong(song2);
playlist.addSong(song3);
playlist.addSong(song4);

Scanner scanner = new Scanner(System.in);
int choice;
// do while loop for the menu based 
do {
    System.out.println("\nMenu:");
    System.out.println("1.Display Playlist");
    System.out.println("2.Add Song");
    System.out.println("3.Add Song at Position");
    System.out.println("4.Remove Song by Position");
    System.out.println("5.Play Next Song");
    System.out.println("6.Play Previous Song");
    System.out.println("7.Shuffle Playlist");
    System.out.println("8.Calculate Total Duration");
    System.out.println("9.Get Number of Songs");
    System.out.println("10.Toggle Continuous Play Mode");
    System.out.println("11.Start Playing");
    System.out.println("12.Exit");
    System.out.print("Enter your choice: ");
    choice = scanner.nextInt();
    scanner.nextLine();  

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
            scanner.nextLine();  
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
            scanner.nextLine();  
            System.out.print("Enter position: ");
            int position = scanner.nextInt();
            scanner.nextLine();  
            newSong = new Song(title, artist, duration);
            playlist.addSongAtPosition(newSong, position);
            break;
        case 4:
            System.out.print("Enter position to remove: ");
            position = scanner.nextInt();
            scanner.nextLine();  
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
            playlist.toggleContinuousPlay();
            break;
        case 11:
            playlist.startPlaying();
            break;
        case 12:
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
    }
} while (choice != 12);

scanner.close();
}
}



        