import java.util.LinkedList;
//import java.util.ListIterator;
import java.util.Random;

class EnhancedPlaylist {

    private LinkedList<Song> playlist;   /// LinkedList to store songs
    //private ListIterator<Song> iterator;   /// for navigation through the plalist
    private Song currentSong;
    private int  counter = 0;
    

    public EnhancedPlaylist() {
        this.playlist = new LinkedList<>();   // initialissing the playlist
        //this.iterator = playlist.listIterator();// initialising the iterator
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

    // Play the next song
    public void playNextSong() {
       Song last = playlist.getLast();
        if ( currentSong == last ) {
            System.out.println("No more songs in the playlist.");
        } 
        else {
            if (currentSong ==null){
                currentSong= playlist.get(0);
                counter = 0;
            }
            else{
                counter++;
                currentSong= playlist.get(counter);
            }

            }
            System.out.println("Playing: " + currentSong);
        }
            
    

    // Play the previous song
    public void playPreviousSong() {
        Song first = playlist.getFirst();
        if ( currentSong == first) {
            System.out.println("No previous songs in the playlist.");
        } else {
            if(currentSong == null){
                currentSong = playlist.getLast();
                counter = playlist.size()-1;// to get the previous
        }else{
                counter--;
                currentSong= playlist.get(counter);
            }
            System.out.println("Playing: " + currentSong);
        
        }
    }

    // Shuffle the playlist
    public void shufflePlaylist() {
        Random rand = new Random();
        for (int i = 0; i < playlist.size(); i++) {
            int swapIndex = rand.nextInt(playlist.size());
            Song temp = playlist.get(i);
            playlist.set(i, playlist.get(swapIndex));
            playlist.set(swapIndex, temp);
        }
    }

    // Get the number of songs in the playlist
    public int getNumberOfSongs() {
        return playlist.size();
    }
}

public class EnhancedPlaylistInterface {
    public static void main(String[] args) {
        // Create some sample songs
        Song song1 = new Song("Song 1", "Artist 1", 180);
        Song song2 = new Song("Song 2", "Artist 2", 240);
        Song song3 = new Song("Song 3", "Artist 3", 200);
        Song song4 = new Song("Song 4", "Artist 4", 220);

        // Create an EnhancedPlaylist instance
        EnhancedPlaylist playlist = new EnhancedPlaylist();

        // Add songs to the playlist
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        // Display the initial playlist
        System.out.println("Initial Playlist:");
        playlist.displayPlaylist();
        System.out.println();

        // Add a song at a specific position
        playlist.addSongAtPosition(song4, 2); // Add song4 at position 2

        // Display the playlist after adding at a specific position
        System.out.println("Playlist after adding Song 4 at position 2:");
        playlist.displayPlaylist();
        System.out.println();

        // Remove a song by position
        playlist.removeSongByPosition(1); // Remove song at position 1 (first song)

        // Display the playlist after removing by position
        System.out.println("Playlist after removing first song:");
        playlist.displayPlaylist();
        System.out.println();

        // Play the next song
        playlist.playNextSong();
        playlist.playNextSong();
        playlist.playNextSong(); // This should indicate no more songs in the playlist

        // Play the previous song
        playlist.playPreviousSong();
        playlist.playPreviousSong(); // This should indicate no previous songs in the playlist

        // Shuffle the playlist
        playlist.shufflePlaylist();

        // Display the shuffled playlist
        System.out.println("Shuffled Playlist:");
        playlist.displayPlaylist();
        System.out.println();

        // Calculate and display the total duration of the playlist
        int totalDuration = playlist.calculateTotalDuration();
        System.out.println("Total Duration of Playlist: " + totalDuration + " seconds");

        // Get and display the number of songs in the playlist
        int numberOfSongs = playlist.getNumberOfSongs();
        System.out.println("Number of Songs in Playlist: " + numberOfSongs);
    }
}
    

