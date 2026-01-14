import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JTextField;


public class Screen extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
    public static final record SongAndButton(Song song, JButton button) {
        @Override
        public String toString() {
            return song.toString();
        }
    };
    
    private MyArrayList<SongAndButton> playlist;
    private JButton sortByNameButton,sortByArtistButton,sortByAlbumButton, randomizeButton, addSongButton, removeByIndexButton;
    private JTextField textField;

    public Screen(){
        setFocusable(true); 
        setLayout(null);
        addKeyListener(this);
        addMouseListener(this);

        playlist = new MyArrayList<SongAndButton>();
        makeSongWithButton("Enter Sandman","The Black Album","Metallica");
        makeSongWithButton("Carry on my Wayward Son","Leftoverture","Kansas");
        makeSongWithButton("Blorpius","Slorpnorp","Gloopus Opus");
        makeSongWithButton("Ssssthssss","Good Slitherin Times","The Snakes");
        makeSongWithButton("Mmmmm oh yeah","Lebron James","Steph Curry");
        makeSongWithButton("Awwww no yeah sorry","oops","Nope");
        makeSongWithButton("Nada","Null","Idk honestly");

        sortByNameButton = new JButton("Sort by name");
        sortByArtistButton = new JButton("Sort by artist");
        sortByAlbumButton = new JButton("Sort by album");
        randomizeButton = new JButton("Randomize playlist");
        addSongButton = new JButton("Add song");
        removeByIndexButton = new JButton("Remove by index");
        textField = new JTextField();

        sortByNameButton.setBounds(50, 400, 200, 100);
        sortByArtistButton.setBounds(260, 400, 200, 100);
        sortByAlbumButton.setBounds(470, 400, 200, 100);
        randomizeButton.setBounds(50, 500, 200, 100);
        addSongButton.setBounds(50, 600, 200, 100);
        removeByIndexButton.setBounds(250, 740, 200, 100);
        textField.setBounds(50, 740, 200, 100);
        
        add(sortByNameButton);
        add(sortByArtistButton);
        add(sortByAlbumButton);
        add(randomizeButton);
        add(addSongButton);
        add(removeByIndexButton);
        add(textField);

        sortByNameButton.addActionListener(this);
        sortByArtistButton.addActionListener(this);
        sortByAlbumButton.addActionListener(this);
        randomizeButton.addActionListener(this);
        addSongButton.addActionListener(this);
        removeByIndexButton.addActionListener(this);
        textField.addActionListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1024,1024);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Enter the Name, Album, and Artist of the song in that order, separated by comma, apostrophe, or pipe", 50, 710);
        g.drawString("If you want to remove a song by index, enter the number that the song appears in the list", 50, 730);
        for (int i = 0; i < playlist.size(); i++) {
            g.drawString(playlist.get(i).song.toString(), 50, 25 * (i + 1));
        }
    }

    // interpret key clicks
    public void keyPressed(KeyEvent e){
    }

    //action listener
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == addSongButton) {
            String[] input = textField.getText().split("[;|,]");
            if (input.length != 3) {
                return;
            }
            makeSongWithButton(input[0], input[1], input[2]);
        }
        else if (e.getSource() == randomizeButton) {
            for (int i = 0; i < playlist.size(); i++) {
                int randomIndex = (int) (Math.random() * playlist.size());
                SongAndButton temp = playlist.get(i);
                playlist.set(i, playlist.get(randomIndex));
                playlist.set(randomIndex, temp);
            }
        }
        else if (e.getSource() == sortByNameButton) {
            sortList(Song.SortingType.NAME);
        }
        else if (e.getSource() == sortByArtistButton) {
            sortList(Song.SortingType.ARTIST);
        }
        else if (e.getSource() == sortByAlbumButton) {
            sortList(Song.SortingType.ALBUM);
        }
        else if (e.getSource() == removeByIndexButton) {
            try {
                int index = Integer.parseInt(textField.getText());
                if (index > playlist.size()) {
                    return;
                }
                JButton buttonToRemove = playlist.get(index - 1).button;
                buttonToRemove.setVisible(false);
                buttonToRemove.setEnabled(false);
                playlist.remove(index - 1);
                revalidate();
                updateButtonPos();
            }
            catch (NumberFormatException except) {}
        }
        repaint();
    }

    

    public void sortList(Song.SortingType type) {
        for (int i = playlist.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (playlist.get(j).song().getComponent(type).toLowerCase().compareTo(playlist.get(j+1).song().getComponent(type).toLowerCase()) > 0) {
                    SongAndButton temp = playlist.get(j);
                    playlist.set(j, playlist.get(j+1));
                    playlist.set(j+1, temp);
                }
            }
        }
    }

    //mouse listener
    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e){
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}

    private void makeSongWithButton(String name, String album, String artist) {
        Song song = new Song(name, album, artist);
        JButton removeButton = new JButton("Remove");
        SongAndButton pair = new SongAndButton(song, removeButton);
        playlist.add(pair);
        removeButton.setBounds(400, 25 * playlist.size() - 15, 100, 25);
        add(removeButton);
        removeButton.addActionListener(e -> {
            playlist.remove(pair);
            removeButton.setVisible(false);
            removeButton.setEnabled(false);
            remove(removeButton);
            updateButtonPos();
            revalidate();
            repaint();
        });
    }

    private void updateButtonPos() {
        for (int i = 0; i < playlist.size(); i++) {
            playlist.get(i).button.setBounds(400, 25 * i + 10, 100, 25);
        }
    }
}
