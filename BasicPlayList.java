import java.util.LinkedList;
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
}
/// makes use of the java collections
class BPlaylist{  

    private LinkedList<Song> playlist; // Using LinkedList to store songs

    public BPlaylist() {
        this.playlist = new LinkedList<>(); // Correct initialization in the constructor
    }
    // Add a song to the end of the playlist
    public void addSong(Song song) {
        playlist.add(song);
    }

    // Add a song at a specific position
    public void addSongAtPosition(Song song, int position) {
        if (position >= 0 && position <= playlist.size()) {
            playlist.add(position, song);
        } else {
            System.out.println("Invalid position to add song: " + position);
        }
    }

    // Remove a song by title
    public void removeSongByTitle(String title) {
        Song toRemove = null;
        for (Song song : playlist) {
            if (song.getTitle().equals(title)) {
                toRemove = song;
                break;
            }
        }
        if (toRemove != null) {
            playlist.remove(toRemove);
        } else {
            System.out.println("Song with title '" + title + "' not found.");
        }
    }

    // Remove a song by position
    public void removeSongByPosition(int position) {
        if (position >= 0 && position < playlist.size()) {
            playlist.remove(position);
        } else {
            System.out.println("Invalid position to remove song: " + position);
        }
    }

    // Display the playlist in order
    public void displayPlaylist() {
        for (Song song : playlist) {
            System.out.println(song);
        }
    }

    // Calculate the total duration of the playlist
    public int calculateTotalDuration() {
        int totalDuration = 0;
        for (Song song : playlist) {
            totalDuration += song.getDuration();
        }
        return totalDuration;
    }
}
                   
public class BasicPlayList{
    public static void main(String[] args) {
        // Create some sample songs

        Song song1 = new Song("Song 1", "Artist 1", 180);
        Song song2 = new Song("Song 2", "Artist 2", 240);
        Song song3 = new Song("Song 3", "Artist 3", 200);

        // Create a BasicPlaylist instance
        BPlaylist playlist = new BPlaylist();

        // Add songs to the playlist
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        // Display the initial playlist
        System.out.println("Initial Playlist:");
        playlist.displayPlaylist();
        System.out.println();

        // Add a song at a specific position
        Song song4 = new Song("Song 4", "Artist 4", 220);
        playlist.addSongAtPosition(song4, 2); // Add song 4 at position 2

        // Display the playlist after adding at a specific position
        System.out.println("Playlist after adding Song 4 at position 2:");
        playlist.displayPlaylist();
        System.out.println();

        // Remove a song by title
        playlist.removeSongByTitle("Song 2");

        // Display the playlist after removing by title
        System.out.println("Playlist after removing Song 2:");
        playlist.displayPlaylist();
        System.out.println();

        // Remove a song by position
        playlist.removeSongByPosition(0); // Remove song at position 0 (first song)

        // Display the playlist after removing by position
        System.out.println("Playlist after removing first song:");
        playlist.displayPlaylist();
        System.out.println();

        // Calculate and display the total duration of the playlist
        int totalDuration = playlist.calculateTotalDuration();
        System.out.println("Total Duration of Playlist: " + totalDuration + " seconds");
    }
}
    
