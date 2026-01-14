public class Song {
    private String name, album, artist;

    public Song(String name, String album, String artist) {
        this.name = name;
        this.album = album;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        Song other = (Song) o;
        return name.equals(other.getName()) && album.equals(other.getAlbum()) && artist.equals(other.getArtist());

    }

    @Override
    public String toString() {
        return name + " | " + album + " | " + artist;
    }

    public static enum SortingType {
        NAME,
        ARTIST,
        ALBUM
    }

    public String getComponent(SortingType type) {
        switch (type) {
            case NAME -> { return name; }
            case ARTIST -> { return artist; }
            case ALBUM -> { return album; }
        }
        return "How did we even get here?";
    }
}
