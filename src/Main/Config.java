package Main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp){
        this.gp = gp;
    }

    public void safeConfig() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

        // MUSIC VOLUME
        bw.write(String.valueOf(gp.music.volumeScale)); // we can't pass int
        bw.newLine();

        // SE VOLUME
        bw.write(String.valueOf(gp.se.volumeScale));
        bw.newLine();

        bw.close();
    }

    public void loadConfig() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        String s;

        // MUSIC VOLUME
        s = br.readLine();
        gp.music.volumeScale = Integer.parseInt(s);

        // SE VOLUME
        s = br.readLine();
        gp.se.volumeScale = Integer.parseInt(s);

        br.close();
    }
}
