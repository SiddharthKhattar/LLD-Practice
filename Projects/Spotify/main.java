import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Main {

    // ==========================================
    // ENUMS
    // ==========================================

    public enum DeviceType {
        BLUETOOTH,
        WIRED,
        HEADPHONES
    }

    public enum PlayStrategyType {
        SEQUENTIAL,
        RANDOM,
        CUSTOM_QUEUE
    }

    // ==========================================
    // MODELS
    // ==========================================

    public static class Song {
        private String title;
        private String artist;
        private String filePath;

        public Song(String title, String artist, String filePath) {
            this.title = title;
            this.artist = artist;
            this.filePath = filePath;
        }

        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

        public String getFilePath() {
            return filePath;
        }
    }

    public static class Playlist {
        private String playlistName;
        private List<Song> songList;

        public Playlist(String name) {
            this.playlistName = name;
            this.songList = new ArrayList<>();
        }

        public String getPlaylistName() {
            return playlistName;
        }

        public List<Song> getSongs() {
            return songList;
        }

        public int getSize() {
            return songList.size();
        }

        public void addSongToPlaylist(Song song) {
            if (song == null) {
                throw new RuntimeException("Cannot add null song to playlist.");
            }
            songList.add(song);
        }
    }

    // ==========================================
    // EXTERNAL APIS & DEVICE ADAPTERS
    // ==========================================

    public static class HeadphonesAPI {
        public void playSoundViaJack(String data) {
            System.out.println("[Headphones] Playing: " + data);
        }
    }

    public static class BluetoothSpeakerAPI {
        public void streamAudioOverBluetooth(String data) {
            System.out.println("[Bluetooth Speaker] Playing: " + data);
        }
    }

    public static class WiredSpeakerAPI {
        public void playSoundViaCable(String data) {
            System.out.println("[Wired Speaker] Playing: " + data);
        }
    }

    public interface IAudioOutputDevice {
        void playAudio(Song song);
    }

    public static class HeadphonesAdapter implements IAudioOutputDevice {
        private HeadphonesAPI headphonesApi;

        public HeadphonesAdapter(HeadphonesAPI api) {
            this.headphonesApi = api;
        }

        @Override
        public void playAudio(Song song) {
            String payload = song.getTitle() + " by " + song.getArtist();
            headphonesApi.playSoundViaJack(payload);
        }
    }

    public static class BluetoothSpeakerAdapter implements IAudioOutputDevice {
        private BluetoothSpeakerAPI bluetoothApi;

        public BluetoothSpeakerAdapter(BluetoothSpeakerAPI api) {
            this.bluetoothApi = api;
        }

        @Override
        public void playAudio(Song song) {
            String payload = song.getTitle() + " by " + song.getArtist();
            bluetoothApi.streamAudioOverBluetooth(payload);
        }
    }

    public static class WiredSpeakerAdapter implements IAudioOutputDevice {
        private WiredSpeakerAPI wiredApi;

        public WiredSpeakerAdapter(WiredSpeakerAPI api) {
            this.wiredApi = api;
        }

        @Override
        public void playAudio(Song song) {
            String payload = song.getTitle() + " by " + song.getArtist();
            wiredApi.playSoundViaCable(payload);
        }
    }

    // ==========================================
    // STRATEGIES
    // ==========================================

    public interface PlayStrategy {
        void setPlaylist(Playlist playlist);
        Song next();
        boolean hasNext();
        Song previous();
        boolean hasPrevious();
        default void addToNext(Song song) {}
    }

    public static class SequentialPlayStrategy implements PlayStrategy {
        private Playlist currentPlaylist;
        private int currentIndex;

        public SequentialPlayStrategy() {
            currentPlaylist = null;
            currentIndex = -1;
        }

        @Override
        public void setPlaylist(Playlist playlist) {
            currentPlaylist = playlist;
            currentIndex = -1;
        }

        @Override
        public boolean hasNext() {
            return ((currentIndex + 1) < currentPlaylist.getSize());
        }

        @Override
        public Song next() {
            if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
                throw new RuntimeException("No playlist loaded or playlist is empty.");
            }
            currentIndex = currentIndex + 1;
            return currentPlaylist.getSongs().get(currentIndex);
        }

        @Override
        public boolean hasPrevious() {
            return (currentIndex - 1 > 0);
        }

        @Override
        public Song previous() {
            if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
                throw new RuntimeException("No playlist loaded or playlist is empty.");
            }
            currentIndex = currentIndex - 1;
            return currentPlaylist.getSongs().get(currentIndex);
        }
    }

    public static class RandomPlayStrategy implements PlayStrategy {
        private Playlist currentPlaylist;
        private List<Song> remainingSongs;
        private Stack<Song> history;
        private Random random;

        public RandomPlayStrategy() {
            currentPlaylist = null;
            random = new Random();
        }

        @Override
        public void setPlaylist(Playlist playlist) {
            currentPlaylist = playlist;
            if (currentPlaylist == null || currentPlaylist.getSize() == 0) return;

            remainingSongs = new ArrayList<>(currentPlaylist.getSongs());
            history = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return currentPlaylist != null && !remainingSongs.isEmpty();
        }

        @Override
        public Song next() {
            if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
                throw new RuntimeException("No playlist loaded or playlist is empty.");
            }
            if (remainingSongs.isEmpty()) {
                throw new RuntimeException("No songs left to play");
            }

            int idx = random.nextInt(remainingSongs.size());
            Song selectedSong = remainingSongs.get(idx);

            int lastIndex = remainingSongs.size() - 1;
            remainingSongs.set(idx, remainingSongs.get(lastIndex));
            remainingSongs.remove(lastIndex);

            history.push(selectedSong);
            return selectedSong;
        }

        @Override
        public boolean hasPrevious() {
            return history.size() > 0;
        }

        @Override
        public Song previous() {
            if (history.isEmpty()) {
                throw new RuntimeException("No previous song available.");
            }

            Song song = history.pop();
            return song;
        }
    }

    public static class CustomQueueStrategy implements PlayStrategy {
        private Playlist currentPlaylist;
        private int currentIndex;
        private Queue<Song> nextQueue;
        private Stack<Song> prevStack;

        private Song nextSequential() {
            if (currentPlaylist.getSize() == 0) {
                throw new RuntimeException("Playlist is empty.");
            }
            currentIndex = currentIndex + 1;
            return currentPlaylist.getSongs().get(currentIndex);
        }

        private Song previousSequential() {
            if (currentPlaylist.getSize() == 0) {
                throw new RuntimeException("Playlist is empty.");
            }
            currentIndex = currentIndex - 1;
            return currentPlaylist.getSongs().get(currentIndex);
        }

        public CustomQueueStrategy() {
            currentPlaylist = null;
            currentIndex = -1;
            nextQueue = new LinkedList<>();
            prevStack = new Stack<>();
        }

        @Override
        public void setPlaylist(Playlist playlist) {
            currentPlaylist = playlist;
            currentIndex = -1;
            nextQueue.clear();
            prevStack.clear();
        }

        @Override
        public boolean hasNext() {
            return ((currentIndex + 1) < currentPlaylist.getSize());
        }

        @Override
        public Song next() {
            if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
                throw new RuntimeException("No playlist loaded or playlist is empty.");
            }

            if (!nextQueue.isEmpty()) {
                Song s = nextQueue.poll();
                prevStack.push(s);

                for (int i = 0; i < currentPlaylist.getSongs().size(); ++i) {
                    if (currentPlaylist.getSongs().get(i) == s) {
                        currentIndex = i;
                        break;
                    }
                }
                return s;
            }

            return nextSequential();
        }

        @Override
        public boolean hasPrevious() {
            return (currentIndex - 1 > 0);
        }

        @Override
        public Song previous() {
            if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
                throw new RuntimeException("No playlist loaded or playlist is empty.");
            }

            if (!prevStack.isEmpty()) {
                Song s = prevStack.pop();

                for (int i = 0; i < currentPlaylist.getSongs().size(); ++i) {
                    if (currentPlaylist.getSongs().get(i) == s) {
                        currentIndex = i;
                        break;
                    }
                }
                return s;
            }

            return previousSequential();
        }

        @Override
        public void addToNext(Song song) {
            if (song == null) {
                throw new RuntimeException("Cannot enqueue null song.");
            }
            nextQueue.add(song);
        }
    }

    // ==========================================
    // FACTORIES & MANAGERS
    // ==========================================

    public static class DeviceFactory {
        public static IAudioOutputDevice createDevice(DeviceType deviceType) {
            switch (deviceType) {
                case BLUETOOTH:
                    return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
                case WIRED:
                    return new WiredSpeakerAdapter(new WiredSpeakerAPI());
                case HEADPHONES:
                default:
                    return new HeadphonesAdapter(new HeadphonesAPI());
            }
        }
    }

    public static class DeviceManager {
        private static DeviceManager instance = null;
        private IAudioOutputDevice currentOutputDevice;

        private DeviceManager() {
            currentOutputDevice = null;
        }

        public static synchronized DeviceManager getInstance() {
            if (instance == null) {
                instance = new DeviceManager();
            }
            return instance;
        }

        public void connect(DeviceType deviceType) {
            currentOutputDevice = DeviceFactory.createDevice(deviceType);

            switch (deviceType) {
                case BLUETOOTH:
                    System.out.println("Bluetooth device connected ");
                    break;
                case WIRED:
                    System.out.println("Wired device connected ");
                    break;
                case HEADPHONES:
                    System.out.println("Headphones connected ");
                    break;
            }
        }

        public IAudioOutputDevice getOutputDevice() {
            if (currentOutputDevice == null) {
                throw new RuntimeException("No output device is connected.");
            }
            return currentOutputDevice;
        }

        public boolean hasOutputDevice() {
            return currentOutputDevice != null;
        }
    }

    public static class PlaylistManager {
        private static PlaylistManager instance = null;
        private Map<String, Playlist> playlists;

        private PlaylistManager() {
            playlists = new HashMap<>();
        }

        public static synchronized PlaylistManager getInstance() {
            if (instance == null) {
                instance = new PlaylistManager();
            }
            return instance;
        }

        public void createPlaylist(String name) {
            if (playlists.containsKey(name)) {
                throw new RuntimeException("Playlist \"" + name + "\" already exists.");
            }
            playlists.put(name, new Playlist(name));
        }

        public void addSongToPlaylist(String playlistName, Song song) {
            if (!playlists.containsKey(playlistName)) {
                throw new RuntimeException("Playlist \"" + playlistName + "\" not found.");
            }
            playlists.get(playlistName).addSongToPlaylist(song);
        }

        public Playlist getPlaylist(String name) {
            if (!playlists.containsKey(name)) {
                throw new RuntimeException("Playlist \"" + name + "\" not found.");
            }
            return playlists.get(name);
        }
    }

    public static class StrategyManager {
        private static StrategyManager instance = null;
        private SequentialPlayStrategy sequentialStrategy;
        private RandomPlayStrategy randomStrategy;
        private CustomQueueStrategy customQueueStrategy;

        private StrategyManager() {
            sequentialStrategy = new SequentialPlayStrategy();
            randomStrategy = new RandomPlayStrategy();
            customQueueStrategy = new CustomQueueStrategy();
        }

        public static synchronized StrategyManager getInstance() {
            if (instance == null) {
                instance = new StrategyManager();
            }
            return instance;
        }

        public PlayStrategy getStrategy(PlayStrategyType type) {
            if (type == PlayStrategyType.SEQUENTIAL) {
                return sequentialStrategy;
            } else if (type == PlayStrategyType.RANDOM) {
                return randomStrategy;
            } else {
                return customQueueStrategy;
            }
        }
    }

    // ==========================================
    // CORE AUDIO ENGINE & FACADE
    // ==========================================

    public static class AudioEngine {
        private String currentSongTitle = "";

        public void play(IAudioOutputDevice device, Song song) {
            currentSongTitle = song.getTitle();
            device.playAudio(song);
        }

        public void pause() {
            System.out.println("Paused track: " + currentSongTitle);
        }

        public String getCurrentSongTitle() {
            return currentSongTitle;
        }
    }

    public static class MusicPlayerFacade {
        private static MusicPlayerFacade instance = null;
        private AudioEngine audioEngine;
        private Playlist loadedPlaylist;
        private PlayStrategy playStrategy;

        private MusicPlayerFacade() {
            loadedPlaylist = null;
            playStrategy = null;
            audioEngine = new AudioEngine();
        }

        public static synchronized MusicPlayerFacade getInstance() {
            if (instance == null) {
                instance = new MusicPlayerFacade();
            }
            return instance;
        }

        public void connectDevice(DeviceType deviceType) {
            DeviceManager.getInstance().connect(deviceType);
        }

        public void setPlayStrategy(PlayStrategyType strategyType) {
            playStrategy = StrategyManager.getInstance().getStrategy(strategyType);
        }

        public void loadPlaylist(String name) {
            loadedPlaylist = PlaylistManager.getInstance().getPlaylist(name);
            if (playStrategy == null) {
                throw new RuntimeException("Play strategy not set before loading.");
            }
            playStrategy.setPlaylist(loadedPlaylist);
        }

        public void playSong(Song song) {
            if (!DeviceManager.getInstance().hasOutputDevice()) {
                throw new RuntimeException("No audio device connected.");
            }
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.play(device, song);
        }

        public void pauseSong(Song song) {
            if (!audioEngine.getCurrentSongTitle().equals(song.getTitle())) {
                throw new RuntimeException("Cannot pause \"" + song.getTitle() + "\"; not currently playing.");
            }
            audioEngine.pause();
        }

        public void playAllTracks() {
            if (loadedPlaylist == null) {
                throw new RuntimeException("No playlist loaded.");
            }
            while (playStrategy.hasNext()) {
                Song nextSong = playStrategy.next();
                IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
                audioEngine.play(device, nextSong);
            }
            System.out.println("Completed playlist: " + loadedPlaylist.getPlaylistName());
        }

        public void playNextTrack() {
            if (loadedPlaylist == null) {
                throw new RuntimeException("No playlist loaded.");
            }
            if (playStrategy.hasNext()) {
                Song nextSong = playStrategy.next();
                IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
                audioEngine.play(device, nextSong);
            } else {
                System.out.println("Completed playlist: " + loadedPlaylist.getPlaylistName());
            }
        }

        public void playPreviousTrack() {
            if (loadedPlaylist == null) {
                throw new RuntimeException("No playlist loaded.");
            }
            if (playStrategy.hasPrevious()) {
                Song prevSong = playStrategy.previous();
                IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
                audioEngine.play(device, prevSong);
            } else {
                System.out.println("Completed playlist: " + loadedPlaylist.getPlaylistName());
            }
        }

        public void enqueueNext(Song song) {
            playStrategy.addToNext(song);
        }
    }

    // ==========================================
    // APPLICATION ENTRY POINT & RUNNER
    // ==========================================

    public static class MusicPlayerApplication {
        private static MusicPlayerApplication instance = null;
        private List<Song> songLibrary;

        private MusicPlayerApplication() {
            songLibrary = new ArrayList<>();
        }

        public static synchronized MusicPlayerApplication getInstance() {
            if (instance == null) {
                instance = new MusicPlayerApplication();
            }
            return instance;
        }

        public void createSongInLibrary(String title, String artist, String path) {
            Song newSong = new Song(title, artist, path);
            songLibrary.add(newSong);
        }

        public Song findSongByTitle(String title) {
            for (Song s : songLibrary) {
                if (s.getTitle().equals(title)) {
                    return s;
                }
            }
            return null;
        }

        public void createPlaylist(String playlistName) {
            PlaylistManager.getInstance().createPlaylist(playlistName);
        }

        public void addSongToPlaylist(String playlistName, String songTitle) {
            Song song = findSongByTitle(songTitle);
            if (song == null) {
                throw new RuntimeException("Song \"" + songTitle + "\" not found in library.");
            }
            PlaylistManager.getInstance().addSongToPlaylist(playlistName, song);
        }

        public void connectAudioDevice(DeviceType deviceType) {
            MusicPlayerFacade.getInstance().connectDevice(deviceType);
        }

        public void selectPlayStrategy(PlayStrategyType strategyType) {
            MusicPlayerFacade.getInstance().setPlayStrategy(strategyType);
        }

        public void loadPlaylist(String playlistName) {
            MusicPlayerFacade.getInstance().loadPlaylist(playlistName);
        }

        public void playSingleSong(String songTitle) {
            Song song = findSongByTitle(songTitle);
            if (song == null) {
                throw new RuntimeException("Song \"" + songTitle + "\" not found.");
            }
            MusicPlayerFacade.getInstance().playSong(song);
        }

        public void pauseCurrentSong(String songTitle) {
            Song song = findSongByTitle(songTitle);
            if (song == null) {
                throw new RuntimeException("Song \"" + songTitle + "\" not found.");
            }
            MusicPlayerFacade.getInstance().pauseSong(song);
        }

        public void playAllTracksInPlaylist() {
            MusicPlayerFacade.getInstance().playAllTracks();
        }

        public void playPreviousTrackInPlaylist() {
            MusicPlayerFacade.getInstance().playPreviousTrack();
        }

        public void queueSongNext(String songTitle) {
            Song song = findSongByTitle(songTitle);
            if (song == null) {
                throw new RuntimeException("Song \"" + songTitle + "\" not found.");
            }
            MusicPlayerFacade.getInstance().enqueueNext(song);
        }
    }

    public static void main(String[] args) {
        try {
            MusicPlayerApplication application = MusicPlayerApplication.getInstance();

            // Populate library
            application.createSongInLibrary("Kesariya", "Arijit Singh", "/music/kesariya.mp3");
            application.createSongInLibrary("Chaiyya Chaiyya", "Sukhwinder Singh", "/music/chaiyya_chaiyya.mp3");
            application.createSongInLibrary("Tum Hi Ho", "Arijit Singh", "/music/tum_hi_ho.mp3");
            application.createSongInLibrary("Jai Ho", "A. R. Rahman", "/music/jai_ho.mp3");
            application.createSongInLibrary("Zinda", "Siddharth Mahadevan", "/music/zinda.mp3");

            // Create playlist and add songs
            application.createPlaylist("Bollywood Vibes");
            application.addSongToPlaylist("Bollywood Vibes", "Kesariya");
            application.addSongToPlaylist("Bollywood Vibes", "Chaiyya Chaiyya");
            application.addSongToPlaylist("Bollywood Vibes", "Tum Hi Ho");
            application.addSongToPlaylist("Bollywood Vibes", "Jai Ho");

            // Connect device
            application.connectAudioDevice(DeviceType.BLUETOOTH);

            // Play/pause a single song
            application.playSingleSong("Zinda");
            application.pauseCurrentSong("Zinda");

            System.out.println("\n-- Sequential Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.SEQUENTIAL);
            application.loadPlaylist("Bollywood Vibes");
            application.playAllTracksInPlaylist();

            System.out.println("\n-- Custom Queue Playback --\n");
            application.selectPlayStrategy(PlayStrategyType.CUSTOM_QUEUE);
            application.loadPlaylist("Bollywood Vibes");
            application.queueSongNext("Kesariya");
            application.queueSongNext("Tum Hi Ho");
            application.playAllTracksInPlaylist();

        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }
}