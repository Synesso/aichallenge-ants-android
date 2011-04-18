package net.badgerhunt.antroid;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ReplayDataParserTest {

    private ReplayDataParser subject = new ReplayDataParser();

    @Test
    public void mustParseSampleReplayData() throws Exception {
        String json = load("test/sample_replay_data.js");
        ReplayData data = subject.parse(json);
        assertEquals("ants", data.challenge);
        assertEquals(new SimpleDateFormat("MM-dd-yyyy").parse("10-04-2011"), data.date);
        assertEquals(3, data.players.size());
        assertEquals(new Player("amstan", "timeout", 6, 94), data.players.get(0));
        assertEquals(new Player("a1k0n", "crash", 3, 813), data.players.get(1));
        assertEquals(new Player("mega1", "Some other message", 7, 39), data.players.get(2));
        assertEquals("http://ai-contest.com/profile.php?user_id=~", data.userURL);
        assertEquals("12345", data.gameId);
        assertEquals("http://ai-contest.com/ants/visualizer.php?game_id=~", data.gameURL);
        assertEquals("storage", data.replayFormat);
        assertEquals("v ants 1\nplayers 3\nturns 200\n<...>", data.replayData);
    }

    private String load(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            builder.append('\n');
            line = reader.readLine();
        }
        return builder.toString();
    }
}
